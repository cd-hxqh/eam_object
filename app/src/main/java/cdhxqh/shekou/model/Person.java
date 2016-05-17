package cdhxqh.shekou.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2016/5/17.
 * 人员
 */
@DatabaseTable(tableName = "Person")
public class Person implements Serializable {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "personid")
    public String personid;//人员
    @DatabaseField(columnName = "displayname")
    public String displayname;//名称
    @DatabaseField(columnName = "title")
    public String title;//头衔
    @DatabaseField(columnName = "department")
    public String department;//部门
    @DatabaseField(columnName = "location")
    public String location;//人员的位置
    @DatabaseField(columnName = "locationsite")
    public String locationsite;//人员的地点
    @DatabaseField(columnName = "locationorg")
    public String locationorg;//组织
}
