package com.example.tobysspring.user.dao.c3.v1;

import com.example.tobysspring.user.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDaoC3V1 {

    private DataSource dataSource;

    public UserDaoC3V1(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c= dataSource.getConnection();

        PreparedStatement ps = makeStatement(c);
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws SQLException, ClassNotFoundException {
        Connection c= dataSource.getConnection();

        PreparedStatement ps  = makeStatement(c);

        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();
        return user;
    }


    // Close 메서드가 제대로 실행되지 않아 문제가 생길 수 있음 따라서 try catch 로 close 시켜주는 로직이 필요함
    public void deleteAll() throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();
            ps = makeStatement(c);
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

    // 등록된 user  수를 가져오기 위한 count 메서드
    // try / catch/ finally 가 반복적으로 등장함
    public int getCount() throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = dataSource.getConnection();
            ps = makeStatement(c);
            rs = ps.executeQuery();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {}
            }
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
    abstract protected PreparedStatement makeStatement(Connection c) throws SQLException;
}
