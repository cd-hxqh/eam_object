package cdhxqh.shekou.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2016/5/17.
 * 立项申报
 */
@DatabaseTable(tableName = "Projappr")
public class Projappr implements Serializable {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "prjnum")
    public String prjnum;//项目编码
    @DatabaseField(columnName = "projapprnum")
    public String projapprnum;//立项编号
    @DatabaseField(columnName = "bugnum")
    public String bugnum;//项目预算
    @DatabaseField(columnName = "description")
    public String description;//描述
    @DatabaseField(columnName = "year")
    public String year;//项目年份
    @DatabaseField(columnName = "ysje")
    public String ysje;//项目预算金额
    @DatabaseField(columnName = "fzrqm")
    public String fzrqm;//负责人
}
