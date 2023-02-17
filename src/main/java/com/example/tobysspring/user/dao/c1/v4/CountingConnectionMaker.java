package com.example.tobysspring.user.dao.c1.v4;

import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker{

    private final ConnectionMaker maker;

    int count=0;

    public CountingConnectionMaker(ConnectionMaker maker) {
        this.maker = maker;
    }

    @Override
    public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
        this.count++;
        return maker.makeNewConnection();
    }

    public int getCount() {
        return this.count;
    }
}
