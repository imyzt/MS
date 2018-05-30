package top.imyzt.ms.common.model;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 邮件实体类
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */
public class Mail implements Serializable{

    /**
     * 接收人
     */
    private String[] to;

    /**
     * 抄送
     */
    private String[] cc;

    /**
     * 邮件标题
     */
    private String subject;

    /**
     * 邮件内容 简单文本和附件内容必填, 其它类型非必填
     */
    private String text;

    /**
     * 模板需要的数据 发送模板消息必填
     */
    private Map<String, String> templateModel;

    /**
     * 选用哪个模板 发送模板消息必填
     */
    private String templateName;

    /**
     * 邮件不为空
     * @return
     */
    public boolean isNotBlank(){

        return ArrayUtil.isNotEmpty(to) && ArrayUtil.isNotEmpty(cc) &&
                StrUtil.isNotBlank(subject);
    }

    /**
     * 邮件为空
     * @return
     */
    public boolean isBlank(){

        return ArrayUtil.isEmpty(to) || ArrayUtil.isEmpty(cc) ||
                StrUtil.isBlank(subject);
    }

    @Override
    public String toString() {
        return "Mail{" +
                "to=" + Arrays.toString(to) +
                ", cc=" + Arrays.toString(cc) +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", templateModel=" + templateModel +
                ", templateName='" + templateName + '\'' +
                '}';
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, String> getTemplateModel() {
        return templateModel;
    }

    public void setTemplateModel(Map<String, String> templateModel) {
        this.templateModel = templateModel;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
