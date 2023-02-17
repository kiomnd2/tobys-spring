package com.example.tobysspring.user.dao.c3.v5;

import com.example.tobysspring.user.domain.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// 전략패턴 적용
public class UserDaoC3V5 {

    private JdbcContext jdbcContext;


    public void deleteAll() throws SQLException {
        jdbcContext.executeSql("delete from users");
    }

    public void add(User user) throws SQLException {
        jdbcContext.executeSql("insert into users(id, name, password) values(?,?,?)",
                user.getId(),
                user.getName(),
                user.getPassword());
    }

}
