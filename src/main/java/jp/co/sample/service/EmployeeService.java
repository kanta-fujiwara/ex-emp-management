package jp.co.sample.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * 従業員情報を操作するサービス.
 *
 * @author kanta.fujiwara
 */
@Service
@Transactional
public class EmployeeService {
  @Autowired
  private EmployeeRepository employeeRepository;

  /**
   * 従業員情報を全件取得する.
   *
   * @return 従業員情報のリスト。 従業員情報がない場合は空のリスト。
   */
  public List<Employee> showList() {
    return employeeRepository.findAll();
  }
}
