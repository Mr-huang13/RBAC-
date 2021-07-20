package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObjet;

import java.util.List;

public interface IRoleSercice {
    List<Role> selectAll();
    void deleteById(Long id);
    void saveOrUpdate(Role role,Long[] permissionIds);
    Role getById(Long id);
    PageResult query(QueryObjet qo);
}
