package kybmig.ssm.service;


import com.mysql.cj.jdbc.MysqlDataSource;
import kybmig.ssm.Utility;
import kybmig.ssm.mapper.TopicCommentMapper;
import kybmig.ssm.mapper.TopicMapper;
import kybmig.ssm.model.BoardModel;
import kybmig.ssm.model.ModelFactory;
import kybmig.ssm.model.TopicCommentModel;
import kybmig.ssm.model.TopicModel;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {

    private TopicMapper mapper;
    private TopicCommentMapper commentMapper;

    public TopicService(TopicMapper TopicMapper, TopicCommentMapper commentMapper) {
        this.mapper = TopicMapper;
        this.commentMapper = commentMapper;
    }

    public List<TopicModel> selectAlltopicByBoardId(Integer id) {
        return mapper.selectAlltopicByBoardId(id);
    }

    public List<Integer> selectTopicIdByUserId(Integer id) {
        return mapper.selectTopicIdByUserId(id);
    }

    public TopicModel selectOneWithCommentsByTopicId(Integer id) {
        return mapper.selectOneWithCommentsByTopicId(id);
    }

    public List<TopicModel> selectAlltopicByUserId(Integer id) {
        return mapper.selectAlltopicByUserId(id);
    }


    public TopicModel findByIdWithComments(Integer id) {
        TopicModel m = mapper.selectOneWithComments(id);
        return m;
    }

    public TopicModel findByIdWithCommentsAndUser(Integer id) {
        TopicModel m = mapper.selectOneWithCommentsAndUser(id);
        return m;
    }

    public TopicModel add(Integer userId, String title, String content, BoardModel boardModel, Integer boardId) {
        Long createTime = System.currentTimeMillis();
        Long updateTime = System.currentTimeMillis();

        TopicModel m = new TopicModel();
        m.setContent(content);
        m.setTitle(title);
        m.setUserId(userId);
        m.setCreatedTime(createTime);
        m.setUpdatedTime(updateTime);
        m.setBoard(boardModel);
        m.setBoardId(boardId);
        mapper.inserttopic(m);

        Utility.log("Topic.id: %s", m.getId());
        return m;

    }

//    public TopicModel add(String content) {
//        ArrayList<TopicModel> ms = load();
//
//
//        TopicModel m = new TopicModel();
//        m.setContent(content);
//        Integer id = ms.size() + 1;
//        m.setId(id);
//        ms.add(m);
//        save(ms);
//        return m;
//    }

    public TopicModel addBySQL(String content) {
        TopicModel m = new TopicModel();
        m.setContent(content);

        MysqlDataSource ds =Utility.getDataSource();
        String sqlInsert = String.format("INSERT INTO `Topic` (content) VALUES(?)", content);

        try {
            Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getContent());
            ps.executeUpdate();

//            ResultSet rs = ps.getGeneratedKeys();
//            rs.first();
//            Integer id = rs.getInt("GENERATED_KEY");
//            m.setId(id);

            try(ResultSet rs = ps.getGeneratedKeys()) {
                rs.first();
                Integer id = rs.getInt("GENERATED_KEY");
                m.setId(id);
            }

            connection.close();
            ps.close();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return m;
    }

    public void update(Integer id, String title, String content) {
        TopicModel m = new TopicModel();

        Long updateTime = System.currentTimeMillis();

        m.setId(id);
        m.setTitle(title);
        m.setContent(content);
        m.setUpdatedTime(updateTime);
        mapper.updatetopic(m);
    }

//    public void update(Integer id, String content) {
//        ArrayList<TopicModel> Topics = load();
//        for (int i = 0; i < Topics.size(); i++) {
//            TopicModel e = Topics.get(i);
//            if (e.getId().equals(id)) {
//                e.setContent(content);
//            }
//        }
//        save(Topics);
//    }

    public TopicModel updateBySQL(Integer id, String content) {
        TopicModel m = new TopicModel();
        m.setContent(content);

        MysqlDataSource ds =Utility.getDataSource();
        String sqlInsert = String.format("UPDATE `Topic` SET content = (?) WHERE id = (?)");

        try {
            Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, content);
            ps.setInt(2, id);

            ps.executeUpdate();

//            ResultSet rs = ps.getGeneratedKeys();
//            rs.first();
//            Integer id = rs.getInt("GENERATED_KEY");
//            m.setId(id);

            connection.close();
            ps.close();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return m;
    }

    public void deleteById(Integer id) {
        mapper.deletetopic(id);
    }

//    public void deleteById(Integer id) {
//        ArrayList<TopicModel> ms = load();
//
//        for (int i = 0; i < ms.size(); i++) {
//            TopicModel e = ms.get(i);
//            if (e.getId().equals(id)) {
//                ms.remove(e);
//            }
//        }
//        save(ms);
//    }

    public void deleteByIdSQL(Integer id) {
        MysqlDataSource ds =Utility.getDataSource();
        String sqlInsert = String.format("DELETE FROM `Topic` WHERE id = (?)");

        try {
            Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();

            connection.close();
            ps.close();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public TopicModel findById(Integer id) {
        TopicModel Topic = mapper.selecttopic(id);
        return Topic;
    }

//    public  TopicModel findById(Integer id) {
//        ArrayList<TopicModel> Topics = load();
//        for (int i = 0; i < Topics.size(); i++) {
//            TopicModel e = Topics.get(i);
//            if (e.getId().equals(id)) {
//                return e;
//            }
//        }
//        return null;
//    }

    public TopicModel finByIdSQL(Integer id) {
        TopicModel m = new TopicModel();

        MysqlDataSource ds = Utility.getDataSource();
        String sql = "select * from `Topic` where id = ?";

        try {
            Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);

            try(ResultSet rs = statement.executeQuery()) {
                rs.first();
                Integer Topic_id = rs.getInt("id");
                String content = rs.getString("content");
                m.setId(id);;
                m.setContent(content);
            }


            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return m;
    }

    public List<TopicModel> all() {
        return mapper.selectAlltopic();

    }

//    public  ArrayList<TopicModel> all() {
//        return load();
//    }

    public ArrayList<TopicModel> allBySQL() {
        ArrayList<TopicModel> ms = new ArrayList<>();

        MysqlDataSource ds = Utility.getDataSource();
        String sql = "select * from `ssm`.`Topic`";

        try {
            Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TopicModel m = new TopicModel();
                m.setId(rs.getInt("id"));
                m.setContent(rs.getString("content"));

                ms.add(m);
            }

            connection.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return ms;
    }

    public ArrayList<TopicModel> load() {
        String className = TopicModel.class.getSimpleName();

        ArrayList<TopicModel> ms = ModelFactory.load(className, 2, modelData -> {
            Integer id = Integer.valueOf(modelData.get(0));
            String content = modelData.get(1);

            TopicModel m = new TopicModel();
            m.setId(id);
            m.setContent(content);
            return m;
        });
        return ms;
    }

    public static void save(ArrayList<TopicModel> list) {
        String className = TopicModel.class.getSimpleName();
        ModelFactory.save(className, list, model -> {
            ArrayList<String> lines = new ArrayList<>();
            lines.add(model.getId().toString());
            lines.add(model.getContent());
            return lines;
        });
    }


}
