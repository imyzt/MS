package top.imyzt.ms.controller;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.imyzt.ms.core.ret.RetResult;
import top.imyzt.ms.model.UserInfo;
import top.imyzt.ms.service.UserInfoService;

import java.util.List;

/**
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/4/26 09:34
 * @description: 用户信息基本操作的控制层
 */
@RestController
@Api("用户操作")
public class UserInfoController extends BaseController{

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("user/{id}")
    public RetResult<UserInfo> selUserById(@PathVariable("id") Integer id) {
       return ok(userInfoService.selectById(id));
    }

    @PutMapping("user/{id}")
    public RetResult<Integer> exception(@PathVariable("id") Integer id) {
        int i = 0;

        i = 12 / 0;
        return ok(i);
    }

    @PostMapping("user")
    public Page<UserInfo> selList() {

        Page<UserInfo> page = defaultPage();
        List<UserInfo> userInfos = userInfoService.selList(page);
        return page.setRecords(userInfos);
    }

}
