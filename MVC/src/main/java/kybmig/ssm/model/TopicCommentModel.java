package kybmig.ssm.model;

import kybmig.ssm.mapper.TopicCommentMapper;

import java.util.ArrayList;

public class TopicCommentModel extends BaseModel {
    private Integer id;
    private String content;
    private Integer userId;
    private Integer topicId;

    private Long createdTime;
    private Long updatedTime;

    private UserModel user;

    private ArrayList<TopicModel> topicModels;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public ArrayList<TopicModel> getTopicModels() {
        return topicModels;
    }

    public void setTopicModels(ArrayList<TopicModel> topicModels) {
        this.topicModels = topicModels;
    }
}
