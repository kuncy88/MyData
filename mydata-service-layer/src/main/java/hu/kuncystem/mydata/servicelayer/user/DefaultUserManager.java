package hu.kuncystem.mydata.servicelayer.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import hu.kuncystem.mydata.dao.user.UserDao;
import hu.kuncystem.mydata.pojo.user.User;

/**
 * Class Comment
 *
 * @author Csaba Kun <kuncy88@gmail.com>
 * @date Sep 10, 2018
 *  
 * @version 1.0
 */
@Service
@Scope("prototype")
public class DefaultUserManager implements UserManager{
    
    @Autowired
    @Qualifier("JDBCUserDao")
    private UserDao userDao;
    /* (non-Javadoc)
     * @see hu.kuncystem.mydata.servicelayer.user.UserManager#getAllUsers(int, int, java.lang.String)
     */
    public List<User> getAllUsers(int limit, int offset, String order) {
        try {
            return userDao.getUsers(limit, offset, order);
        }catch(Exception e) {
            return new ArrayList<User>();
        }
    }

    /* (non-Javadoc)
     * @see hu.kuncystem.mydata.servicelayer.user.UserManager#getUser(long)
     */
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    /* (non-Javadoc)
     * @see hu.kuncystem.mydata.servicelayer.user.UserManager#getUser(java.lang.String, java.lang.String)
     */
    public User getUser(String email, String password) {
        // TODO Auto-generated method stub
        return userDao.getUser(email, password);
    }

}
