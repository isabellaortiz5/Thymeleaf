package com.edu.taller.ortiz.isabella.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class LoggingAccessDeniedHandler implements AccessDeniedHandler {
	
	private static Logger logger = LoggerFactory.getLogger(LoggingAccessDeniedHandler.class);
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		Authentication a = SecurityContextHolder.getContext().getAuthentication();

        if (!a.equals(null)) {
            logger.info(a.getName()
                    + " there is no permission to access this protected resource: "
                    + request.getRequestURI());
        }
        
        response.sendRedirect(request.getContextPath() + "/access-denied");
		
	}
	
}
