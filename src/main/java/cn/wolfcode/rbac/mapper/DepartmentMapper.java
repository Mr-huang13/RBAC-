package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.query.QueryObjet;

import java.util.List;

public interface DepartmentMapper {
    List<Department> selectAll();
    void deleteById(Long id);
    void saceDepartment(Department department);
    void updateByid(Department department);

    Department getById(Long id);

    int queryForCount();

    List<Department> queryForData(QueryObjet qo);
}
