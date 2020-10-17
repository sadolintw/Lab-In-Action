package me.lab.in.action.auth_server.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class Oauth2Aspect {

    /**
     * reset hash mark to question mark for implicit grant type
     * @param jp
     * @param retVal
     * @throws Exception
     */
    @AfterReturning(pointcut = "execution( * org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint.*(..))", returning="retVal")
    public void executeAfterAuthentication(JoinPoint jp, Object retVal) throws Exception {
        System.out.println(":Authentication done");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        System.out.println(":Authentication done " + request.getRequestURI());
        if(retVal instanceof ModelAndView){
            ModelAndView modelAndView = (ModelAndView) retVal;
            modelAndView.setView(new RedirectView(((RedirectView)modelAndView.getView()).getUrl().replace("#", "?")));
        }
    }
}
