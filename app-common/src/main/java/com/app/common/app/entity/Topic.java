package com.app.common.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 答题题目表
 * </p>
 *
 * @author zhm
 * @since 2025-04-27
 */
@Data
@Accessors(chain = true)
@TableName("topic")
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 问题+答案内容
     */
    @TableField("topic_content")
    private String topicContent;

    /**
     * 正确答案
     */
    @TableField("answer")
    private String answer;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

}