package top.imyzt.ms.core.filter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * <p>
 *     继承 HttpServletRequestWrapper 将指定的标签通过转义或清除
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String name) {

        String[] values = super.getParameterValues(name);

        if (values == null) {
            return null;
        }

        int len = values.length;

        String[] encodedValues = new String[len];

        for (int i = 0; i < len; i++){
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);

        return value == null ? null : cleanXSS(value);
    }

    @Override
    public String getHeader(String name) {

        String value = super.getHeader(name);

        return value == null ? null : cleanXSS(value);
    }

    /**
     * 清除包含指定标签HTML代码
     * @param value
     * @return
     */
    private String cleanXSS(String value) {

        //You'll need to remove the spaces from the html entities below

        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");

        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");

        value = value.replaceAll("'", "& #39;");

        value = value.replaceAll("eval\\((.*)\\)", "");

        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");

        value = value.replaceAll("script", "");

        return value;

    }
}
