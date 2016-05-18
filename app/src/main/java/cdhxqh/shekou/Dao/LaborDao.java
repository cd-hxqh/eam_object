package cdhxqh.shekou.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import cdhxqh.shekou.OrmLiteHelper.DatabaseHelper;
import cdhxqh.shekou.model.Labor;

/**
 * Created by think on 2016/5/18.
 * 员工
 */
public class LaborDao {
    private Context context;
    private Dao<Labor, Integer> LaborDaoOpe;
    private DatabaseHelper helper;

    public LaborDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            LaborDaoOpe = helper.getDao(Labor.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新位置信息
     *
     * @param list
     */
    public void create(final List<Labor> list) {
        try {
            deleteall();
            LaborDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Labor labor : list) {
                        LaborDaoOpe.createOrUpdate(labor);
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
    public List<Labor> queryForAll() {
        try {
            return LaborDaoOpe.queryForAll();
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
    public List<Labor> queryByCount(int count,String laborcode) {
        try {
            return LaborDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).where().like("laborcode", "%" + laborcode + "%").query();
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
    public List<Labor> queryByCount1(int count,String laborcode,String udeq1) {
        try {
            return LaborDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).where().like("laborcode", "%" + laborcode + "%").and().eq("udeq1",udeq1).query();
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
            LaborDaoOpe.delete(queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param laborcode
     * @return
     */
    public List<Labor> queryByNum(String laborcode) {
        try {
            return LaborDaoOpe.queryBuilder().where().like("laborcode", "%" + laborcode + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param labor
     * @return
     */
    public boolean isexit(Labor labor) {
        try {
            List<Labor> workOrderList = LaborDaoOpe.queryBuilder().where().eq("laborcode", labor.laborcode)
                    .and().eq("displayname", labor.displayname).query();
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
