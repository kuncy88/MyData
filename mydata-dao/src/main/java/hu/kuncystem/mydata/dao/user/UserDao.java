package hu.kuncystem.mydata.dao.user;

import java.util.List;

import hu.kuncystem.mydata.pojo.user.User;

/**
 * Class Comment
 *
 * @author Csaba Kun <kuncy88@gmail.com>
 * @date Sep 2, 2018
 *  
 * @version 1.0
 */
public interface UserDao {
    public User addUser(User user);
    
    public User update(User user);
    
    public boolean remove(User user);
    
    public User getUser(String email, String password);
    
    public User getUser(long id);
    
    public List<User> getUsers(int limit, int offset, String order);
}
