package cdhxqh.shekou.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import cdhxqh.shekou.OrmLiteHelper.DatabaseHelper;
import cdhxqh.shekou.model.Alndomain2;

/**
 * Created by think on 2016/5/18.
 * 故障类别
 */
public class Alndomain2Dao {
    private Context context;
    private Dao<Alndomain2, Integer> AlndomainDaoOpe;
    private DatabaseHelper helper;

    public Alndomain2Dao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            AlndomainDaoOpe = helper.getDao(Alndomain2.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新位置信息
     *
     * @param list
     */
    public void create(final List<Alndomain2> list) {
        try {
            deleteall();
            AlndomainDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Alndomain2 alndomain : list) {
                        AlndomainDaoOpe.createOrUpdate(alndomain);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @return
     */
    public List<Alndomain2> queryForAll() {
        try {
            return AlndomainDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 分页查询
     *
     * @return
     */
    public List<Alndomain2> queryByCount(int count,String value) {
        try {
            return AlndomainDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).where().like("value", "%" + value + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *
     */
    public void deleteall() {
        try {
            AlndomainDaoOpe.delete(queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param value
     * @return
     */
    public List<Alndomain2> queryByNum(String value) {
        try {
            return AlndomainDaoOpe.queryBuilder().where().like("value", "%" + value + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param alndomain
     * @return
     */
    public boolean isexit(Alndomain2 alndomain) {
        try {
            List<Alndomain2> workOrderList = AlndomainDaoOpe.queryBuilder().where().eq("value", alndomain.value)
                    .and().eq("description", alndomain.description).query();
            if (workOrderList.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
