package top.imyzt.ms.common.service.impl;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import top.imyzt.ms.common.constant.MailConstant;
import top.imyzt.ms.common.model.Mail;
import top.imyzt.ms.common.model.UploadFile;
import top.imyzt.ms.common.service.MailService;
import top.imyzt.ms.common.utils.UploadActionUtil;
import top.imyzt.ms.core.exception.MsException;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 发送邮件的业务实现类
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */
@Service
public class MailServiceImpl implements MailService{

    @Resource
    @Qualifier("javaMailSender")
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;


    @Override
    public void sendSimpleMail(Mail mail) {

        if (mail.isBlank()){
            throw new MsException("邮件内容为空");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setCc(mail.getCc());
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getText());

        mailSender.send(message);
    }

    @Override
    public void sendAttachmentsMail(Mail mail, HttpServletRequest request) {

        if (mail.isBlank()){
            throw new MsException("邮件内容为空");
        }

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

            messageHelper.setFrom(from);
            messageHelper.setTo(mail.getTo());
            messageHelper.setCc(mail.getCc());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getText());
            List<UploadFile> files = UploadActionUtil.uploadFiles(request);

            for (int i = 0, len = files.size(); i < files.size(); i++) {
                //文件后缀
                String suffix = files.get(i).getSuffix();
                //文件存储路径
                String fileSavePath = files.get(i).getFileSavePath();

                FileSystemResource file = new FileSystemResource(new File(fileSavePath));

                messageHelper.addAttachment("附件-" + (i + 1) + "." + suffix, file);
            }

            mailSender.send(mimeMessage);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            Console.error(e);
        }

    }

    @Override
    public void sendInlineMail(Mail mail, HttpServletRequest request) throws Exception {

        if (mail.isBlank()){
            throw new MsException("邮件内容为空");
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

        messageHelper.setFrom(from);
        messageHelper.setSubject(mail.getSubject());
        messageHelper.setTo(mail.getTo());

        messageHelper.setText("<html><body><img src='cid:image' /></body></html>", true);

        List<UploadFile> files = UploadActionUtil.uploadFiles(request);

        //拿到上传文件中的第一个文件
        if (files.size() == 1){
            UploadFile file = files.get(0);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(file.getFileSavePath()));

            // contentid 必须与img.src.cid相同
            messageHelper.addInline("image", fileSystemResource);

            mailSender.send(mimeMessage);
        }else {
            throw new MsException("上传的文件个数超过一个");
        }
    }

    @Override
    public void sendTemplateMail(Mail mail) {

        if (mail.isBlank()){
            throw new MsException("邮件内容为空");
        }

        //测试使用,可以自定义任何内容

        mail.setSubject("欢迎使用Ms");
        mail.setTemplateName(MailConstant.RETGISTER_TEMPLATE);
        Map<String, String> map = new HashMap<>();

        map.put("to", mail.getTo()[0]);
        map.put("identifyingCode", RandomUtil.randomString(6));

        mail.setTemplateModel(map);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

            messageHelper.setFrom(from);
            messageHelper.setTo(mail.getTo());
            messageHelper.setSubject(mail.getSubject());

            Configuration configuration = getConfiguration();
            //注册的模板
            Template registerTemplate = configuration.getTemplate(MailConstant.RETGISTER_TEMPLATE);
            //读取出html
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(registerTemplate, mail.getTemplateModel());

            //将内容设置
            messageHelper.setText(html, true);

            //发送邮件
            mailSender.send(mimeMessage);

        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
            Console.error(e);
        }

    }

    private Configuration getConfiguration() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(MailConstant.TEMPLATE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

        return cfg;
    }
}
