package com.example.tobysspring.user.dao.c1.v2;

import java.sql.Connection;
import java.sql.SQLException;

public class NUserDao extends UserDaoV2 {

    @Override
    protected Connection getConnection() throws ClassNotFoundException, SQLException {

        return null;
    }
}
