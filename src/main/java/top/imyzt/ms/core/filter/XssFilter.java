package top.imyzt.ms.core.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    FilterConfig filterConfig = null;

    private List<String> urlExclusion = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getServletPath();

        if (exclusion(servletPath)){
            chain.doFilter(request, response);
        }else {
            chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
        }
    }

    /**
     * 判断url是否需要过滤xss
     * @param servletPath
     * @return
     */
    private boolean exclusion(String servletPath){
        if (urlExclusion == null || urlExclusion.size() == 0){
            return false;
        }

        for (String url : urlExclusion) {
            Pattern pattern = Pattern.compile("^" + url);
            Matcher matcher = pattern.matcher(servletPath);
            return matcher.find();
        }
        return false;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    public List<String> getUrlExclusion() {
        return urlExclusion;
    }

    public void setUrlExclusion(List<String> urlExclusion) {
        this.urlExclusion = urlExclusion;
    }
}
