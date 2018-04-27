package top.imyzt.ms.controller;

import io.swagger.annotations.Api;
import top.imyzt.ms.core.ret.RetResult;
import com.baomidou.mybatisplus.plugins.Page;
import top.imyzt.ms.model.SystemLog;
import top.imyzt.ms.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: imyzt
 * @date: 2018-04-27
 * @description: 系统日志表 前端控制器
 */
@Api("系统日志表")
@RestController
@RequestMapping("/system/systemLog")
public class SystemLogController extends BaseController {

    @Autowired
    private ISystemLogService systemLogService;

    /**
     * <p>
     *     获取系统日志表列表 ${abc}
     * </p>
     *
     * @author: imyzt
     * @date: 2018-04-27
     * @param keyword 查询参数
     */
    @GetMapping(value = {"/systemLog", "/list"})
    public Page<SystemLog> selectList(String keyword) {
        Page<SystemLog> defaultPage = defaultPage();
        Page<SystemLog> page = systemLogService.selectPage(defaultPage);
        return page;
    }

    /**
     * <p>
     *     查询详情
     * </p>
     *
     * @author: imyzt
     * @date: 2018-04-27
     * @param id 编号
     */
    @GetMapping(value = "/systemLog/{id}")
    public RetResult<SystemLog> selectById(@PathVariable("id") Integer id) {
        return ok(systemLogService.selectById(id));
    }

    /**
     * <p>
     *     新增系统日志表
     * </p>
     *
     * @author: imyzt
     * @date: 2018-04-27
     * @param entity 系统日志表
     */
    @PostMapping(value = "/systemLog")
    public RetResult<SystemLog> insert(SystemLog entity) {
        if (systemLogService.insert(entity)) {
            return ok("新增成功", entity);
        }
        return faln("新增失败");
    }

    /**
     * <p>
     *     删除系统日志表
     * </p>
     *
     * @author: imyzt
     * @date: 2018-04-27
     * @param id 编号
     */
    @DeleteMapping(value = "/systemLog/{id}")
    public RetResult<SystemLog> deleteById(@PathVariable("id") Integer id) {
        if (systemLogService.deleteById(id)) {
            return ok("删除成功");
        }
        return faln("删除失败");
    }

    /**
     * <p>
     *     修改系统日志表
     * </p>
     *
     * @author: imyzt
     * @date: 2018-04-27
     * @param entity 系统日志表
     */
    @PutMapping(value = "/systemLog")
    public RetResult<SystemLog> updateById(SystemLog entity) {
        if (systemLogService.updateById(entity)) {
            return ok("修改成功", entity);
        }
        return faln("修改失败");
    }



}

