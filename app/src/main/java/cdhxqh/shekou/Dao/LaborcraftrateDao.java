package cdhxqh.shekou.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import cdhxqh.shekou.OrmLiteHelper.DatabaseHelper;
import cdhxqh.shekou.model.Laborcraftrate;

/**
 * Created by think on 2016/5/18.
 * 预防性维护
 */
public class LaborcraftrateDao {
    private Context context;
    private Dao<Laborcraftrate, Integer> LaborcraftrateDaoOpe;
    private DatabaseHelper helper;

    public LaborcraftrateDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            LaborcraftrateDaoOpe = helper.getDao(Laborcraftrate.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新位置信息
     *
     * @param list
     */
    public void create(final List<Laborcraftrate> list) {
        try {
            deleteall();
            LaborcraftrateDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Laborcraftrate laborcraftrate : list) {
                        LaborcraftrateDaoOpe.createOrUpdate(laborcraftrate);
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
    public List<Laborcraftrate> queryForAll() {
        try {
            return LaborcraftrateDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return
     */
    public List<Laborcraftrate> queryForsite() {
        try {
            return LaborcraftrateDaoOpe.queryBuilder().where().eq("LOCATIONSITE","CCT").query();
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
    public List<Laborcraftrate> queryByCount(int count,String laborcode,String locationsite) {
        try {
            return LaborcraftrateDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).where().like("laborcode", "%" + laborcode + "%")
                    .and().eq("LOCATIONSITE", locationsite).query();
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
            LaborcraftrateDaoOpe.delete(LaborcraftrateDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param laborcode
     * @return
     */
    public List<Laborcraftrate> queryByNum(String laborcode) {
        try {
            return LaborcraftrateDaoOpe.queryBuilder().where().like("laborcode", "%" + laborcode + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param laborcraftrate
     * @return
     */
    public boolean isexit(Laborcraftrate laborcraftrate) {
        try {
            List<Laborcraftrate> workOrderList = LaborcraftrateDaoOpe.queryBuilder().where().eq("laborcode", laborcraftrate.laborcode)
                    .and().eq("displayname", laborcraftrate.displayname).query();
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
