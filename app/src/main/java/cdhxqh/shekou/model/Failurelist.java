package cdhxqh.shekou.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2016/5/25.
 * 故障代码
 *
 */
@DatabaseTable(tableName = "Failurelist")
public class Failurelist implements Serializable {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "failurecode")
    public String failurecode;//故障代码
    @DatabaseField(columnName = "description")
    public String description;//描述
    @DatabaseField(columnName = "parent")
    public String parent;//父级
    @DatabaseField(columnName = "type")
    public String type;//类型
    @DatabaseField(columnName = "failurelist")
    public String failurelist;//
}
