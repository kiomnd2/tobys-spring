package com.example.tobysspring.user.dao.c1.v0;

import com.example.tobysspring.user.domain.User;

import java.sql.*;

public class UserDaoV0 {

    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver"); // 클래스 호출
        Connection c = DriverManager.getConnection(
                "jdbc:h2:~/test", "sa", "");

        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver"); // 클래스 호출
        Connection c = DriverManager.getConnection(
                "jdbc:h2:~/test", "sa", "");

        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
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

}
