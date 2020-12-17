package kybmig.ssm.service;


import com.mysql.cj.jdbc.MysqlDataSource;
import kybmig.ssm.Utility;
import kybmig.ssm.mapper.TopicCommentMapper;
import kybmig.ssm.mapper.TopicMapper;
import kybmig.ssm.model.ModelFactory;
import kybmig.ssm.model.TopicCommentModel;
import kybmig.ssm.model.TopicModel;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicCommentService {
    private TopicMapper topicMapper;
    private TopicCommentMapper topicCommentMapper;

    public TopicCommentService(TopicMapper topicMapper, TopicCommentMapper topicCommentMapper) {
        this.topicMapper = topicMapper;
        this.topicCommentMapper = topicCommentMapper;
    }


    public List<TopicCommentModel> selectAllCommentsWithUserId(Integer id) {
        return topicCommentMapper.selectAllCommentsWithUserId(id);
    }

    public TopicCommentModel findCommentById(Integer id) {
        return topicCommentMapper.selectTopicComment(id);
    }

    public TopicCommentModel add(Integer userId, Integer topicId, String content) {
        Long createdTime = System.currentTimeMillis();
        Long updatedTime = System.currentTimeMillis();

        TopicCommentModel m = new TopicCommentModel();
        m.setTopicId(topicId);
        m.setContent(content);
        m.setUserId(userId);
        m.setCreatedTime(createdTime);
        m.setUpdatedTime(updatedTime);
        topicCommentMapper.insertTopicComment(m);

        return m;
    }

    public void update(Integer id, String content) {
        TopicCommentModel m = new TopicCommentModel();

        Long updateTime = System.currentTimeMillis();

        m.setId(id);
        m.setContent(content);
        m.setUpdatedTime(updateTime);

        topicCommentMapper.updateTopicComment(m);
    }

    public void deleteById(Integer id) {
        topicCommentMapper.deleteTopicComment(id);
    }





}
