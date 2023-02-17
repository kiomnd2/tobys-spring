package com.example.tobysspring.user.dao.c3.v2;

import com.example.tobysspring.user.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 전략패턴 적용
public class UserDaoC3V2 {

    private DataSource dataSource;

    public UserDaoC3V2(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    // Close 메서드가 제대로 실행되지 않아 문제가 생길 수 있음 따라서 try catch 로 close 시켜주는 로직이 필요함
    public void deleteAll() throws SQLException {
        StatementStrategy st = new DeleteAllStatement();
        jdbcContextWithStatementStrategy(st);
    }

    public void add(User user) throws SQLException {
        StatementStrategy st = new AddStatement(user);
        jdbcContextWithStatementStrategy(st);
    }



    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = dataSource.getConnection();
            ps = stmt.makePreparedStatement(c);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ignored) {}
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException ignored) {}
            }
        }
    }
}
