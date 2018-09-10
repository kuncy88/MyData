package hu.kuncystem.mydata.servicelayer.user;

import java.util.List;

import hu.kuncystem.mydata.pojo.user.User;

/**
 * Class Comment
 *
 * @author Csaba Kun <kuncy88@gmail.com>
 * @date Sep 10, 2018
 *  
 * @version 1.0
 */
public interface UserManager {
    public List<User> getAllUsers(int limit, int offset, String order);
    
    public User getUser(long id);
    
    public User getUser(String email, String password);
    
}
