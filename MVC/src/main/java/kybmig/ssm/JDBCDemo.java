package kybmig.ssm;

import com.mysql.cj.jdbc.MysqlDataSource;
import jdk.jshell.execution.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
    public static void log(String format, Object... args) {
        System.out.println(String.format(format, args));
    }

    public static MysqlDataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("12345");
        dataSource.setServerName("127.0.0.1");
        dataSource.setDatabaseName("ssm");

        try{
            dataSource.setCharacterEncoding("UTF-8");
            dataSource.setServerTimezone("Asia/Shanghai");

            Utility.log("url: %s", dataSource);
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return dataSource;

    }

    public static void addBySQL(String content) {
        MysqlDataSource ds =getDataSource();
        String sqlInsert = String.format("INSERT INTO `todo` (content) VALUES('%S')", content);

        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlInsert);

            connection.close();
            statement.close();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static void selectBySQL(Integer id) {

        MysqlDataSource ds = getDataSource();
        String sql = String.format("select * from `ssm`.`Todo` where id = %s", id);
//        String sql = String.format("select * from `ssm`.`Todo`");

        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
//                rs.first();
                log("result id : %s", rs.getInt("id"));
                log("result content: %s", rs.getString("content"));

            }

            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        addBySQL("11111");
        selectBySQL(1);
    }



















}
