package cdhxqh.shekou.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import cdhxqh.shekou.OrmLiteHelper.DatabaseHelper;
import cdhxqh.shekou.model.Person;
import cdhxqh.shekou.model.Projappr;

/**
 * Created by think on 2016/5/18.
 * 资产
 */
public class ProjapprDao {
    private Context context;
    private Dao<Projappr, Integer> ProjapprDaoOpe;
    private DatabaseHelper helper;

    public ProjapprDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            ProjapprDaoOpe = helper.getDao(Projappr.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新位置信息
     *
     * @param list
     */
    public void create(final List<Projappr> list) {
        try {
            ProjapprDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Projappr projappr : list) {
                        deleteByProjapprNum(projappr);
                        ProjapprDaoOpe.createOrUpdate(projappr);
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
    public List<Projappr> queryForAll() {
        try {
            return ProjapprDaoOpe.queryForAll();
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
    public List<Projappr> queryByCount(int count,String prjnum) {
        try {
            return ProjapprDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).where().like("prjnum", "%" + prjnum + "%").query();
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
            ProjapprDaoOpe.delete(ProjapprDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param prjnum
     * @return
     */
    public List<Projappr> queryByNum(String prjnum) {
        try {
            return ProjapprDaoOpe.queryBuilder().where().like("prjnum", "%" + prjnum + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param projappr
     * @return
     */
    public boolean isexit(Projappr projappr) {
        try {
            List<Projappr> workOrderList = ProjapprDaoOpe.queryBuilder().where().eq("prjnum", projappr.prjnum)
                    .and().eq("description", projappr.description).query();
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
    private void deleteByProjapprNum(Projappr projappr) {
        try {
            List<Projappr> projapprList = ProjapprDaoOpe.queryBuilder().where().eq("prjnum", projappr.prjnum).query();

            if (null != projapprList && projapprList.size() != 0) {
                ProjapprDaoOpe.delete(projapprList.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }




}
