package cdhxqh.shekou.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2016/5/17.
 * 备件
 */
@DatabaseTable(tableName = "Item")
public class Item implements Serializable {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "ITEMNUM")
    public String itemnum;//备件编号
    @DatabaseField(columnName = "DESCRIPTION")
    public String description;//描述
    @DatabaseField(columnName = "ORDERUNIT")
    public String orderunit;//
    @DatabaseField(columnName = "STATUS")
    public String status;//狀態
    @DatabaseField(columnName = "UDSUBJECT")
    public String udsubject;//
    @DatabaseField(columnName = "UDBRAND")
    public String udbrand;//
    @DatabaseField(columnName = "UDMANUFACTURER")
    public String udmanufacturer;//
    @DatabaseField(columnName = "UDMODEL")
    public String udmodel;//型号
    @DatabaseField(columnName = "UDSTDNAME")
    public String udstdname;//
    @DatabaseField(columnName = "UDCHNNAME")
    public String udchnname;//
    @DatabaseField(columnName = "UDUSE")
    public String uduse;//用途
    @DatabaseField(columnName = "SITEID")
    public String siteid;//站点

}
