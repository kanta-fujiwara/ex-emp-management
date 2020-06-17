package jp.co.sample.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 管理者情報を操作するコントローラ.
 *
 * @author kanta.fujiwara
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

  /** 管理者の業務処理を行うクラス */
  @Autowired
  private AdministratorService administratorService;

  /** セッションにデータを保存するオブジェクト */
  @Autowired
  private HttpSession session;

  /**
   * ログイン画面を表示する.
   *
   * @param model ビューに渡すデータを保存するオブジェクト
   * @return 管理者ログイン画面
   */
  @RequestMapping("/")
  public String toLogin(Model model) {
    model.addAttribute(new LoginForm());
    model.addAttribute("isFailed", false);
    return "administrator/login";
  }

  /**
   * 管理者のログイン処理を行う.
   *
   * @param form 受け取ったログイン情報
   * @param model ビューに渡すデータを保存するオブジェクト
   * @return 従業員一覧へリダイレクト
   */
  @RequestMapping("/login")
  public String login(LoginForm form, Model model) {
    String mailAddress = form.getMailAddress();
    String password = form.getPassword();
    Administrator administrator = administratorService.login(mailAddress, password);

    if (administrator == null) {
      model.addAttribute(form);
      model.addAttribute("isFailed", true);
      return "administrator/login";
    } else {
      session.setAttribute("administratorName", administrator.getName());
      return "redirect:/employee/showList";
    }
  }


  /**
   * 管理者登録画面に遷移する.
   *
   * @param model ビューに渡すデータを保存するオブジェクト
   * @return 管理者登録画面
   */
  @RequestMapping("/toInsert")
  public String toInsert(Model model) {
    model.addAttribute(new InsertAdministratorForm());
    return "administrator/insert";
  }

  /**
   * 管理者情報を登録する.
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
