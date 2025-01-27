//package com.inspire12.practice.api.utils;
//
//import org.springframework.util.StringUtils;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
//public class AuditUtils {
//    public static String getAuditUser() {
//        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (sra != null) {
//            HttpServletRequest request = sra.getRequest();
//            if (request != null) {
//                String userId = request.getHeader(AuditorAwareImpl.USER_ID);
//                if (!StringUtils.hasText(userId)) {
//                    throw new FaiptCommonException(ErrorCode.INTERNAL_SERVER_ERROR, "Audit - no audit user");
//                }
//                return userId;
//            } else {
//                throw new FaiptCommonException(ErrorCode.INTERNAL_SERVER_ERROR, "Audit - no request ");
//            }
//        } else {
//            throw new FaiptCommonException(ErrorCode.INTERNAL_SERVER_ERROR, "Audit - not servlet");
//        }
//    }
//}
