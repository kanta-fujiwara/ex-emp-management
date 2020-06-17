package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {

  @Autowired
  private AdministratorService administratorService;

  /**
   * @return 管理者ログイン画面
   */
  @RequestMapping("/")
  public String index() {
    return "administrator/login";
  }

  /**
   * @param model モデル
   * @return 管理者登録画面
   */
  @RequestMapping("/toInsert")
  public String toInsert(Model model) {
    model.addAttribute(new InsertAdministratorForm());
    return "administrator/insert";
  }

  /**
   * 管理者情報を登録する
   *
   * @param form 登録する管理者情報
   * @return 管理者ログイン画面へリダイレクト
   */
  @RequestMapping("/insert")
  public String insert(InsertAdministratorForm form) {
    Administrator administrator = new Administrator();
    BeanUtils.copyProperties(form, administrator);
    administratorService.insert(administrator);
    return "redirect:/";
  }

}
