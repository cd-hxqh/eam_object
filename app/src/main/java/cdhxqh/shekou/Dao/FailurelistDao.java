package cdhxqh.shekou.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import cdhxqh.shekou.OrmLiteHelper.DatabaseHelper;
import cdhxqh.shekou.model.Alndomain;
import cdhxqh.shekou.model.Failurelist;

/**
 * Created by think on 2016/5/18.
 * 预防性维护
 */
public class FailurelistDao {
    private Context context;
    private Dao<Failurelist, Integer> FailurelistDaoOpe;
    private DatabaseHelper helper;

    public FailurelistDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            FailurelistDaoOpe = helper.getDao(Failurelist.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新位置信息
     *
     * @param list
     */
    public void create(final List<Failurelist> list) {
        try {
            FailurelistDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Failurelist failurelist : list) {
                        deleteByFailurelistNum(failurelist);
                        FailurelistDaoOpe.createOrUpdate(failurelist);
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
    public List<Failurelist> queryForAll() {
        try {
            return FailurelistDaoOpe.queryForAll();
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
    public List<Failurelist> queryByCount(int count, String failurecode, String parent) {
        try {
            return FailurelistDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).where().like("failurecode", "%" + failurecode + "%")
                    .and().eq("parent", parent).query();
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
    public List<Failurelist> queryByFailurelist(int count, String failurecode, String type) {
        try {
            return FailurelistDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).where().like("failurecode", "%" + failurecode + "%")
                    .and().eq("type", type).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String queryForParent(String failurecode) {
        try {
            if (FailurelistDaoOpe.queryBuilder().where().eq("failurecode", failurecode).query().size() > 0) {
                return FailurelistDaoOpe.queryBuilder().where().eq("failurecode", failurecode).queryForFirst().failurelist;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String queryForParent(String failurecode, String parent) {
        try {
            return FailurelistDaoOpe.queryBuilder().where().eq("failurecode", failurecode).and().eq("parent", parent).queryForFirst().failurelist;
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
            FailurelistDaoOpe.delete(FailurelistDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param failurecode
     * @return
     */
    public List<Failurelist> queryByNum(String failurecode) {
        try {
            return FailurelistDaoOpe.queryBuilder().where().like("failurecode", "%" + failurecode + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param failurelist
     * @return
     */
    public boolean isexit(Failurelist failurelist) {
        try {
            List<Failurelist> workOrderList = FailurelistDaoOpe.queryBuilder().where().eq("failurecode", failurelist.failurecode)
                    .and().eq("description", failurelist.description).query();
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
    private void deleteByFailurelistNum(Failurelist failurelist) {
        try {
            List<Failurelist> failurelistList = FailurelistDaoOpe.queryBuilder().where().eq("failurecode", failurelist.failurecode).query();

            if (null != failurelistList && failurelistList.size() != 0) {
                FailurelistDaoOpe.delete(failurelistList.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
