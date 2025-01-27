//package com.inspire12.practice.api.websocket;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.socket.CloseStatus;
//import org.springframework.web.reactive.socket.WebSocketSession;
//import org.springframework.web.reactive.socket.adapter.StandardWebSocketSession;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.Objects;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.TimeUnit;
//
//@RequiredArgsConstructor
//@Slf4j
//@Component
//public class WebSocketSessionHandler extends TextWebSocketHandler {
//
//    private static final String PINGPONG = "PINGPONG";
//    private final ObjectMapper mapper;
//    private final RedisTemplate<String, Object> redisTemplate;
//    private final RabbitMqService rabbitMqService;
//    @Getter
//    private final Map<String, StandardWebSocketSession> webSocketSessionMap = new ConcurrentHashMap<>();
//
//    @Value("${faipt.user-id}")
//    private String htsId;
//
//
//    public boolean isWsConnect(TrIdEnum trId, String trKey) {
//        if (trId == TrIdEnum.H0STCNI0 || trId == TrIdEnum.H0STCNI9) {
//            // 체결가 통보일 경우
//            return !Objects.isNull(redisTemplate.opsForValue().get(WsKeyUtil.getRedisWsDestinationSessionKey(trId.getId(), htsId)));
//        } else {
//            // 호가, 체결가 일경우
//            return !Objects.isNull(redisTemplate.opsForValue().get(WsKeyUtil.getRedisWsDestinationSessionKey(trId.getId(), trKey)));
//        }
//    }
//
//    public Map<String, String> getWsSession(TrIdEnum trId) {
//        Object o = redisTemplate.opsForValue().get(WsKeyUtil.getRedisWsDestinationSessionKey(trId.getId(), htsId));
//        if (o == null) {
//            return Map.of();
//        }
//        return Map.of(WsKeyUtil.getRedisWsDestinationSessionKey(trId.getId(), htsId), (String) o);
//    }
//
//    boolean sendSubscribeCode(String code, TrIdEnum trId, String approvalKey) {
//        if (isWsConnect(trId, code)) {
//            log.error("socket not connected {} {} {}", code, trId, approvalKey);
//            return false;
//        }
//        WebSocketSession webSocketSession = webSocketSessionMap.get(WsKeyUtil.getRedisWsDestinationSessionKey(trId.getId(), code));
//        if (webSocketSession == null) {
//            log.error("socket not created {} {} {}", code, trId, approvalKey);
//            return false;
//        }
//        KisWsRequest request;
//        if (trId == TrIdEnum.H0STCNI0 || trId == TrIdEnum.H0STCNI9) {
//            request = KisWsRequest.create(approvalKey, trId.getId(), htsId);
//        } else {
//            request = KisWsRequest.create(approvalKey, trId.getId(), code);
//        }
//        try {
//            String raw = mapper.writeValueAsString(request);
//            log.info("session subscribe request: {}", raw);
//            webSocketSession.sendMessage(new TextMessage(raw));
//            return true;
//        } catch (IOException e) {
//            log.error("connection parse error", e);
//        }
//        return true;
//    }
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
//        Map<String, Object> userProperties = ((StandardWebSocketSession) webSocketSession).getNativeSession().getUserProperties();
//
//        log.info("connection {} {}", userProperties.get("id"), webSocketSession.getId());
//        webSocketSessionMap.put((String) userProperties.getOrDefault("id", ""), (StandardWebSocketSession) webSocketSession);
//    }
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        log.debug("handleMessage msg {}, session {}", message.getPayload(), session);
//        String payload = message.getPayload();
//        // 전문 데이터 처리
//        if (payload.charAt(0) == '0' || payload.charAt(0) == '1') {
//            String[] split = payload.split("\\|");
//            String trId = split[1];
//            log.info("websocket subscribe: {} {}", trId, session.getId());
//            // 체결가 확인
//            if (TrIdEnum.checkPurchaseType(trId)) {
//                String code = split[3].substring(0, split[3].indexOf("^"));
//                // 현재 팟에서 지속적으로 보고 있는지 확인하기 위해
//                Object andExpireWithPod = redisTemplate.opsForValue().getAndExpire(WsKeyUtil.getRedisWsSessionKeyWithPod(trId, code), 30L, TimeUnit.SECONDS);
//                if (andExpireWithPod == null) {
//                    redisTemplate.opsForValue().set(WsKeyUtil.getRedisWsSessionKeyWithPod(trId, code), session.getId(), 30L, TimeUnit.SECONDS);
//                }
//
//                KisWsOrderContractPriceInfo kisWsInfo = KisWsOrderContractPriceInfo.create(payload);
//                rabbitMqService.sendStockTransactionToStomp(WsKeyUtil.getMqDestinationKey(trId, kisWsInfo.getTrKey()), kisWsInfo);
//            }
//            // 호가 확인
//            else if (TrIdEnum.checkAskPriceType(trId)) {
//                String code = split[3].substring(0, split[3].indexOf("^"));
//                // 현재 팟에서 지속적으로 보고 있는지 확인하기 위해
//                Object andExpireWithPod = redisTemplate.opsForValue().getAndExpire(WsKeyUtil.getRedisWsSessionKeyWithPod(trId, code), 15L, TimeUnit.SECONDS);
//                if (andExpireWithPod == null) {
//                    redisTemplate.opsForValue().set(WsKeyUtil.getRedisWsSessionKeyWithPod(trId, code), session.getId(), 15L, TimeUnit.SECONDS);
//                }
//
//                KisWsOrderBookInfo kisWsInfo = KisWsOrderBookInfo.create(payload);
//                rabbitMqService.sendAskToStomp(WsKeyUtil.getMqDestinationKey(trId, kisWsInfo.getTrKey()), kisWsInfo);
//            }
//
//            // 내 체결 통보 확인
//            else if (TrIdEnum.checkSigningNoticeType(trId)) {
//                String aesKey = Objects.requireNonNull(redisTemplate.opsForValue().get("aes_key:" + session.getId())).toString();
//                String aesIv = Objects.requireNonNull(redisTemplate.opsForValue().get("aes_iv:" + session.getId())).toString();
//                AES256 aes256 = new AES256();
//                String output = aes256.decrypt(split[3], aesKey, aesIv);
//
//                KisWsOrderMyContractInfo kisWsInfo = KisWsOrderMyContractInfo.create(trId, output);
//                log.info("{} {} {}", trId, output, kisWsInfo);
//                rabbitMqService.sendMyTransactionToStomp(WsKeyUtil.getMqDestinationKey(trId, htsId), kisWsInfo);
//            }
//        } else {
//            // 전문 데이터가 아닌 websocket response 처리
//            KisSubscribeResponse response = mapper.readValue(payload, KisSubscribeResponse.class);
//
//            String trId = response.getHeader().getTrId();
//            if (PINGPONG.equals(trId)) {
//                log.debug("sendMessage PINGPONG {}", payload);
//                session.sendMessage(new TextMessage(payload)); // PINGPONG 일 경우 받은 데이터를 되돌려줘서 세션 유지
//
//                // 내 체결 확인 통보 세션일 확인 pingpong일 경우 30초간 redis 세션 연장, pingpong 은 10초 주기로 오고 있음
//                log.info("PINGPONG 체결 session 연장 {}", WsKeyUtil.getRedisWsDestinationSessionKey(KisWsSingleClient.getSignNoticeTrId().getId(), htsId));
//                Object andExpire = redisTemplate.opsForValue().getAndExpire(WsKeyUtil.getRedisWsDestinationSessionKey(KisWsSingleClient.getSignNoticeTrId().getId(), htsId), 30L, TimeUnit.SECONDS);
//                if (andExpire == null) {
//                    redisTemplate.opsForValue().set(WsKeyUtil.getRedisWsDestinationSessionKey(KisWsSingleClient.getSignNoticeTrId().getId(), htsId), session.getId(), 15L, TimeUnit.SECONDS);
//                }
//
//            } else if (TrIdEnum.trIdSet.contains(trId)) {
//                if (response.getBody().getMsg1() != null) {
//                    if (response.getBody().getMsg1().contains("ERROR")) {
//                        log.error("ws connection info {}", response.getBody());
//
//                        rabbitMqService.sendTickResponseToStomp(session.getId(), response);
//                    } else if (response.getBody().getMsg1().contains("ALREADY IN SUBSCRIBE")) {
//                        log.error("ws connection info {}", response.getBody());
//
//                    } else if (response.getBody().getMsg1().contains("NOT FOUND")) {
//                        log.error("ws connection info {}", response.getBody());
//                        Balance balance = balanceService.getBaseBalance();
//                        ApprovalRequest approvalRequest = ApprovalRequest.create(balance.getStockAppkey(), balance.getStockAppsecret());
//                        String key = "kis:issueSocketApprovalKey:".concat(approvalRequest.getAppkey().concat("@").concat(approvalRequest.getSecretkey()));
//                        redisTemplate.delete(key);
//
//                        session.close(CloseStatus.SESSION_NOT_RELIABLE);
//                    } else {
//                        log.info("ws connection info {}", response.getBody().getMsg1());
//                        if (TrIdEnum.checkSigningNoticeType(trId) && response.getBody().getOutput() != null) {
//                            String aesKey = response.getBody().getOutput().getKey();
//                            String aesIv = response.getBody().getOutput().getIv();
//                            redisTemplate.opsForValue().set("aes_key:" + session.getId(), aesKey, 8L, TimeUnit.HOURS);
//                            redisTemplate.opsForValue().set("aes_iv:" + session.getId(), aesIv, 8L, TimeUnit.HOURS);
//                            redisTemplate.opsForValue().set(WsKeyUtil.getRedisWsDestinationSessionKey(trId, htsId), session.getId(), 30L, TimeUnit.SECONDS);
//                        }
//                    }
//                } else {
//                    rabbitMqService.sendTickResponseToStomp(session.getId(), response);
//                }
//            }
//        }
//    }
//
//
//    @Override
//    public void handleTransportError(WebSocketSession session, Throwable exception) {
//        log.info("handleTransportError: {}", session.getId());
//
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
//        Map<String, Object> userProperties = ((StandardWebSocketSession) session).getNativeSession().getUserProperties();
//        log.info("afterConnectionClosed: {} {} {} isOpen: {}", session.getId(), userProperties.get("id"), closeStatus.getReason(), session.isOpen());
//        redisTemplate.opsForValue().getAndDelete((String) userProperties.get("id"));
//    }
//
//
//    @Override
//    public boolean supportsPartialMessages() {
//        return false;
//    }
//}
