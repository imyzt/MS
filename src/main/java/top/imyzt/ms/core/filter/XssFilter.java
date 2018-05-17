package top.imyzt.ms.core.filter;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang.BooleanUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *     Xss过滤器
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */
public class XssFilter implements Filter {

    //是否过滤富文本内容
    private static boolean IS_INCLUDE_RICH_TEXT = true;

    public List<String> excludes = new ArrayList<>();

    /**
     * 从InitParameter中读取出配置文件
     * isIncludeRichText(bool)表示是否需要过滤富文本内容
     * excludes是排除不需要过滤的url
     * @param config XssFilter配置文件
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        String isIncludeRichText = config.getInitParameter("isIncludeRichText");
        if (StrUtil.isNotBlank(isIncludeRichText)) {
            IS_INCLUDE_RICH_TEXT = BooleanUtils.toBoolean(isIncludeRichText);
        }
        String temp = config.getInitParameter("excludes");
        if (StrUtil.isNotBlank(temp)){
            String[] urls = temp.split(",");
            for (int i = 0; urls != null && i < urls.length; i++){
                excludes.add(urls[i]);
            }
        }
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (handleExcludeURL(req, resp)){
            chain.doFilter(request, response);
            return;
        }
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request, IS_INCLUDE_RICH_TEXT);
        chain.doFilter(xssRequest, response);
    }

    private boolean handleExcludeURL(HttpServletRequest req, HttpServletResponse resp){
        if (excludes == null || excludes.isEmpty()){
            return false;
        }

        String url = req.getServletPath();
        for (String pattern : excludes) {
            Pattern compile = Pattern.compile("^" + pattern);
            Matcher matcher = compile.matcher(url);
            if (matcher.find()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
