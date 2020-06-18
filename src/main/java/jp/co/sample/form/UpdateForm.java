package jp.co.sample.form;

/**
 * /employee/update で受け取るフォームデータ
 *
 * @author kanta.fujiwara
 */
public class UpdateForm {
  /** 従業員ID */
  private Integer id;
  /** 新しい扶養人数 */
  private Integer dependentsCount;

  public UpdateForm() {}

  public UpdateForm(Integer id, Integer dependentsCount) {
    this.id = id;
    this.dependentsCount = dependentsCount;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getDependentsCount() {
    return dependentsCount;
  }

  public void setDependentsCount(Integer dependentsCount) {
    this.dependentsCount = dependentsCount;
  }
}
