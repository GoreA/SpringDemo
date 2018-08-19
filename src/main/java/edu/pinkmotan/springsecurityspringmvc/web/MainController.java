/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.pinkmotan.springsecurityspringmvc.web;

import edu.pinkmotan.springsecurityspringmvc.restresponse.StringResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Joe Grandja
 */
@Controller
public class MainController {

  @RequestMapping("/")
  public String root() {
    return "redirect:/index";
  }

  @RequestMapping("/index")
  public String index() {
    return "index";
  }

  @RequestMapping("/user/index")
  public String userIndex() {
    return "user/index";
  }

  @RequestMapping("/login")
  public String login() {
    return "login";
  }

  @RequestMapping("/login-error")
  public String loginError(Model model) {
    model.addAttribute("loginError", true);
    return "login";
  }

  @RequestMapping(value = "/hello", method = RequestMethod.GET,  produces = "application/json")
  public StringResponse getHello() {
    System.out.println("Hello!");
    return new StringResponse("Hello!");
  }

}