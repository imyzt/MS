package top.imyzt.ms.core.mutidatasoucre.config;

/**
 *
 * <p>
 *     数据源的上下文(当前使用哪一个数据源)
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/5/4 10:55
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源类型
     * @param dataSourceType 数据源类型
     */
    public static void setDataSourceType(String dataSourceType){
        contextHolder.set(dataSourceType);
    }

    /**
     *获取数据源类型
     * @return 当前哪个数据源
     */
    public static String getDataSourceType(){
        return contextHolder.get();
    }

    /**
     * 清除数据源类型
     */
    public static void clearDataSourceType(){
        contextHolder.remove();
    }
}
