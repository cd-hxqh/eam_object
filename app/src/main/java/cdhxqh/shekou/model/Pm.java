package cdhxqh.shekou.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2016/5/24.
 * 预防性维护
 */
@DatabaseTable(tableName = "Pm")
public class Pm implements Serializable {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "pmnum")
    public String pmnum;//预防性维护
    @DatabaseField(columnName = "description")
    public String description;//描述
    @DatabaseField(columnName = "assetnum")
    public String assetnum;//设备
    @DatabaseField(columnName = "jpnum")
    public String jpnum;//作业计划
    @DatabaseField(columnName = "status")
    public String status;//状态
    @DatabaseField(columnName = "priority")
    public String priority;//优先级
    @DatabaseField(columnName = "siteid")
    public String siteid;//地点
}
