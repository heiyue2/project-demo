package com.app.web.controller.app.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;

@Data
@Accessors(chain = true)
public class DeepseekReqVo {
    private String model;
    private List<Message> messages;
    private boolean stream;

    @Data
    @Accessors(chain = true)
    public static class Message {
        private String role;
        private String content;
    }
}
