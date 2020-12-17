package kybmig.ssm.service;


import com.mysql.cj.jdbc.MysqlDataSource;
import kybmig.ssm.Utility;
import kybmig.ssm.mapper.TodoMapper;
import kybmig.ssm.model.ModelFactory;
import kybmig.ssm.model.TodoModel;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private TodoMapper mapper;

    public TodoService(TodoMapper todoMapper) {
        this.mapper = todoMapper;
    }

    public TodoModel add(String content) {
        TodoModel m = new TodoModel();
        m.setContent(content);
        mapper.insertTodo(m);

        Utility.log("todo.id: %s", m.getId());
        return m;

    }

//    public TodoModel add(String content) {
//        ArrayList<TodoModel> ms = load();
//
//
//        TodoModel m = new TodoModel();
//        m.setContent(content);
//        Integer id = ms.size() + 1;
//        m.setId(id);
//        ms.add(m);
//        save(ms);
//        return m;
//    }

    public TodoModel addBySQL(String content) {
        TodoModel m = new TodoModel();
        m.setContent(content);

        MysqlDataSource ds =Utility.getDataSource();
        String sqlInsert = String.format("INSERT INTO `todo` (content) VALUES(?)", content);

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

    public void update(Integer id, String content) {
        TodoModel m = new TodoModel();
        m.setId(id);
        m.setContent(content);
        mapper.updateTodo(m);
    }

//    public void update(Integer id, String content) {
//        ArrayList<TodoModel> todos = load();
//        for (int i = 0; i < todos.size(); i++) {
//            TodoModel e = todos.get(i);
//            if (e.getId().equals(id)) {
//                e.setContent(content);
//            }
//        }
//        save(todos);
//    }

    public TodoModel updateBySQL(Integer id, String content) {
        TodoModel m = new TodoModel();
        m.setContent(content);

        MysqlDataSource ds =Utility.getDataSource();
        String sqlInsert = String.format("UPDATE `todo` SET content = (?) WHERE id = (?)");

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
        mapper.deleteTodo(id);
    }

//    public void deleteById(Integer id) {
//        ArrayList<TodoModel> ms = load();
//
//        for (int i = 0; i < ms.size(); i++) {
//            TodoModel e = ms.get(i);
//            if (e.getId().equals(id)) {
//                ms.remove(e);
//            }
//        }
//        save(ms);
//    }

    public void deleteByIdSQL(Integer id) {
        MysqlDataSource ds =Utility.getDataSource();
        String sqlInsert = String.format("DELETE FROM `todo` WHERE id = (?)");

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

    public TodoModel findById(Integer id) {
        TodoModel todo = mapper.selectTodo(id);
        return todo;
    }

//    public  TodoModel findById(Integer id) {
//        ArrayList<TodoModel> todos = load();
//        for (int i = 0; i < todos.size(); i++) {
//            TodoModel e = todos.get(i);
//            if (e.getId().equals(id)) {
//                return e;
//            }
//        }
//        return null;
//    }

    public TodoModel finByIdSQL(Integer id) {
        TodoModel m = new TodoModel();

        MysqlDataSource ds = Utility.getDataSource();
        String sql = "select * from `todo` where id = ?";

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

    public List<TodoModel> all() {
        return mapper.selectAllTodo();

    }

//    public  ArrayList<TodoModel> all() {
//        return load();
//    }

    public ArrayList<TodoModel> allBySQL() {
        ArrayList<TodoModel> ms = new ArrayList<>();

        MysqlDataSource ds = Utility.getDataSource();
        String sql = "select * from `ssm`.`Todo`";

        try {
            Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TodoModel m = new TodoModel();
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

    public ArrayList<TodoModel> load() {
        String className = TodoModel.class.getSimpleName();

        ArrayList<TodoModel> ms = ModelFactory.load(className, 2, modelData -> {
            Integer id = Integer.valueOf(modelData.get(0));
            String content = modelData.get(1);

            TodoModel m = new TodoModel();
            m.setId(id);
            m.setContent(content);
            return m;
        });
        return ms;
    }

    public static void save(ArrayList<TodoModel> list) {
        String className = TodoModel.class.getSimpleName();
        ModelFactory.save(className, list, model -> {
            ArrayList<String> lines = new ArrayList<>();
            lines.add(model.getId().toString());
            lines.add(model.getContent());
            return lines;
        });
    }
}
