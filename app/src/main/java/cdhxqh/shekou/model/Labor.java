package cdhxqh.shekou.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2016/5/17.
 * 员工
 */
@DatabaseTable(tableName = "Labor")
public class Labor implements Serializable {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "laborcode")
    public String laborcode;//员工
    @DatabaseField(columnName = "displayname")
    public String displayname;//名称
    @DatabaseField(columnName = "udisoutside")
    public String udisoutside;//是否外部人员
    @DatabaseField(columnName = "craft")
    public String craft;//工种
    @DatabaseField(columnName = "craft_description")
    public String craft_description;//描述
    @DatabaseField(columnName = "udeq1")
    public String udeq1;//管理组
    @DatabaseField(columnName = "udeq2")
    public String udeq2;//管理室
    @DatabaseField(columnName = "udeq3")
    public String udeq3;//管理班组

}
