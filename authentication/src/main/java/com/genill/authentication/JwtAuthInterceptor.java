package com.genill.authentication;

import com.genill.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * JwtAuthInterceptor
 */
public class JwtAuthInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private List<String> excludedUrls;

    @Autowired
    private JwtTokenHandler tokenHandler;

    public JwtAuthInterceptor() {}

    public JwtAuthInterceptor(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException, ServletException {

    	if(excludedUrls.contains(request.getRequestURI().replaceFirst(request.getContextPath(), "")) || request.getMethod().equals("OPTIONS")) {
            return true;
        }

        Users authedUser = tokenHandler.getUser(request.getHeader(AUTHORIZATION_HEADER));
        if(authedUser == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header.");
            return false;
        } else {
            request.setAttribute(RequestAuthProvider.USER_KEY, authedUser);
            return true;
        }
    }



    /**
     * @param exludedUrls the exludedUrls to set
     */
    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}