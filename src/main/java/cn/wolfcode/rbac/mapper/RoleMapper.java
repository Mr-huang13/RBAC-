package cn.wolfcode.rbac.mapper;

import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.domain.Role;
import cn.wolfcode.rbac.query.QueryObjet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    List<Role> selectAll();
    void deleteById(Long id);
    void saceRole(Role role);
    void updateById(Role role);
    Role getById(Long id);
    int queryForCount();

    List<Role> queryForData(QueryObjet qo);

    void deleteRoleAndPermissionRelation(Long id);

    void saceRoleAndPermissionRelation(@Param("id") Long id, @Param("permissionId") Long permissionId);
}
