package top.imyzt.ms.core.filter;

import cn.hutool.core.util.StrUtil;
import org.apache.catalina.startup.RealmRuleSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    HttpServletRequest orgRequest = null;

    private boolean isIncludeRichText = false;

    /**
     *
     */
    public XssHttpServletRequestWrapper(HttpServletRequest req, boolean isIncludeRichText) {
        super(req);
        orgRequest  = req;
        this.isIncludeRichText = isIncludeRichText;
    }

    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤.
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {

        if (("content".equals(name)) || (name.endsWith("WithHtml")) && !isIncludeRichText){
            return super.getParameter(name);
        }

        name = XssFilterUtil.clean(name);
        String value = super.getParameter(name);
        if (StrUtil.isNotBlank(value)){
            value = XssFilterUtil.clean(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] arr = super.getParameterValues(name);
        if (arr != null){
            for (int i = 0; i < arr.length ; i ++){
                arr[i]  = XssFilterUtil.clean(arr[i]);
            }
        }
        return arr;
    }

    @Override
    public String getHeader(String name) {
        name = XssFilterUtil.clean(name);
        String value = super.getHeader(name);
        if (StrUtil.isNotBlank(value)){
            value = XssFilterUtil.clean(value);
        }
        return value;
    }

    /**
     * 获取最原始的request
     * @return
     */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * 获取最原始的request的静态方法
     * @param req
     * @return
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req){
        if (req instanceof XssHttpServletRequestWrapper){
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();
        }
        return req;
    }
}
