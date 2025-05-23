package com.app.web.controller.app;

import com.app.common.app.entity.Topic;
import com.app.common.app.mapper.TopicMapper;
import com.app.common.core.domain.R;
import com.app.web.controller.app.vo.DeepseekReqVo;
import com.app.web.controller.app.vo.DeepseekRespVo;
import com.app.web.controller.app.vo.TopicControllerReqVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 答题题目表 前端控制器
 * </p>
 *
 * @author zhm
 * @since 2025-04-27
 */
@RestController
@RequestMapping("/topic")
public class TopicController {

    @Resource
    private TopicMapper topicMapper;
    @Resource
    private RestTemplate restTemplate;

    /** 添加题目,如果有则返回答案,没有则通过deepseek获取答案 */
    @RequestMapping("/addTopic")
    public R addTopic(@RequestBody TopicControllerReqVo topicControllerReqVo) {
        Topic topic = topicMapper.selectOne(new LambdaQueryWrapper<Topic>()
                .eq(Topic::getTopicContent, topicControllerReqVo.getTopicContent())
        );
        if(topic == null){
            // 调用deepseek获取答案
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // 设置内容类型为 JSON
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)); // 接受 JSON 响应
            headers.set("Authorization", "Bearer sk-24cbb0172b934b66a56c7f890c8be425"); // 自定义认证头

            DeepseekReqVo requestBody = new DeepseekReqVo()
                    .setModel("deepseek-chat")
                    .setMessages(Collections.singletonList(new DeepseekReqVo.Message()
                            .setRole("system")
                            .setContent(topicControllerReqVo.getTopicContent()+" 给我一个正确的选项,无需分析")))
                    .setStream(false);

            ResponseEntity<DeepseekRespVo> result = restTemplate.exchange("https://api.deepseek.com/v1/chat/completions"
                    , HttpMethod.POST, new HttpEntity<>(requestBody, headers), DeepseekRespVo.class);

            // 保存起来
            String answer = result.getBody().getChoices().get(0).getMessage().getContent();

            Topic answerTopic = new Topic()
                    .setTopicContent(topicControllerReqVo.getTopicContent())
                    .setAnswer(answer);
            topicMapper.insert(answerTopic);

            // 返回答案
            return R.ok(answerTopic);
        }

        return R.ok(topic);
    }
}
