/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pinkmotan.springsecurityspringmvc.config;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.web.filter.GenericFilterBean;

/**
 *
 * @author agore
 */
public class CustomFilter extends GenericFilterBean {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomFilter.class);

  @Override
  public void doFilter(
          ServletRequest request,
          ServletResponse response,
          FilterChain chain) throws IOException, ServletException {
    System.out.println("Run, Filter! Run!!!");

    Optional<String> clientIP = getClientIP(request);

    if (clientIP.isPresent()) {
      if (!isAccessAllowed(clientIP.get(), request)) {
        forbidAccess(request, String.format("Incomming request has been rejected! Source IP [%s] is not trusted.", clientIP.get()));
      }
    } else {
      forbidAccess(request, "Access forbidden! Could not determine source IP of the incoming request.");
    }

    chain.doFilter(request, response);
  }

  /**
   * Gets the source IP of the incoming HTTP request.
   *
   * @return {@code Optional<String>}
   */
  private Optional<String> getClientIP(ServletRequest request) {
    Optional<String> clientIP = Optional.empty();
    if (request != null) {
      clientIP = Optional.of(request.getRemoteAddr());
    }
    return clientIP;
  }

  /**
   * Gets the ACL (Access control list) from the property file.
   *
   * @return a list of IPs.
   */
  private List<String> getACL() {
    String aclPropertyValue = "localhost,127.0.0.1,0:0:0:0:0:0:0:1";
    return Stream.of(aclPropertyValue.split(",")).collect(Collectors.toList());
  }

  /**
   * Checks whether the incoming request is allowed to reach the target
   * end-point based on the following constraints:<br>
   * 1. The method being invoked is annotated with {@link ACFIgnored}, which
   * means that the request doesn't have to be processed by this filter.<br>
   * 2. The class that contains the method being invoked is annotated with
   * {@link ACFIgnored}, which means that a request to any method from this
   * class doesn't have to be processed by this filter.<br>
   * 2. The source IP of the incoming request is presents in ACL.
   *
   * @param clientIP the IP of the incoming request.
   * @return <code>true</code> if the IP is presents in ACL, <code>false</code>
   * otherwise.
   */
  private boolean isAccessAllowed(String clientIP, ServletRequest request) {
//    Method method = ((HttpServletRequest)request).get();
//
//    if (method.isAnnotationPresent(ACFIgnored.class) || method.getDeclaringClass().isAnnotationPresent(ACFIgnored.class)) {
//      return true;
//    } else {
      List<String> acl = getACL();
      return acl.stream().anyMatch(aclEntry -> matches(clientIP, aclEntry));
//    }
//    return true;
  }

  /**
   * Rejects the incoming request and logs the message explaining why it was
   * rejected.
   *
   * @param requestContext the request to be rejected.
   * @param message the message that explains why the request was rejected.
   */
  private void forbidAccess(ServletRequest request, String message) {
    LOGGER.warn(message);
    throw new AccessDeniedException(message);
  }

  /**
   * Checks if client IP matches the expected IP or subnet.
   *
   * @param clientIP the IP extracted from the request.
   * @param expectedIPOrSubnet an IP or subnet from ACL.
   * @return <code>true</code> if the IPs match, <code>false</code> otherwise.
   */
  private boolean matches(String clientIP, String expectedIPOrSubnet) {
    IpAddressMatcher ipAddressMatcher = new IpAddressMatcher(expectedIPOrSubnet);
    return ipAddressMatcher.matches(clientIP);
  }
}
