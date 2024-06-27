
package com.qunar.qfc2024.infrastructure;


import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zhangge
 */
public class MySQLCodeGenerator {
    private final static String PARENT_PACKAGE_NAME = "com.qunar.qfc2024.infrastructure";
    private final static String OUT_DIR = "/QFC2024-infrastructure/src/main";

    public static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/qfc2024?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "root";
    public static final String PASSWORD = "root";

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + OUT_DIR + "/java");
        gc.setAuthor("mybatisplus generator");
        gc.setOpen(false);
        //实体属性 Swagger2 注解
        gc.setSwagger2(true);
        gc.setFileOverride(true);
        gc.setEntityName("%sPO");
        gc.setServiceName("%sRepository");
        gc.setServiceImplName("%sRepositoryImpl");
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        dsc.setDriverName(DRIVER_CLASS_NAME);
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                String name = projectPath + OUT_DIR + "/resources/mybatis/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                return name.replace("POMapper", "Mapper");
            }
        });
        cfg.setFileOutConfigList(focList);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setController(null);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig()
                .setEntityColumnConstant(true)
                .setEntityLombokModel(true)
                .setRestControllerStyle(false)
                //不生成id字段
//                .setSuperEntityColumns("id")
                .setInclude(scanner("表名，多个英文逗号分割").split(","))
                .setControllerMappingHyphenStyle(false)
//                .setEntitySerialVersionUID(false)
                .setEntityBooleanColumnRemoveIsPrefix(true)
                .setEntityTableFieldAnnotationEnable(true)
                //表名下划线对应驼峰
                .setNaming(NamingStrategy.underline_to_camel)
                //设定表前缀，加上该配置后，会在表上自动生成@TableName注解
                .setTablePrefix("t_")
                .setColumnNaming(NamingStrategy.underline_to_camel);

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(PARENT_PACKAGE_NAME);
        packageConfig.setEntity("po");
        packageConfig.setMapper("mapper");
        packageConfig.setService("repository");
        packageConfig.setServiceImpl("repository.impl");
        new AutoGenerator().setGlobalConfig(gc)
                .setDataSource(dsc)
                .setCfg(cfg)
                .setStrategy(strategy)
                .setTemplate(templateConfig)
                .setTemplateEngine(new VelocityTemplateEngine())
                .setPackageInfo(packageConfig)
                .execute();
    }
}