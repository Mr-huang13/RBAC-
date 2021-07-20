package cn.wolfcode.rbac.service.impl;

import cn.wolfcode.rbac.domain.Role;

import cn.wolfcode.rbac.mapper.RoleMapper;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObjet;
import cn.wolfcode.rbac.service.IRoleSercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServicelmp implements IRoleSercice {
    @Autowired
    private RoleMapper roleMapper;
    public List<Role> selectAll(){
        return roleMapper.selectAll();
    }
    public void deleteById(Long id){
        roleMapper.deleteById(id);
    }
    public void saveOrUpdate(Role role,Long[] permissionIds){
        if(role.getId()!=null){
            roleMapper.updateById(role);
        }else {
            roleMapper.saceRole(role);
        }
        //删除角色和权限的旧关系
        roleMapper.deleteRoleAndPermissionRelation(role.getId());
        //保存角色权限的新关系
        if(permissionIds!=null){
            //遍历permissionIds
            for (Long permissionId : permissionIds) {
                roleMapper.saceRoleAndPermissionRelation(role.getId(),permissionId);
            }
        }
    }
    @Override
    public Role getById(Long id) {
        return roleMapper.getById(id);
    }

    public PageResult query(QueryObjet qo){
        //带有分页效果的数据集，总条数
        int totalCount = roleMapper.queryForCount();
        if(totalCount != 0){
            List<Role> data = roleMapper.queryForData(qo);
            return new PageResult(data,totalCount,qo.getCurrentPage(),qo.getPageSize());//全参构造器，有数据的情况
        }
        return new PageResult(qo.getCurrentPage(),qo.getPageSize());//缺参构造器，没有满足条件的数据的情况
    }
}
