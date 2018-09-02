package hu.kuncystem.mydata.dao.user;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import hu.kuncystem.mydata.dao.config.PGConfig;
import hu.kuncystem.mydata.pojo.user.User;

/**
 * Class Comment
 *
 * @author Csaba Kun <kuncy88@gmail.com>
 * @date Sep 2, 2018
 * 
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PGConfig.class })
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JDBCUserDaoTest {
    @Autowired
    @Qualifier(value = "JDBCUserDao")
    private UserDao userDao;

    @Test
    public void test1_addUser() {
        User user = userDao.getUser("test2@gmail.com", "abcd1234");
        if(user == null) {
            user = new User.UserBuilder(-1, "test2@gmail.com", "abcd1234")
                    .setAddress("5630 Békés, Test utca 3")
                    .setInaktiv(false)
                    .setName("Test John")
                    .build();
            user = userDao.addUser(user);
            System.out.println(user.getId());
        }else {
            System.out.println("user already exists!");
        }
    }
}
