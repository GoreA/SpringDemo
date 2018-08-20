/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pinkmotan.springsecurityspringmvc.errorhandlers;

import edu.pinkmotan.springsecurityspringmvc.errorhandlers.exception.ContrloListAccessDeniedException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author GoreA
 */
@ControllerAdvice
public class ExceptionController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

  @ExceptionHandler({ContrloListAccessDeniedException.class})
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ResponseEntity handleError(HttpServletRequest request, Exception e) {
    LOGGER.warn(e.getMessage());
    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
  }
}
