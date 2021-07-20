package cn.wolfcode.rbac.service.impl;
import cn.wolfcode.rbac.domain.Department;
import cn.wolfcode.rbac.mapper.DepartmentMapper;
import cn.wolfcode.rbac.query.PageResult;
import cn.wolfcode.rbac.query.QueryObjet;
import cn.wolfcode.rbac.service.iDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServicelmpl implements iDepartmentService{
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> selectAll(){
        return departmentMapper.selectAll();
    }
    public void deleteById(Long id){
        departmentMapper.deleteById(id);
    }

    public void saveOrUpdate(Department department){
        //如果传的参数中包含ID，修改
        //无ID，添加
        if(department.getId()!=null){
            departmentMapper.updateByid(department);
        }else {
            departmentMapper.saceDepartment(department);
        }
    }

    @Override
    public Department getById(Long id) {
        return departmentMapper.getById(id);
    }

    public PageResult query(QueryObjet qo){
        //带有分页效果的数据集，总条数
        int totalCount = departmentMapper.queryForCount();
        if(totalCount != 0){
            List<Department> data = departmentMapper.queryForData(qo);
            return new PageResult(data,totalCount,qo.getCurrentPage(),qo.getPageSize());//全参构造器，有数据的情况
        }
        return new PageResult(qo.getCurrentPage(),qo.getPageSize());//缺参构造器，没有满足条件的数据的情况
    }

}
