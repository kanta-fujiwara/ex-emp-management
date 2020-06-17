package jp.co.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdministratorController {

  @RequestMapping("/")
  public String index() {
    return "administrator/login";
  }

  @RequestMapping("/toInsert")
  public String toInsert() {
    return "administrator/insert";
  }

}
