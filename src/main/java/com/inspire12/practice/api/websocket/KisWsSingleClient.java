//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.client.WebSocketConnectionManager;
//import org.springframework.web.socket.client.standard.StandardWebSocketClient;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class KisWsSingleClient implements KisWsClient {
//
//    private static TrIdEnum signNoticeTrId;
//    private final WebSocketSessionHandler webSocketSessionHandler;
//    private final KisClient kisClient;
//    private final BalanceService balanceService;
//    @Value("${faipt.user-id:zest123}")
//    String htsId;
//    @Value("${faipt.ws:}")
//    private String baseUrl;
//
//    public static TrIdEnum getSignNoticeTrId() {
//        return KisWsSingleClient.signNoticeTrId;
//    }
//
//    @Value("${faipt.invest-type}")
//    void setSignNoticeTrId(InvestType investType) {
//        if (InvestType.PRACTICE == investType) {
//            signNoticeTrId = TrIdEnum.H0STCNI0;
//        } else {
//            signNoticeTrId = TrIdEnum.H0STCNI9;
//        }
//    }
//
//    public boolean startTick(KisMqConnectRequest kisTickRequest) {
//        if (this.connect(kisTickRequest)) {
//            this.sendTick(kisTickRequest);
//            return true;
//        }
//        return false;
//    }
//
//    private boolean connect(KisMqConnectRequest kisTickRequest) {
//        // redis 에서 다른 팟 혹은 세션이 중인지 확인
//        String trKey = kisTickRequest.getTrKey();
//        if (trKey == null) {
//            trKey = this.htsId;
//        }
//        if (this.isWsConnect(kisTickRequest.getTrId(), trKey)) {
//            log.info("already ws session connected");
//            return false;
//        }
//        log.info("connect start {} {}", kisTickRequest.getTrId(), trKey);
//        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
//        webSocketClient.setUserProperties(Map.of("id", WsKeyUtil.getRedisWsDestinationSessionKey(kisTickRequest.getTrId().getId(), trKey)));
//        WebSocketConnectionManager connectionManager = new WebSocketConnectionManager(webSocketClient, this.webSocketSessionHandler, "{uri}/{path}/{type}", this.baseUrl, "tryitout", kisTickRequest.getTrId().getId());
//        connectionManager.start();
//        // 소켓 연결 10초 동안 매초 확인
//        for (int i = 0; i < 10; i++) {
//            if (!connectionManager.isConnected()) {
//                try {
//                    TimeUnit.SECONDS.sleep(1L);
//                    log.info("websocket connection wait {} second {} {}", i + 1, kisTickRequest.getTrId(), trKey);
//                } catch (InterruptedException e) {
//                    log.info("Interrupted!", e);
//                    Thread.currentThread().interrupt();
//                }
//            }
//        }
//        return connectionManager.isConnected();
//    }
//
//    public boolean isWsConnect(TrIdEnum trId, String trKey) {
//        return this.webSocketSessionHandler.isWsConnect(trId, trKey);
//    }
//
//    public boolean isWsConnect() {
//        return this.webSocketSessionHandler.isWsConnect(getSignNoticeTrId(), this.htsId);
//    }
//
//
//    private void sendTick(KisMqConnectRequest kisTickRequest) {
//        String trKey = kisTickRequest.getTrKey();
//        if (trKey == null) {
//            trKey = this.htsId;
//        }
//        this.webSocketSessionHandler.sendSubscribeCode(trKey, kisTickRequest.getTrId(), this.getApprovalKey());
//    }
//
//
//    public Map<String, Map<String, String>> getLocalWebsocketStatus() {
//        Map<String, Map<String, String>> socketMap = new HashMap<>();
//        Map<String, String> localSocketMap = new HashMap<>();
//        this.webSocketSessionHandler.getWebSocketSessionMap().forEach((key, value) -> localSocketMap.put(key, value.getId()));
//        socketMap.put("local", localSocketMap);
//        //
//        Map<String, String> wsSession = this.webSocketSessionHandler.getWsSession(getSignNoticeTrId());
//        socketMap.put("global", wsSession);
//        return socketMap;
//    }
//
//    public Map<String, String> resetLocalWebsocketSession() {
//        Map<String, String> closedSocketMap = new HashMap<>();
//        this.webSocketSessionHandler.getWebSocketSessionMap().forEach((key, value) -> {
//            try {
//                log.info("reset session {}", value.getNativeSession().getId());
//                value.close(CloseStatus.GOING_AWAY);
//                closedSocketMap.put(key, value.getId());
//
//            } catch (IOException e) {
//                log.error("reset socket error ", e);
//            }
//        });
//        this.webSocketSessionHandler.getWebSocketSessionMap().clear();
//
//        return closedSocketMap;
//    }
//
//    public String getApprovalKey() {
//        Balance balance = balanceService.getBaseBalance();
//
//        ApprovalResponse approvalResponse = kisClient.issueSocketApprovalKey(ApprovalRequest.create(balance.getStockAppkey(), balance.getStockAppsecret()));
//        log.info("approval_key check {}", approvalResponse.getApprovalKey());
//        return approvalResponse.getApprovalKey();
//    }
//
//}
