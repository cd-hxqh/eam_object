package cdhxqh.shekou.Dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import cdhxqh.shekou.OrmLiteHelper.DatabaseHelper;
import cdhxqh.shekou.model.JobPlan;

/**
 * Created by think on 2015/12/28.
 * 作业计划
 */
public class JobPlanDao {
    private Context context;
    private Dao<JobPlan, Integer> JobPlanDaoOpe;
    private DatabaseHelper helper;

    public JobPlanDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            JobPlanDaoOpe = helper.getDao(JobPlan.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新位置信息
     *
     * @param list
     */
    public void create(final List<JobPlan> list) {
        try {
            deleteall();
            JobPlanDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (JobPlan jpnum : list) {
                        JobPlanDaoOpe.createOrUpdate(jpnum);
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
    public List<JobPlan> queryForAll() {
        try {
            return JobPlanDaoOpe.queryForAll();
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
    public List<JobPlan> queryByCount(int count,String jpnum) {
        try {
            return JobPlanDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).where().like("jpnum", "%" + jpnum + "%").query();
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
    public List<JobPlan> queryByCount1(int count,String jpnum) {
        try {
            return JobPlanDaoOpe.queryBuilder().offset((count - 1) * 20).limit(20).
                    where().like("jpnum", "%" + jpnum + "%").and().eq("UDASSETTYPE","AC").query();
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
            JobPlanDaoOpe.delete(queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param jpnum
     * @return
     */
    public List<JobPlan> queryByNum(String jpnum) {
        try {
            return JobPlanDaoOpe.queryBuilder().where().like("jpnum", "%" + jpnum + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param jobPlan
     * @return
     */
    public boolean isexit(JobPlan jobPlan) {
        try {
            List<JobPlan> workOrderList = JobPlanDaoOpe.queryBuilder().where().eq("jpnum", jobPlan.jpnum)
                    .and().eq("description", jobPlan.description).query();
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
