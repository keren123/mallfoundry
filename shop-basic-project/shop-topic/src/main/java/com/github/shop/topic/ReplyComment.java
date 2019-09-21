/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.shop.topic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@JsonPropertyOrder({
        "topicName", "commentId", "id", "uid", "nickname",
        "replyId", "replyUid", "replyNickname",
        "message", "likes", "createTime"})
public class ReplyComment {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    @JsonProperty("topic_name")
    private String topicName;

    @Getter
    @Setter
    @JsonProperty("comment_id")
    private String commentId;

    @Getter
    @Setter
    private String uid;

    @Getter
    @Setter
    private String nickname;

    @Getter
    @Setter
    @JsonProperty("reply_id")
    private String replyId;

    @Getter
    @Setter
    @JsonProperty("reply_uid")
    private String replyUid;

    @Getter
    @Setter
    @JsonProperty("reply_nickname")
    private String replyNickname;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private int likes;

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("create_time")
    private Date createTime;

    public static ReplyComment of(String topicName, String commentId, String replyId) {
        ReplyComment reply = new ReplyComment();
        reply.setTopicName(topicName);
        reply.setCommentId(commentId);
        reply.setId(replyId);
        return reply;
    }
}
