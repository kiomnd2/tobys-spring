package com.example.tobysspring.user.dao.v3;

import com.example.tobysspring.user.dao.v1.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    public  UserDaoV3 userDao() {
        ConnectionMaker connectionMaker = () -> {
            Class.forName("org.h2.Driver"); // 클래스 호출
            Connection c = DriverManager.getConnection(
                    "jdbc:h2:~/test", "sa", "");
            return c;
        };
        return new UserDaoV3(connectionMaker);
    }
}
