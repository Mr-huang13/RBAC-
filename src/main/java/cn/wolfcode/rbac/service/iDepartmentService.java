package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObjet;

import java.util.List;

public interface iDepartmentService {
    List<Department> selectAll();
    void deleteById(Long id);
    void saveOrUpdate(Department department);

    Department getById(Long id);


    PageResult query(QueryObjet qo);
}
