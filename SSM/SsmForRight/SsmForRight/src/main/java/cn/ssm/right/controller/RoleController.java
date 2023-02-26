package cn.ssm.right.controller;

import cn.ssm.right.http.Results;
import cn.ssm.right.po.Role;
import cn.ssm.right.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class RoleController {
    //依赖注入
    RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService){
        this.roleService = roleService;
    }

    @RequestMapping("/getRole/{id}")
    @ResponseBody
    public Results getRole(@PathVariable("id") int id){
        Role role = roleService.getRoleById(id);
        Results results = null;
        if(role != null){
            results = Results.success("success", role);
        }else{
            results = Results.fail();
        }
        return results;
    }
}
