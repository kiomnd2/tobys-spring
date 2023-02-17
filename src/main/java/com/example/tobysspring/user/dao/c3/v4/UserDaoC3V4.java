package com.example.tobysspring.user.dao.c3.v4;

import com.example.tobysspring.user.dao.c3.v2.StatementStrategy;
import com.example.tobysspring.user.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 전략패턴 적용
public class UserDaoC3V4 {

    private JdbcContext jdbcContext;



    // Close 메서드가 제대로 실행되지 않아 문제가 생길 수 있음 따라서 try catch 로 close 시켜주는 로직이 필요함
    public void deleteAll() throws SQLException {
        jdbcContext.workWithStatementStrategy(c -> {
            PreparedStatement ps = c.prepareStatement("delete from users");
            return ps;
        });
    }

    public void add(User user) throws SQLException {
        jdbcContext.workWithStatementStrategy(c -> {
            PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            return ps;
        });
    }

}
