package cdhxqh.shekou.Dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import cdhxqh.shekou.OrmLiteHelper.DatabaseHelper;
import cdhxqh.shekou.model.JobPlan;
import cdhxqh.shekou.model.Locations;

/**
 * Created by think on 2016/5/18.
 * 位置
 */
public class LocationDao {

    private static final String TAG="LocationDao";
    private Context context;
    private Dao<Locations, Integer> locationsesDao;
    private DatabaseHelper helper;

    public LocationDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            locationsesDao = helper.getDao(Locations.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新位置信息
     *
     * @param list
     */
    public void create(final List<Locations> list) {
        try {
            deleteall();
            locationsesDao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Locations locations : list) {
                        deleteByLocationNum(locations);
                        locationsesDao.createOrUpdate(locations);
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
    public List<Locations> queryForAll() {
        try {
            return locationsesDao.queryForAll();
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
    public List<Locations> queryByCount(int count, String jpnum) {
        try {
            return locationsesDao.queryBuilder().offset((count - 1) * 20).limit(20).where().like("location", "%" + jpnum + "%").query();
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
    public List<Locations> queryByCount1(int count, String location) {
        try {
            return locationsesDao.queryBuilder().offset((count - 1) * 20).limit(20).
                    where().like("location", "%" + location + "%").and().eq("description", "AC").query();
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
            locationsesDao.delete(queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    public List<Locations> queryByNum(String location) {
        try {
            return locationsesDao.queryBuilder().where().like("location", "%" + location + "%").or().like("description", "%" + location + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return
     */
    public boolean isexit(Locations locations) {
        try {
            List<Locations> locationList = locationsesDao.queryBuilder().where().eq("location", locations.location)
                    .and().eq("description", locations.description).query();
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
     * 根据类型查询库房信息
     */
    public List<Locations> findByLocations(String type, String value) {
        Log.i(TAG,"type="+type+"value="+value);
        try {
            List<Locations> locationList = locationsesDao.queryBuilder().where().eq("type", type).query();
//            List<Locations> locationList = locationsesDao.queryBuilder().where().eq("type", type).and().eq("location", value)
//                    .or().eq("description", value).query();
            return locationList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据编号删除信息*
     */
    private void deleteByLocationNum(Locations locations) {
        try {
            List<Locations> locationsList = locationsesDao.queryBuilder().where().eq("locationsid", locations.locationsid).query();

            if (null != locationsList && locationsList.size() != 0) {
                locationsesDao.delete(locationsList.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



}
