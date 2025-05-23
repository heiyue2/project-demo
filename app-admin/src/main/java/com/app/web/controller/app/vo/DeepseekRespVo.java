package com.app.web.controller.app.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class DeepseekRespVo {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;
    @JSONField(name = "system_fingerprint")
    private String systemFingerprint;


    // Getters and Setters
    @Data
    @Accessors(chain = true)
    public static class Choice {
        private int index;
        private Message message;
        private Object logprobs;
        @JSONField(name = "finish_reason")
        private String finishReason;

        // Getters and Setters
    }

    @Data
    @Accessors(chain = true)
    public static class Message {
        private String role;
        private String content;
    }

    @Data
    @Accessors(chain = true)
    public static class Usage {
        @JSONField(name = "prompt_tokens")
        private int promptTokens;
        @JSONField(name = "completion_tokens")
        private int completionTokens;
        @JSONField(name = "total_tokens")
        private int totalTokens;
        @JSONField(name = "prompt_tokens_details")
        private PromptTokensDetails promptTokensDetails;
        @JSONField(name = "prompt_cache_hit_tokens")
        private int promptCacheHitTokens;
        @JSONField(name = "prompt_cache_miss_tokens")
        private int promptCacheMissTokens;

        // Getters and Setters
    }

    @Data
    @Accessors(chain = true)
    public static class PromptTokensDetails {
        @JSONField(name = "cached_tokens")
        private int cachedTokens;

        // Getters and Setters
    }
}
