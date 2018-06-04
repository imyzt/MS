package top.imyzt.ms.core.interceptor;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.imyzt.ms.core.ret.RetCode;
import top.imyzt.ms.core.ret.RetResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 授权认证拦截器,检查请求头中是否含有认证信息
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */
public class AuthorizedInterceptor implements HandlerInterceptor {

    private static final String IZATION = "imyzt";

    /**
     * 在请求处理之前进行调用(Controller方法调用之前)
     * <br/>
     * 只有返回true,才会继续向下执行,返回false停止当前请求     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Log log = LogFactory.get();
        log.info("AuthorizedInterceptor >>> preHandle");

        String ization = request.getHeader("ization");
        if (IZATION.equals(ization)){
            return true;
        }else {
                            /*throw new MsException(RetCode.UNAUTHORIZED);*/
            RetResult<String> result = new RetResult<>();
            String falnMsg = "签名认证失败";
            result.setCode(RetCode.UNAUTHORIZED).setMsg(falnMsg);
            responseResult(response, result);
            log.info("接口: [".concat(request.getRequestURI()).concat("] ").concat(falnMsg));
            return false;
        }
    }

    /**
     * 在请求处理之后执行,但是在视图被渲染之前(Controller方法调用之后)
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        Log log = LogFactory.get();
        log.info("AuthorizedInterceptor >>> postHandle");
    }


    /**
     * 在整个请求结束后被调用,也就是DispatcherServlet 渲染了对应的视图之后执行(主要用于对资源的清理工作)
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {

        Log log = LogFactory.get();
        log.info("AuthorizedInterceptor >>> afterCompletion");
    }

    /**
     * 封装 response 返回的消息
     * @param resp
     * @param ret
     */
    private static void responseResult(HttpServletResponse resp, RetResult<String> ret){
        Log log = LogFactory.get();

        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-type", "application/json;charset=UTF-8");
        resp.setStatus(200);
        try {
            resp.getWriter().write(JSON.toJSONString(ret, SerializerFeature.WriteMapNullValue));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
