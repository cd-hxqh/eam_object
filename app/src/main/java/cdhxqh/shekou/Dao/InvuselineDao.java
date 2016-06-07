package cdhxqh.shekou.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import cdhxqh.shekou.OrmLiteHelper.DatabaseHelper;
import cdhxqh.shekou.model.Assets;
import cdhxqh.shekou.model.Invuseline;

/**
 * Created by think on 2016/5/18.
 * 资产
 */
public class InvuselineDao {
    private Context context;
    private Dao<Invuseline, Integer> InvuselineDaoOpe;
    private DatabaseHelper helper;

    public InvuselineDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            InvuselineDaoOpe = helper.getDao(Invuseline.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param list
     */
    public void create(final List<Invuseline> list) {
        try {
            InvuselineDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Invuseline invuseline : list) {
                        InvuselineDaoOpe.createOrUpdate(invuseline);
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
    public List<Invuseline> queryForAll() {
        try {
            return InvuselineDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据领料单编号查询备件行信息
     *
     * @return
     */
    public List<Invuseline> queryByNum(String invusenum) {
        try {
            return InvuselineDaoOpe.queryBuilder().where().eq("invusenum", invusenum).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
