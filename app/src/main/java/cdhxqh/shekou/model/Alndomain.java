package cdhxqh.shekou.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2016/5/17.
 * 抢修班组
 */
@DatabaseTable(tableName = "Alndomain")
public class Alndomain implements Serializable {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "value")
    public String value;//值
    @DatabaseField(columnName = "description")
    public String description;//描述

}
