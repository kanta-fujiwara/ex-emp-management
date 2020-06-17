package jp.co.sample.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.sample.domain.Employee;
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
   * @return 従業員一覧画面
   */
  @RequestMapping("/showList")
  public String showList(Model model) {
    List<Employee> employeeList = employeeService.showList();
    model.addAttribute("employeeList", employeeList);
    return "employee/list";
  }

}
