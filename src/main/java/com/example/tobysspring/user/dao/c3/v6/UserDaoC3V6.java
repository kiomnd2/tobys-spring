package com.example.tobysspring.user.dao.c3.v6;

import com.example.tobysspring.user.dao.c3.v5.JdbcContext;
import com.example.tobysspring.user.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// 전략패턴 적용
public class UserDaoC3V6 {

    public UserDaoC3V6(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    };

    public void deleteAll() {
        jdbcTemplate.update("delete from users");
    }

    public int getCount() {
        return jdbcTemplate.queryForObject("select count(1) from users", Integer.class);
    }

    public void add(User user) {
        this.jdbcTemplate.update("insert into users(id,name,password) values (?,?,?)",
        user.getId(), user.getName(), user.getPassword());
    }

    public User get(String id) {
        return jdbcTemplate.queryForObject("select * from users where id = ?", new Object[] {id}, this.userMapper);
    }

    public List<User> getAll() {
        return jdbcTemplate.query("select * from users order by id", this.userMapper);
    }

}
