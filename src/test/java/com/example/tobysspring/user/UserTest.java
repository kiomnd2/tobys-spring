package com.example.tobysspring.user;

import com.example.tobysspring.user.dao.UserDao;
import com.example.tobysspring.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
}
