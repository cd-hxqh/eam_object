package cdhxqh.shekou.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by apple on 15/10/.
 */
public abstract class Entity implements Serializable {
    private static final long serialVersionUID = 2015050101L;

    abstract public void parse(JSONObject jsonObject) throws JSONException;
}
