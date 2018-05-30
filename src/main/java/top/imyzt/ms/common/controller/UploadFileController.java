package top.imyzt.ms.common.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.imyzt.ms.common.model.UploadFile;
import top.imyzt.ms.common.utils.UploadActionUtil;
import top.imyzt.ms.core.ret.RetResult;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 文件上传控制器
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */

@RestController
@RequestMapping("uploadFile")
public class UploadFileController extends BaseController{

    @PostMapping("/upload")
    public RetResult<List<UploadFile>> upload(HttpServletRequest httpServletRequest) throws IOException {

        List<UploadFile> files = UploadActionUtil.uploadFiles(httpServletRequest);
        return ok(files);
    }

}
