package cdhxqh.shekou.Dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import cdhxqh.shekou.OrmLiteHelper.DatabaseHelper;
import cdhxqh.shekou.model.Item;
import cdhxqh.shekou.model.Locations;

/**
 * Created by think on 2016/5/18.
 */
public class ItemDao {

    private static final String TAG = "ItemDao";
    private Context context;
    private Dao<Item, Integer> itemDao;
    private DatabaseHelper helper;

    public ItemDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            itemDao = helper.getDao(Item.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新备件信息
     *
     * @param list
     */
    public void create(final List<Item> list) {
        try {
            itemDao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Item item : list) {
                        deleteByItemNum(item);
                        itemDao.createOrUpdate(item);
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
    public List<Item> queryForAll() {
        try {
            return itemDao.queryForAll();
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
    public List<Item> queryByCount(int count, String itemnum) {
        try {
            return itemDao.queryBuilder().offset((count - 1) * 20).limit(20).where().like("ITEMNUM", "%" + itemnum + "%").query();
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
    public List<Item> queryByCount1(int count, String item) {
        try {
            return itemDao.queryBuilder().offset((count - 1) * 20).limit(20).
                    where().like("ITEMNUM", "%" + item + "%").or().eq("description", "%" + item + "%").query();
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
            itemDao.delete(queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    public List<Item> queryByNum(String itemnum) {
        try {
            return itemDao.queryBuilder().where().like("ITEMNUM", "%" + itemnum + "%").or().like("description", "%" + itemnum + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return
     */
    public boolean isexit(Item item) {
        try {
            List<Item> locationList = itemDao.queryBuilder().where().eq("ITEMNUM", item.itemnum)
                    .and().eq("description", item.description).query();
            if (locationList.size() > 0) {
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
    private void deleteByItemNum(Item item) {
        try {
            List<Item> items = itemDao.queryBuilder().where().eq("ITEMNUM", item.itemnum).query();

            if (null != items && items.size() != 0) {
                itemDao.delete(items.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
