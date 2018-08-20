
package edu.pinkmotan.springsecurityspringmvc.errorhandlers;

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

/**
 *
 * @author GoreA
 */
public class ControlListAccessDeniedHandler implements AccessDeniedHandler {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(ControlListAccessDeniedHandler.class);
 
    @Override
    public void handle(
      HttpServletRequest request,
      HttpServletResponse response, 
      AccessDeniedException exc) throws IOException, ServletException {
        response.sendError(403, exc.getMessage());
    }
}