package cdhxqh.shekou.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import cdhxqh.shekou.OrmLiteHelper.DatabaseHelper;
import cdhxqh.shekou.model.Assets;

/**
 * Created by think on 2015/12/28.
 * 资产
 */
public class AssetDao {
    private Context context;
    private Dao<Assets, Integer> AssetDaoOpe;
    private DatabaseHelper helper;

    public AssetDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            AssetDaoOpe = helper.getDao(Assets.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新位置信息
     *
     * @param list
     */
    public void create(final List<Assets> list) {
        try {
            deleteall();
            AssetDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Assets location : list) {
                        AssetDaoOpe.createOrUpdate(location);
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
    public List<Assets> queryForAll() {
        try {
            return AssetDaoOpe.queryBuilder().limit(2000).query();
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
    public List<Assets> queryByCount(int count,String assetnum) {
        try {
            return AssetDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).where().like("assetnum", "%" + assetnum + "%").query();
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
            AssetDaoOpe.delete(AssetDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param assetnum
     * @return
     */
    public List<Assets> queryByNum(String assetnum) {
        try {
            return AssetDaoOpe.queryBuilder().where().like("assetnum", "%" + assetnum + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param assets
     * @return
     */
    public boolean isexit(Assets assets) {
        try {
            List<Assets> workOrderList = AssetDaoOpe.queryBuilder().where().eq("assetnum", assets.assetnum)
                    .and().eq("description", assets.description).query();
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
