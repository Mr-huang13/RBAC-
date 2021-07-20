package cn.wolfcode.rbac.query;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class PageResult {
    private List data;//数据集
    private int totalCount;//总条数

    private int prevPage;//上一页
    private int nextPage;//下一页
    private int totalPage;//总页数

    private int currentPage;//当前页
    private int pageSize;//每页显示数量

    //全参构造器
    public PageResult(List data,int totalCount,int currentPage,int pageSize){
        this.data=data;

        this.totalCount=totalCount;

        this.totalPage = totalCount%pageSize==0 ? totalCount/pageSize : totalCount/pageSize+1;
        this.prevPage = currentPage>1 ? currentPage-1 : 1;
        this.nextPage = currentPage<this.totalPage ? currentPage+1 : this.totalPage;

        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    //缺参构造器
    public PageResult(int currentPage,int pageSize){
        this.data = Collections.EMPTY_LIST;//给一个空的集合
        this.totalCount = 0;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
}

