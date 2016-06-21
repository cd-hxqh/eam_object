package cdhxqh.shekou.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import cdhxqh.shekou.OrmLiteHelper.DatabaseHelper;
import cdhxqh.shekou.model.Alndomain;
import cdhxqh.shekou.model.Alndomain2;

/**
 * Created by think on 2016/5/18.
 * 作业计划
 */
public class AlndomainDao {
    private Context context;
    private Dao<Alndomain, Integer> AlndomainDaoOpe;
    private DatabaseHelper helper;

    public AlndomainDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            AlndomainDaoOpe = helper.getDao(Alndomain.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新位置信息
     *
     * @param list
     */
    public void create(final List<Alndomain> list) {
        try {
            deleteall();
            AlndomainDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Alndomain alndomain : list) {
                        deleteByAlndomainNum(alndomain);
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
    public List<Alndomain> queryForAll() {
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
    public List<Alndomain> queryByCount(int count,String value) {
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
    public List<Alndomain> queryByNum(String value) {
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
    public boolean isexit(Alndomain alndomain) {
        try {
            List<Alndomain> workOrderList = AlndomainDaoOpe.queryBuilder().where().eq("value", alndomain.value)
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


    /**
     * 根据编号删除信息*
     */
    private void deleteByAlndomainNum(Alndomain alndomain) {
        try {
            List<Alndomain> alndomainList = AlndomainDaoOpe.queryBuilder().where().eq("value", alndomain.value).query();

            if (null != alndomainList && alndomainList.size() != 0) {
                AlndomainDaoOpe.delete(alndomainList.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
