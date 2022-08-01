package com.example.tobysspring.user;

import com.example.tobysspring.user.dao.v1.UserDao;
import com.example.tobysspring.user.dao.v3.ConnectionMaker;
import com.example.tobysspring.user.dao.v3.DaoFactory;
import com.example.tobysspring.user.dao.v3.UserDaoV3;
import com.example.tobysspring.user.dao.v4.UserDaoV4;
import com.example.tobysspring.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootTest
public class UserTest {

    @Test
    void userDaoTest() throws Exception{
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("kiomnd2");
        user.setName("김형익");
        user.setPassword("qwer1234");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());

        System.out.println("user2.getName() = " + user2.getName());

    }

    @Test
    void userDaoTestV3() throws Exception{

        ConnectionMaker dConnectionMaker = new ConnectionMaker() {
            @Override
            public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
                Class.forName("org.h2.Driver"); // 클래스 호출
                Connection c = DriverManager.getConnection(
                        "jdbc:h2:~/test", "sa", "");
                return c;
            }
        };
        UserDaoV3 dao = new UserDaoV3(dConnectionMaker);

        User user = new User();
        user.setId("kiomnd2");
        user.setName("김형익");
        user.setPassword("qwer1234");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());

        System.out.println("user2.getName() = " + user2.getName());

    }

    @Test
    void userDaoFactoryTest() throws Exception {
        UserDaoV3 dao = new DaoFactory().userDao();

        User user = new User();
        user.setId("kiomnd2");
        user.setName("김형익");
        user.setPassword("qwer1234");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());

        System.out.println("user2.getName() = " + user2.getName());
    }

    @Test
    void applicationContextTest() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(com.example.tobysspring.user.dao.v4.DaoFactory.class);
        UserDaoV4 dao = context.getBean("userDao", UserDaoV4.class);
        
    }
}
