package kybmig.ssm.service;


import com.mysql.cj.jdbc.MysqlDataSource;
import kybmig.ssm.Utility;
import kybmig.ssm.mapper.WeiboMapper;
import kybmig.ssm.model.*;
import kybmig.ssm.model.WeiboModel;
import kybmig.ssm.model.WeiboModel;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeiboService {

    private WeiboMapper mapper;

    public WeiboService(WeiboMapper mapper) {
        this.mapper = mapper;
    }

    public WeiboModel add(String content) {
        WeiboModel weibo = new WeiboModel();
        weibo.setContent(content);

        mapper.insertWeibo(weibo);
        Utility.log("weibo.id: %s", weibo.getId());

        return weibo;
    }

    public WeiboModel addBySQL(String content) {
        WeiboModel m = new WeiboModel();
        m.setContent(content);

        MysqlDataSource ds = Utility.getDataSource();
        String sqlInsert = String.format("INSERT INTO `weibo` (content) VALUES(?)", content);

        try {
            Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getContent());
            ps.executeUpdate();

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


    public  void update(Integer id, String content) {
        WeiboModel weibo = findById(id);
        weibo.setContent(content);
        mapper.updateWeibo(weibo);
    }

    public WeiboModel updateBySQL(Integer id, String content) {
        WeiboModel m = new WeiboModel();
        m.setContent(content);
        m.setId(id);

        MysqlDataSource ds = Utility.getDataSource();
        String sqlInsert = "update  `Weibo` set  content = (?) where id = (?) ";
        try {
            Connection connection = ds.getConnection();
//            connection.execute(sqlInsert);
            PreparedStatement statement = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, content);
            statement.setInt(2, id);
            statement.executeUpdate();

            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return m;
    }

    public void deleteById(Integer id) {
        mapper.deleteWeibo(id);
    }

    public  void deleteByIdSQL(Integer id) {
        MysqlDataSource ds = Utility.getDataSource();
        String sqlInsert = "delete from `Weibo` where id = (?)";

        try {
            Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.executeUpdate();

            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public  WeiboModel findById(Integer id) {
        WeiboModel weibo = mapper.selectWeibo(id);
        return weibo;
    }

    public WeiboModel finByIdSQL(Integer id) {
        WeiboModel m = new WeiboModel();

        MysqlDataSource ds = Utility.getDataSource();
        String sql = "select * from `weibo` where id = ?";

        try {
            Connection connection = ds.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);

            try(ResultSet rs = statement.executeQuery()) {
                rs.first();
                Integer todo_id = rs.getInt("id");
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


    public List<WeiboModel> all() {
        List<WeiboModel> weibos = mapper.selectAllWeibo();

        return weibos;

    }

    public ArrayList<WeiboModel> allBySQL() {
        ArrayList<WeiboModel> ms = new ArrayList<>();

        MysqlDataSource ds = Utility.getDataSource();
        String sql = "select * from `ssm`.`Weibo`";

        try {
            Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                WeiboModel m = new WeiboModel();
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

    public ArrayList<WeiboModel> load() {
        String className = WeiboModel.class.getSimpleName();

        ArrayList<WeiboModel> ms = ModelFactory.load(className, 2, modelData -> {
            Integer id = Integer.valueOf(modelData.get(0));
            String content = modelData.get(1);

            WeiboModel m = new WeiboModel();
            m.setId(id);
            m.setContent(content);
            return m;
        });
        return ms;
    }

    public static void save(ArrayList<WeiboModel> list) {
        String className = WeiboModel.class.getSimpleName();
        ModelFactory.save(className, list, model -> {
            ArrayList<String> lines = new ArrayList<>();
            lines.add(model.getId().toString());
            lines.add(model.getContent());
            return lines;
        });
    }
}
