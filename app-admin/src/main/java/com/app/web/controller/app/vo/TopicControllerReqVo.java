package com.app.web.controller.app.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TopicControllerReqVo {
    //问题+答案内容
    private String topicContent;
}
