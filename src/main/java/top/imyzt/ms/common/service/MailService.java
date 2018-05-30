package top.imyzt.ms.common.service;

import top.imyzt.ms.common.model.Mail;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 发送邮件的业务类
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */
public interface MailService {

    /**
     * 发送简单邮件
     * @param mail 邮件内容
     */
    void sendSimpleMail(Mail mail);

    /**
     * 发送带附件的邮件
     * @param mail 邮件内容
     * @param request 请求内容
     */
    void sendAttachmentsMail(Mail mail, HttpServletRequest request);

    /**
     * 发送静态资源  一张照片
     * @param mail 邮件内容
     * @throws Exception
     */
    void sendInlineMail(Mail mail, HttpServletRequest request) throws Exception;

    /**
     * 发送模板邮件
     * @param mail 邮件内容
     */
    void sendTemplateMail(Mail mail);
}
