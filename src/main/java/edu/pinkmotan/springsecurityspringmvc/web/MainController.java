
package edu.pinkmotan.springsecurityspringmvc.web;

import edu.pinkmotan.springsecurityspringmvc.restresponse.StringResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author GoreA
 */
@Controller
public class MainController {

  @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public StringResponse getHello() {
    System.out.println("Hello!");
    StringResponse r = new StringResponse();
    r.setResponse("Hello!!!");
    return r;
  }

}
