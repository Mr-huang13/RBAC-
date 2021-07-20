package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.query.QueryObjet;

import java.util.List;

public interface PermissionMapper {
    List<Permission> selectAll();
    void deleteById(Long id);
    int queryForCount();
    List<Permission> queryForData(QueryObjet qo);

    void insert(Permission permission);

    void deleteAll();

    List<String> selectExpressionsByEmpId(Long id);
}
