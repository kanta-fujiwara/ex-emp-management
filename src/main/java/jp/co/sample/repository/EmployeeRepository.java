package jp.co.sample.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import jp.co.sample.domain.Employee;

/**
 * employeesテーブルを操作するリポジトリ.
 * 
 * @author kanta.fujiwara
 */
@Repository
public class EmployeeRepository {
  @Autowired
  private NamedParameterJdbcTemplate template;

  private static final String TABLE_NAME = "employees";

  private static final RowMapper<Employee> ROW_MAPPER = new BeanPropertyRowMapper<Employee>();

  /**
   * 従業員情報を全件取得する.
   *
   * @return 従業員情報のリスト(入社日降順)
   */
  public List<Employee> findAll() {
    String sql = ""
        + "SELECT id, name, image, gender, hire_date, mail_address, zip_code, "
        + "  address, telephone, salary, characteristics, dependents_count"
        + "    FROM " + TABLE_NAME + "　ORDER BY hire_date DESC;";

    return template.query(sql, ROW_MAPPER);
  }

  /**
   * IDから従業員情報を取得する.
   *
   * @param id 対象のID
   * @return idが一致する従業員情報。存在しない場合は例外が発生する。
   */
  public Employee load(Integer id) {
    String sql = ""
        + "SELECT id, name, image, gender, hire_date, mail_address, zip_code, "
        + "  address, telephone, salary, characteristics, dependents_count"
        + "    FROM " + TABLE_NAME + "WHERE id = :id";


    SqlParameterSource param =
        new MapSqlParameterSource()
            .addValue("id", id);

    return template.queryForObject(sql, param, ROW_MAPPER);
  }

  /**
   * 従業員情報を更新する.
   *
   * @param employee 新しい従業員情報。idが一致するものが更新される。
   */
  public void update(Employee employee) {
    String sql = ""
        + "UPDATE employees"
        + "  SET name=:name, image=:image, gender=:gender, hire_date=:hire_date, mail_address=:mail_address, zip_code=:zip_code,"
        + "    address=:address, telephone=:telephone, salary=:salary, characteristics=:characteristics, dependents_count=:dependents_count"
        + "      WHERE id=:id;";

    SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

    template.update(sql, param);
  }
}
