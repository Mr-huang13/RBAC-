package cn.wolfcode.rbac.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryObjet {
    private int currentPage = 1;//当前页
    private int pageSize = 5;//每页显示数量

    public int getStartIndex(){
        //
        return (currentPage-1)*pageSize;
    }
    //
}
