package cdhxqh.shekou.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by think on 2016/5/17.
 * 故障类别
 */
@DatabaseTable(tableName = "Alndomain2")
public class Alndomain2 implements Serializable {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "value")
    public String value;//值
    @DatabaseField(columnName = "description")
    public String description;//描述

}
