package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import jp.co.sample.domain.Administrator;

/**
 * administratorsテーブルを操作するリポジトリ.
 *
 * @author kanta.fujiwara
 */
@Repository
public class AdministratorRepository {
  @Autowired
  private NamedParameterJdbcTemplate template;

  private static final String TABLE_NAME = "administrators";

  private static final RowMapper<Administrator> ROW_MAPPER = (rs, i) -> {
    Administrator administrator = new Administrator();
    administrator.setId(rs.getInt("id"));
    administrator.setName(rs.getString("name"));
    administrator.setMailAddress(rs.getString("mail_address"));
    administrator.setPassword(rs.getString("password"));
    return administrator;
  };

  /**
   * 管理者情報を追加する.
   *
   * IDは自動採番される
   *
   * @param administrator 追加する管理者情報(IDは無視される)
   */
  public void insert(Administrator administrator) {
    String sql = "INSERT INTO "
        + TABLE_NAME + "(name, mail_address, password)"
        + "VALUES (:name, :mailAddress, :password);";

    SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
    template.update(sql, param);
  }

  /**
   * メールアドレスとパスワードから管理者情報を検索する.
   *
   * @param mailAddress メールアドレス
   * @param password パスワード
   *
   * @return メールアドレスとパスワードが一致する管理者情報。該当情報がない場合はnull。
   */
  public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
    String sql = ""
        + "SELECT id, name, mail_address, password"
        + "  FROM " + TABLE_NAME
        + "  WHERE mail_address = :mailAddress AND password = :password;";

    SqlParameterSource param =
        new MapSqlParameterSource()
            .addValue("mailAddress", mailAddress)
            .addValue("password", password);

    try {
      return template.queryForObject(sql, param, ROW_MAPPER);
    } catch (Exception ex) {
      return null;
    }
  }
}
