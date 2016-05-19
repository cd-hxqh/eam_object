package cdhxqh.shekou.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2016/5/17.
 * 事故
 */
@DatabaseTable(tableName = "Udev")
public class Udev implements Serializable {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "evnum")
    public String evnum;//事故编码
    @DatabaseField(columnName = "description")
    public String description;//描述
    @DatabaseField(columnName = "evtype")
    public String evtype;//事故类型
    @DatabaseField(columnName = "evclass")
    public String evclass;//事故分类
    @DatabaseField(columnName = "evgrade")
    public String evgrade;//事故等级
}
