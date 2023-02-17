package com.example.tobysspring.user.dao.c1.v3;

import java.sql.Connection;
import java.sql.DriverManager;

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
