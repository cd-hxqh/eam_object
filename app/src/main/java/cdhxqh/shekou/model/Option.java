package cdhxqh.shekou.model;

import java.io.Serializable;

/**
 * Created by think on 2016/5/16.
 */
public class Option implements Serializable{
    private String name;
    private String description;
    private String value;
    private String value2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }
}
