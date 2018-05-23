package top.imyzt.ms.modular.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.imyzt.ms.common.controller.BaseController;
import top.imyzt.ms.core.ret.RetResult;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */
@RestController
@RequestMapping("/image")
public class Image extends BaseController {

    @GetMapping("/test")
    public RetResult<Object> Test(String word){
        System.out.println(word);
        return ok(word);
    }
}
