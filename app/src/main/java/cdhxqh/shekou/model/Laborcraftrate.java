package cdhxqh.shekou.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2016/5/24.
 * 员工工种
 */
@DatabaseTable(tableName = "Laborcraftrate")
public class Laborcraftrate implements Serializable {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "laborcode")
    public String laborcode;//员工
    @DatabaseField(columnName = "displayname")
    public String displayname;//描述
    @DatabaseField(columnName = "craft")
    public String craft;//工种
    @DatabaseField(columnName = "craft_description")
    public String craft_description;//描述
    @DatabaseField(columnName = "locationsite")
    public String locationsite;//工种
}
