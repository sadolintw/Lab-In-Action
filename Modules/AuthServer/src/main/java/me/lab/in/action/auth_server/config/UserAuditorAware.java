//package me.lab.in.action.auth_server.config;
//
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class UserAuditorAware implements AuditorAware<String> {
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        String userid = "";
//        //正式環境應該用的寫法
//        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication.isAuthenticated()==false) {
//            userid = null;
//        }else{
//            userid = authentication.getPrincipal().getUserid();
//        }
//        return Optional.of(userid);
//    }
//}
