package com.mos.eboot.api.platform.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.access.annotation.Secured;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mos.eboot.api.jwt.common.constant.RoleConstants;
import com.mos.eboot.api.jwt.common.exception.BaseException;
import com.mos.eboot.api.platform.api.ISysUserService;
import com.mos.eboot.platform.entity.SysUser;
import com.mos.eboot.tools.result.ResultModel;

import io.swagger.annotations.ApiOperation;

/**
 * 用户控制层
 * 跳过了server层，以及简单的参数校验
 * @author 周恒
 * @date 20180831 16:28:52
 * @since v1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private ISysUserService sysUserService;

    /**
     * 获取所有用户
     *
     * @return 返回 map 描述此返回参数
     * @author 周恒
     * @date 20180831 16:29:58
     * @since v1.0
     */
	@ApiOperation(value = "获取所有用户信息", notes = "获取所有用户信息")
    @GetMapping("/queryAll")
    public ResultModel<List<SysUser>> queryAll(){
    	SysUser user=new SysUser();
    	user.setIsDel("0");
    	ResultModel<List<SysUser>> users = sysUserService.all(user);
        return users;
    }

    /**
     * 测试USER和ADMIN角色可以访问该接口
     * 根据id获取指定用户
     *
     * @param id 描述此参数
     * @return 返回 user 描述此返回参数
     * @author 周恒
     * @date 20180903 17:42:28
     * @since v1.0
     */
//    @Secured({RoleConstants.USER,RoleConstants.ADMIN})
//    @GetMapping("/queryById")
//    public SysUser queryById(Integer id){
//    	SysUser user = sysUserService.findById(id);
//        return user;
//    }

    /**
     * 测试只有ADMIN角色可以访问该接口
     * 根据姓名获取用户信息
     *
     * @param userName 描述此参数
     * @return 返回 user 描述此返回参数
     * @author 周恒
     * @date 20180903 17:41:18
     * @since v1.0
     */
    @Secured({RoleConstants.ADMIN})
    @GetMapping("/queryByUserName")
    public SysUser userList(String userName){
    	SysUser user = sysUserService.getByUsername(userName);
        return user;
    }

    /**
     * 用户注册
     *
     * @param user 描述此参数
     * @return 返回 user 描述此返回参数
     * @author 周恒
     * @date 20180831 16:30:38
     * @since v1.0
     */
    @PostMapping("/signUp")
    public ResultModel<String> signUp(@RequestBody SysUser user) {
    	SysUser bizUser = sysUserService.getByUsername(user.getUsername());
        if(null != bizUser){
            throw new BaseException("用户已经存在");
        }
        //密码加密并保存返回用户信息
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword()).getBytes()));
        return sysUserService.saveOrUpdate(user);
    }

}
