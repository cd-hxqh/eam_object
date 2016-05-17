package cdhxqh.shekou.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2016/5/17.
 * 作业计划
 */
@DatabaseTable(tableName = "JobPlan")
public class JobPlan implements Serializable {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "jpnum")
    public String jpnum;//作业计划
    @DatabaseField(columnName = "description")
    public String description;//描述
    @DatabaseField(columnName = "templatetype")
    public String templatetype;//模板类型
    @DatabaseField(columnName = "udassettype")
    public String udassettype;//设备类型
    @DatabaseField(columnName = "udassettype_description")
    public String udassettype_description;//类型描述
    @DatabaseField(columnName = "udassettype1")
    public String udassettype1;//类型描述1
    @DatabaseField(columnName = "orgid")
    public String orgid;//组织
    @DatabaseField(columnName = "siteid")
    public String siteid;//地点
}
