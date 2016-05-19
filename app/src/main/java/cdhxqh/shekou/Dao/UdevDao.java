package cdhxqh.shekou.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import cdhxqh.shekou.OrmLiteHelper.DatabaseHelper;
import cdhxqh.shekou.model.Udev;

/**
 * Created by think on 2016/5/18.
 * 资产
 */
public class UdevDao {
    private Context context;
    private Dao<Udev, Integer> UdevDaoOpe;
    private DatabaseHelper helper;

    public UdevDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            UdevDaoOpe = helper.getDao(Udev.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新位置信息
     *
     * @param list
     */
    public void create(final List<Udev> list) {
        try {
            deleteall();
            UdevDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Udev udev : list) {
                        UdevDaoOpe.createOrUpdate(udev);
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
    public List<Udev> queryForAll() {
        try {
            return UdevDaoOpe.queryForAll();
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
    public List<Udev> queryByCount(int count,String evnum) {
        try {
            return UdevDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).where().like("evnum", "%" + evnum + "%").query();
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
            UdevDaoOpe.delete(UdevDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param evnum
     * @return
     */
    public List<Udev> queryByNum(String evnum) {
        try {
            return UdevDaoOpe.queryBuilder().where().like("evnum", "%" + evnum + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param udev
     * @return
     */
    public boolean isexit(Udev udev) {
        try {
            List<Udev> workOrderList = UdevDaoOpe.queryBuilder().where().eq("evnum", udev.evnum)
                    .and().eq("description", udev.description).query();
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
