package com.app.gen;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * 代码生成器
 */
public class GenCode {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://43.142.55.49:3306/project-demo?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=Asia/Shanghai"
                        , "root"
                        , "asdasd")
            // 全局配置
            .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？")).fileOverride())
            // 包配置
            .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？如:com.game.system")))
            // 策略配置
            .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
//                    .addTablePrefix("pn_") // 过滤表的前缀
                    .controllerBuilder().enableRestStyle().enableHyphenStyle()
                    .entityBuilder().enableLombok().addTableFills(
                            new Column("create_time", FieldFill.INSERT),
                            new Column("update_time", FieldFill.INSERT_UPDATE),
                            new Column("create_by", FieldFill.INSERT),
                            new Column("update_by", FieldFill.INSERT_UPDATE)
                    ).logicDeleteColumnName("deleted").enableTableFieldAnnotation()
                    .serviceBuilder().formatServiceFileName("%sService")
                    .build())
                .templateConfig(new Consumer<TemplateConfig.Builder>() {
                    @Override
                    public void accept(TemplateConfig.Builder builder) {
                        // 实体类使用我们自定义模板 @Getter @Setter 换成@Data
                        builder.entity("templates/genCode");
                    }
                }).templateEngine(new FreemarkerTemplateEngine())
            /*
                模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
               .templateEngine(new BeetlTemplateEngine())
               .templateEngine(new FreemarkerTemplateEngine())
             */
            .execute();
    }
    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
