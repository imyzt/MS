package top.imyzt.ms.common.utils;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import top.imyzt.ms.common.model.UploadFile;
import top.imyzt.ms.core.constant.GlobalConstant;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 文件上传工具类
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */
public class UploadActionUtil {


    /**
     * 上传文件工具类,将文件存入指定路径下
     * @param req 请求内容
     * @return 文件详细信息
     * @throws IOException
     */
    public static List<UploadFile> uploadFiles(HttpServletRequest req) throws IOException {

        List<UploadFile> list = new ArrayList<>();

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(req.getSession().getServletContext());

        // 是多文件
        if (multipartResolver.isMultipart(req)){
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;

            Iterator<String> fileNames = multipartRequest.getFileNames();
            while (fileNames.hasNext()){
                // 获取上传文件
                MultipartFile file = multipartRequest.getFile(fileNames.next());

                if (null != file){

                    //文件全名称
                    String fullName = file.getOriginalFilename();

                    if (StrUtil.isNotBlank(fullName)){

                        //文件名称,不带后缀
                        String fileName = fullName.substring(0, fullName.lastIndexOf("."));
                        //文件后缀
                        String suffix = fullName.substring(fullName.lastIndexOf(".") + 1);
                        //文件UUID随机名称
                        String UUIDName = RandomUtil.randomUUID().replaceAll("-","").concat(".").concat(suffix);
                        //文件大小
                        long fileSize = file.getSize();

                        //获取文件存放全路径
                        File uploadFileSavePath = savePath(UUIDName);
                        //将文件转存至指定目录
                        file.transferTo(uploadFileSavePath);

                        //将文件信息加入到List用于后续操作
                        UploadFile uploadFile = new UploadFile(RandomUtil.randomInt(10_000, 100_000), fileName, fullName, UUIDName, suffix, fileSize);
                        list.add(uploadFile);
                    }

                }

            }
        }
        return list;
    }

    /**
     * 文件存放路径
     * @param uuidName 文件UUID名称
     * @return
     */
    private static File savePath(String uuidName){
        //文件存放文件夹路径
        String floderName = GlobalConstant.UPLOAD_FILE_SAVE_PATH + File.separator + floderName();
        File savePath = new File(floderName);

        //不存在或者不是目录,创建该文件夹
        if (!savePath.exists() || !savePath.isDirectory()){
            savePath.mkdirs();
        }

        return new File(floderName + File.separator + uuidName);
    }

    /**
     * 当前日期文件夹
     * @return
     */
    private static String floderName(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy\\MM\\dd");
        return sdf.format(new Date());
    }


}
