package cdhxqh.shekou.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2016/5/12.
 * 设备AssetDao
 */
@DatabaseTable(tableName = "Assets")
public class Assets implements Serializable {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "assetnum")
    public String assetnum;//设备编码
    @DatabaseField(columnName = "description")
    public String description;//设备名称
    @DatabaseField(columnName = "eq1")
    public String eq1;//管理组
    @DatabaseField(columnName = "eq2")
    public String eq2;//管理室
    @DatabaseField(columnName = "eq3")
    public String eq3;//管理班组
    @DatabaseField(columnName = "assettype")
    public String assettype;//资产类型
    @DatabaseField(columnName = "udassettype")
    public String udassettype;//设备类型
    @DatabaseField(columnName = "siteid")
    public String siteid;//地点
}
