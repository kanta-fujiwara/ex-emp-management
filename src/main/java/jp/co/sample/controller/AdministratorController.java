package jp.co.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.sample.form.InsertAdministratorForm;

@Controller
@RequestMapping("/")
public class AdministratorController {


  @RequestMapping("/")
  public String index() {
    return "administrator/login";
  }

  @RequestMapping("/toInsert")
  public String toInsert(Model model) {
    model.addAttribute(new InsertAdministratorForm());
    return "administrator/insert";
  }

  @RequestMapping("/insert")
  public String insert(InsertAdministratorForm form) {
    return "redirect:/";
  }


}
