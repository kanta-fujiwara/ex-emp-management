package jp.co.sample.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報を操作するコントローラ.
 *
 * @author kanta.fujiwara
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
  /** 従業員の業務処理を行うクラス */
  @Autowired
  private EmployeeService employeeService;

  /** セッションにデータを保存するオブジェクト */
  @Autowired
  private HttpSession session;

  /**
   * 従業員一覧を表示する.
   *
   * @param model ビューに渡すデータを保存するオブジェクト
   * @return 従業員一覧画面。ログインしていない場合はログイン画面へリダイレクト。
   */
  @GetMapping("/showList")
  public String showList(Model model) {
    // ログイン情報の確認
    if (session.getAttribute("administratorName") == null) {
      return "redirect:/";
    }

    List<Employee> employeeList = employeeService.showList();
    model.addAttribute("employeeList", employeeList);
    return "employee/list";
  }

  /**
   * 従業員の詳細ページを表示する.
   *
   * @param id 表示する従業員のID
   * @param model ビューに渡すデータを保存するオブジェクト
   * @return 従業員の詳細ページ。 ログインしていない場合はログイン画面へリダイレクト。
   */
  @GetMapping("/showDetail")
  public String showDetail(Integer id, Model model) {
    // ログイン情報の確認
    if (session.getAttribute("administratorName") == null) {
      return "redirect:/";
    }

    Employee employee = employeeService.showDetail(id);
    model.addAttribute("employee", employee);
    return "employee/detail";
  }

  /**
   * 扶養人数を更新する.
   *
   * @param form 受け取った情報
   * @return 従業員一覧にリダイレクト
   */
  @PostMapping("/update")
  public String update(UpdateForm form) {
    // ログイン情報の確認
    if (session.getAttribute("administratorName") == null) {
      return "redirect:/";
    }

    Employee employee = employeeService.showDetail(form.getId());
    employee.setDependentsCount(form.getDependentsCount());
    employeeService.update(employee);
    return "redirect:/employee/showList";
  }

}
