package cdhxqh.shekou.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import cdhxqh.shekou.OrmLiteHelper.DatabaseHelper;
import cdhxqh.shekou.model.Person;
import cdhxqh.shekou.model.Pm;

/**
 * Created by think on 2016/5/18.
 * 预防性维护
 */
public class PmDao {
    private Context context;
    private Dao<Pm, Integer> PmDaoOpe;
    private DatabaseHelper helper;

    public PmDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            PmDaoOpe = helper.getDao(Pm.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新位置信息
     *
     * @param list
     */
    public void create(final List<Pm> list) {
        try {
            PmDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Pm pm : list) {
                        deleteByPmNum(pm);
                        PmDaoOpe.createOrUpdate(pm);
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
    public List<Pm> queryForAll() {
        try {
            return PmDaoOpe.queryForAll();
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
    public List<Pm> queryByCount(int count,String pmnum) {
        try {
            return PmDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).where().like("pmnum", "%" + pmnum + "%").query();
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
            PmDaoOpe.delete(PmDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param pmnum
     * @return
     */
    public List<Pm> queryByNum(String pmnum) {
        try {
            return PmDaoOpe.queryBuilder().where().like("pmnum", "%" + pmnum + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param pm
     * @return
     */
    public boolean isexit(Pm pm) {
        try {
            List<Pm> workOrderList = PmDaoOpe.queryBuilder().where().eq("pmnum", pm.pmnum)
                    .and().eq("description", pm.description).query();
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


    /**
     * 根据编号删除信息*
     */
    private void deleteByPmNum(Pm pm) {
        try {
            List<Pm> pmList = PmDaoOpe.queryBuilder().where().eq("pmnum", pm.pmnum).query();

            if (null != pmList && pmList.size() != 0) {
                PmDaoOpe.delete(pmList.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
