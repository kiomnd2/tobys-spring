package com.example.tobysspring.user.dao.v2;

import com.example.tobysspring.user.dao.v1.UserDao;

import java.sql.Connection;
import java.sql.SQLException;

public class NUserDao extends UserDaoV2 {

    @Override
    protected Connection getConnection() throws ClassNotFoundException, SQLException {

        return null;
    }
}
