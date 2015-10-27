package cdhxqh.shekou.bean;


import java.io.Serializable;

/**
 * Created by apple on 15/9/10.
 * 解析结果
 */

public class Results implements Serializable {
    private int id;
    /**
     * 当前页*
     */
    private int curpage;
    /**
     * 总共条数*
     */
    private String totalresult;
    /**
     * 返回结果*
     */
    private String resultlist;
    /**
     * 总共页数*
     */
    private String totalpage;
    /**
     * 显示条数*
     */
    private int showcount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurpage() {
        return curpage;
    }

    public void setCurpage(int curpage) {
        this.curpage = curpage;
    }

    public String getTotalresult() {
        return totalresult;
    }

    public void setTotalresult(String totalresult) {
        this.totalresult = totalresult;
    }

    public String getResultlist() {
        return resultlist;
    }

    public void setResultlist(String resultlist) {
        this.resultlist = resultlist;
    }

    public String getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(String totalpage) {
        this.totalpage = totalpage;
    }

    public int getShowcount() {
        return showcount;
    }

    public void setShowcount(int showcount) {
        this.showcount = showcount;
    }
}
