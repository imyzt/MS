package top.imyzt.ms.core.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * <p>
 *     Mybatis-Plus代码生成插件,自定义模板使更符合MS功能
 * </p>
 *
 * @author: imyzt
 * @email imyzt01@gmail.com
 * @date: 2018/4/27 10:09
 */
public class MpGenerator {

    /**
     * <p>
     * MySQL 自定义模板生成演示
     * </p>
     */
    public static void main(String[] args) {

        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        mpg.setGlobalConfig(globalConfig("D:\\y\\MS\\src\\main\\java", "imyzt"));
        // 数据源配置
        mpg.setDataSource(dataSourceConfig());
        // 策略配置
        mpg.setStrategy(strategyConfig());
        // 包配置
        mpg.setPackageInfo(packageConfig("top.imyzt.ms.modular", "system"));
        //自定义模板配置
        mpg.setTemplate(template());
        // 注入自定义配置
        mpg.setCfg(injectionConfig());

        // 执行生成
        mpg.execute();
        // 打印注入设置
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }

    /**
     * 自定义模板
     * @return
     */
    private static TemplateConfig template(){
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templates/generator/controller.java.vm");
        tc.setEntity("/templates/generator/entity.java.vm");
        tc.setMapper("/templates/generator/mapper.java.vm");
        tc.setXml("/templates/generator/mapper.xml.vm");
        tc.setService("/templates/generator/service.java.vm");
        tc.setServiceImpl("/templates/generator/serviceImpl.java.vm");
        return tc;
    }

    /**
     * 全局配置
     * @param path 输出目录
     * @param author 作者
     * @return
     */
    private static GlobalConfig globalConfig(String path, String author){
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()){
            throw new RuntimeException("不是目录或目录不存在");
        }
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(path);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor(author);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sDao");
        // gc.setServiceName("MP%sService");
        // gc.setServiceImplName("%sServiceDiy");
        // gc.setControllerName("%sAction");
        return gc;
    }

    /**
     * 数据源
     * @return
     */
    private static DataSourceConfig dataSourceConfig(){
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://localhost:3306/ms?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&autoReconnect=true&failOverReadOnly=false");
        return dsc;
    }

    /**
     * 策略配置
     * @return
     */
    private static StrategyConfig strategyConfig(){
        StrategyConfig strategy = new StrategyConfig();
        //strategy.setTablePrefix("beautiful_");// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[] { "sys_user" }); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 字段名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //是否设置为Rest API
        strategy.setRestControllerStyle(true);
        // 自定义实体父类
        // strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
        strategy.setSuperControllerClass("top.imyzt.ms.common.controller.BaseController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuliderModel(true);
        return strategy;
    }

    /**
     * 包配置
     * @param packageName 包名
     * @param modelName 模块名
     * @return
     */
    private static PackageConfig packageConfig(String packageName, String modelName){
        PackageConfig pc = new PackageConfig();
        pc.setEntity("model").setController("controller");
        //包名
        pc.setParent(packageName);
        //模块名
        pc.setModuleName(modelName);
        return pc;
    }

    /**
     * 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
     * @return
     */
    private static InjectionConfig injectionConfig(){
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>(16);
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        return cfg;
    }
}
