package top.imyzt.ms.core.filter;

import com.fasterxml.jackson.core.PrettyPrinter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

/**
 * <p>
 *     Xss非法标签过滤
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/12 11:46
 */
public class XssFilterUtil {

    /**
     * 使用自带的basicWithImages 白名单
     * 允许的便签有a,b,blockquote,br,cite,code,dd,dl,dt,em,i,li,ol,p,pre,q,small,span,strike,strong,sub,sup,u,ul,img
     * 以及a标签的href,img标签的src,align,alt,height,width,title属性
     */
    private static final Whitelist WHITELIST = Whitelist.basicWithImages();

    private static final Document.OutputSettings OUTPUT_SETTINGS = new Document.OutputSettings().prettyPrint(false);

    static {
        /**
         * 富文本编辑时一些样式是通过style实现的,故需要给所有标签添加style属性
         */
        WHITELIST.addAttributes(":all", "style");
    }

    /**
     * 脱敏(XSS)处理
     * @param content 需要脱敏的问题
     * @return
     */
    public static String clean(String content){
        return Jsoup.clean(content, "", WHITELIST, OUTPUT_SETTINGS);
    }





}
