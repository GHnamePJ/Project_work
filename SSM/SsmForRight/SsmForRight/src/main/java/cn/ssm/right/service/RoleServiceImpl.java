package cn.ssm.right.service;

import cn.ssm.right.mapper.RoleMapper;
import cn.ssm.right.po.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    //依赖注入
    RoleMapper roleMapper;

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper){
        this.roleMapper = roleMapper;
    }

    @Override
    public Role getRoleById(int id) {
        Role role = roleMapper.getRoleById(id);
        return role;
    }
}
