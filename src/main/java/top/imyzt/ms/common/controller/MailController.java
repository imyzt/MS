package top.imyzt.ms.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.imyzt.ms.common.model.Mail;
import top.imyzt.ms.common.service.MailService;
import top.imyzt.ms.core.ret.RetResult;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 邮件发送控制器
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */

@RestController
@RequestMapping("mail")
public class MailController extends BaseController{

    @Autowired
    private MailService mailService;

    /**
     * 发送简单邮件
     * @param mail 邮件
     * @return
     */
    @PostMapping("/sendSimpleMail")
    public RetResult<String> sendSimpleMail(Mail mail){
        mailService.sendSimpleMail(mail);
        return ok();
    }

    /**
     * 发送带附件的邮件
     * @param mail 邮件内容
     * @param request 请求内容
     * @return
     */
    @PostMapping("/sendAttachmentsMail")
    public RetResult<String> sendAttachmentsMail(Mail mail, HttpServletRequest request){

        mailService.sendAttachmentsMail(mail, request);

        return ok();
    }

    /**
     * 发送静态资源(一张照片)
     * @param mail 邮件内容
     * @param request 请求内容
     * @return
     * @throws Exception
     */
    @PostMapping("/sendInlineMail")
    public RetResult<String> sendInlineMail(Mail mail, HttpServletRequest request) {

        try {
            mailService.sendInlineMail(mail, request);
            return ok();
        } catch (Exception e) {
            e.printStackTrace();
            return faln("邮件发送失败");
        }
    }

    /**
     * 发送模板邮件
     * @param mail 邮件内容
     * @return
     * @throws Exception
     */
    @PostMapping("/sendTemplateMail")
    public RetResult<String> sendTemplateMail(Mail mail) {

        mailService.sendTemplateMail(mail);

        return ok();
    }

}
