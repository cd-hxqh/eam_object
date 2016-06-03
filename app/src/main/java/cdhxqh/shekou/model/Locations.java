package cdhxqh.shekou.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2016/5/17.
 * 位置
 */
@DatabaseTable(tableName = "Locations")
public class Locations implements Serializable {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "locationsid")
    public String locationsid;//唯一ID
    @DatabaseField(columnName = "location")
    public String location;//位置编号
    @DatabaseField(columnName = "description")
    public String description;//描述
    @DatabaseField(columnName = "orgid")
    public String orgid;//组织
    @DatabaseField(columnName = "siteid")
    public String siteid;//站点
    @DatabaseField(columnName = "type")
    public String type;//类型

}
