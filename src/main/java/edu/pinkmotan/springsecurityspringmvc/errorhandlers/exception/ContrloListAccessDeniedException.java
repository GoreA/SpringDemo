
package edu.pinkmotan.springsecurityspringmvc.errorhandlers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author GoreA
 */
@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="No permission to access requested resources")
public class ContrloListAccessDeniedException extends AccessDeniedException{

  public ContrloListAccessDeniedException(String message) {
    super(message);
  }

}
