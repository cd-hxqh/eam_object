package cdhxqh.shekou.OrmLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cdhxqh.shekou.model.Alndomain;
import cdhxqh.shekou.model.Alndomain2;
import cdhxqh.shekou.model.Assets;
import cdhxqh.shekou.model.Failurelist;
import cdhxqh.shekou.model.Item;
import cdhxqh.shekou.model.JobPlan;
import cdhxqh.shekou.model.Labor;
import cdhxqh.shekou.model.Laborcraftrate;
import cdhxqh.shekou.model.Locations;
import cdhxqh.shekou.model.Person;
import cdhxqh.shekou.model.Pm;
import cdhxqh.shekou.model.Projappr;
import cdhxqh.shekou.model.Udev;
import cdhxqh.shekou.utils.DataUtils;

/**
 * Created by think on 2015/12/23.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final int DATABASE_VERSION = 17;
    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private DatabaseHelper(Context context) {

        super(context, DataUtils.getFilePath(context), null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Assets.class);
            TableUtils.createTable(connectionSource, JobPlan.class);
            TableUtils.createTable(connectionSource, Person.class);
            TableUtils.createTable(connectionSource, Labor.class);
            TableUtils.createTable(connectionSource, Alndomain.class);
            TableUtils.createTable(connectionSource, Udev.class);
            TableUtils.createTable(connectionSource, Projappr.class);
            TableUtils.createTable(connectionSource, Pm.class);
            TableUtils.createTable(connectionSource, Laborcraftrate.class);
            TableUtils.createTable(connectionSource, Failurelist.class);
            TableUtils.createTable(connectionSource, Alndomain2.class);
            TableUtils.createTable(connectionSource, Locations.class);
            TableUtils.createTable(connectionSource, Item.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Assets.class, true);
            TableUtils.dropTable(connectionSource, JobPlan.class, true);
            TableUtils.dropTable(connectionSource, Person.class, true);
            TableUtils.dropTable(connectionSource, Labor.class, true);
            TableUtils.dropTable(connectionSource, Alndomain.class, true);
            TableUtils.dropTable(connectionSource, Udev.class, true);
            TableUtils.dropTable(connectionSource, Projappr.class, true);
            TableUtils.dropTable(connectionSource, Pm.class, true);
            TableUtils.dropTable(connectionSource, Laborcraftrate.class, true);
            TableUtils.dropTable(connectionSource, Failurelist.class, true);
            TableUtils.dropTable(connectionSource, Alndomain2.class, true);
            TableUtils.dropTable(connectionSource, Locations.class, true);
            TableUtils.dropTable(connectionSource, Item.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper instance;


    /**
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }

        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            try {
                dao = super.getDao(clazz);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     *
     */
    @Override
    public void close() {
        super.close();

        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }

}
