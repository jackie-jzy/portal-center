package com.winter.portal.server;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import org.junit.jupiter.api.Test;

/**
 * <p>
 *
 * </p>
 *
 * @author jzyan
 * @since 2022-03-10
 */
public class AutoGenerator {
    @Test
    void contextLoads() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/system_center?useSSL=false&useUnicode=true" +
                "&characterEncoding" +
                "=utf-8", "root", "123456")
                //FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("jzyan") // 设置作者
                            .outputDir("target"); // 覆盖已生成文件
                })
                .packageConfig(builder -> {
                    // 设置父包名
                    builder.parent("com.winter.portal.server");
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_resource") // 设置需要生成的表名
                            .addTablePrefix("sys_") // 设置过滤表前缀
                            .controllerBuilder()
                            .enableRestStyle()
                            .enableHyphenStyle()
                            .entityBuilder()
                            .enableLombok()
                            .entityBuilder()
                            .formatFileName("%sEntity")
                            .enableFileOverride();
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
