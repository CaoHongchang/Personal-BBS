package kybmig.ssm;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class Utility {

    private static Logger logger = LoggerFactory.getLogger(Utility.class);

    static public void log(String format, Object...args) {
        logger.info(String.format(format, args));
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
}
