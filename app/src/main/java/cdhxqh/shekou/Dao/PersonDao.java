package cdhxqh.shekou.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import cdhxqh.shekou.OrmLiteHelper.DatabaseHelper;
import cdhxqh.shekou.model.Locations;
import cdhxqh.shekou.model.Person;

/**
 * Created by think on 2016/5/18.
 * 作业计划
 */
public class PersonDao {
    private Context context;
    private Dao<Person, Integer> PersonDaoOpe;
    private DatabaseHelper helper;

    public PersonDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            PersonDaoOpe = helper.getDao(Person.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新位置信息
     *
     * @param list
     */
    public void create(final List<Person> list) {
        try {
            PersonDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Person person : list) {
                        deleteByPersonNum(person);
                        PersonDaoOpe.createOrUpdate(person);
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
    public List<Person> queryForAll() {
        try {
            return PersonDaoOpe.queryForAll();
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
    public List<Person> queryByCount(int count,String personid) {
        try {
            return PersonDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).where().like("personid", "%" + personid + "%").query();
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
            PersonDaoOpe.delete(queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param personid
     * @return
     */
    public List<Person> queryByNum(String personid) {
        try {
            return PersonDaoOpe.queryBuilder().where().like("personid", "%" + personid + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param person
     * @return
     */
    public boolean isexit(Person person) {
        try {
            List<Person> workOrderList = PersonDaoOpe.queryBuilder().where().eq("jpnum", person.personid)
                    .and().eq("displayname", person.displayname).query();
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
    private void deleteByPersonNum(Person person) {
        try {
            List<Person> personList = PersonDaoOpe.queryBuilder().where().eq("personid", person.personid).query();

            if (null != personList && personList.size() != 0) {
                PersonDaoOpe.delete(personList.get(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }





}
