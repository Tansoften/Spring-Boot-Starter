package tz.co.victorialush.lushpesa.middlewares;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;
import tz.co.victorialush.lushpesa.services.Authenticate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@Service
public class AuthMiddleware implements HandlerInterceptor {
    @Autowired
    private Authenticate auth;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        String token = null;
        boolean authenticated = false;

        Cookie[] cookies = request.getCookies();

        if(cookies == null){
            String bearerToken = request.getHeader("authorization");
            if(bearerToken != null){
                try{
                    token = bearerToken.split(" ")[1];
                    authenticated = auth.verifyToken(token);
                }catch (Exception ignored){
                }
            }
        }else{
            for(Cookie cookie:cookies){
                if(Objects.equals(cookie.getName(), "X-XSRF-TOKEN")){
                    token = cookie.getValue();
                    authenticated = auth.verifyToken(token);
                    break;
                }
            }
        }
        System.out.println(token);
        if(!authenticated){
            response.sendRedirect("/un-authenticated");
            return false;
        }

        request.setAttribute("user", auth.user());
        request.setAttribute("token", token);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
