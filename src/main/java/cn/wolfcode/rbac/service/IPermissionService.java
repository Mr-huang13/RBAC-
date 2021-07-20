package cn.wolfcode.rbac.service;

import cn.wolfcode.rbac.domain.Permission;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObjet;

import java.util.List;

public interface IPermissionService {
    List<Permission> selectAll();
    void deleteById(Long id);

    PageResult query(QueryObjet qo);

    void reload();
}
