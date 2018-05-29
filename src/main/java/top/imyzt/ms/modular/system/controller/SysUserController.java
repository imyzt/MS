package top.imyzt.ms.modular.system.controller;

import io.swagger.annotations.Api;
import top.imyzt.ms.core.ret.RetResult;
import com.baomidou.mybatisplus.plugins.Page;
import top.imyzt.ms.modular.system.model.SysUser;
import top.imyzt.ms.modular.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.imyzt.ms.common.controller.BaseController;

/**
 * <p>
 *     用户信息 前端控制器
 * </p>
 *
 * @author: imyzt
 * @date: 2018-05-29
*/
@Api(value = "用户信息", description = "用户信息")
@RestController
@RequestMapping("/system/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * <p>
     *     获取用户信息列表
     * </p>
     *
     * @author: imyzt
     * @date: 2018-05-29
     * @param keyword 查询参数
     */
    @GetMapping(value = {"/", "/list"})
    public Page<SysUser> selectList(String keyword) {
        Page<SysUser> defaultPage = defaultPage();
        Page<SysUser> page = sysUserService.selectPage(defaultPage);
        return page;
    }

    /**
     * <p>
     *     查询详情
     * </p>
     *
     * @author: imyzt
     * @date: 2018-05-29
     * @param id 编号
     */
    @GetMapping(value = "/{id}")
    public RetResult<SysUser> selectById(@PathVariable("id") Integer id) {
        return ok(sysUserService.selectById(id));
    }

    /**
     * <p>
     *     新增用户信息
     * </p>
     *
     * @author: imyzt
     * @date: 2018-05-29
     * @param sysUser 用户信息
     */
    @PostMapping(value = "/")
    public RetResult<SysUser> insert(SysUser sysUser) {
        if (sysUserService.insert(sysUser)) {
            return ok("新增成功", sysUser);
        }
        return faln("新增失败");
    }

    /**
     * <p>
     *     删除用户信息
     * </p>
     *
     * @author: imyzt
     * @date: 2018-05-29
     * @param id 编号
     */
    @DeleteMapping(value = "/{id}")
    public RetResult<SysUser> deleteById(@PathVariable("id") Integer id) {
        if (sysUserService.deleteById(id)) {
            return ok("删除成功");
        }
        return faln("删除失败");
    }

    /**
     * <p>
     *     修改用户信息
     * </p>
     *
     * @author: imyzt
     * @date: 2018-05-29
     * @param sysUser 用户信息
     */
    @PutMapping(value = "/")
    public RetResult<SysUser> updateById(SysUser sysUser) {
        if (sysUserService.updateById(sysUser)) {
            return ok("修改成功", sysUser);
        }
        return faln("修改失败");
    }



}

