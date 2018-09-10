package hu.kuncystem.mydata.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import hu.kuncystem.mydata.pojo.user.User;

/**
 * Class Comment
 *
 * @author Csaba Kun <kuncy88@gmail.com>
 * @date Sep 2, 2018
 * 
 * @version 1.0
 */
@Repository
public class JDBCUserDao implements UserDao {
    private static final String SQL_INSERT = "INSERT INTO users (email, password, name, address, inaktiv) VALUES (?, ?, ?, ?, ?);";

    private static final String SQL_SELECT = "SELECT u.* FROM users u WHERE $CONDITION$";

    private final static class UserMapper implements RowMapper<User> {

        /*
         * (non-Javadoc)
         * 
         * @see
         * org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
         * int)
         */
        public User mapRow(ResultSet result, int row) throws SQLException {
            return new User.UserBuilder(result.getLong("id"), result.getString("email"), result.getString("password"))
                    .setName(result.getString("name"))
                    .setAddress(result.getString("address"))
                    .setInaktiv(result.getBoolean("inaktiv"))
                    .setCreatedDate(result.getDate("created_date"))
                    .setLastLogin(result.getDate("last_login"))
                    .build();
        }

    }

    @Autowired
    private JdbcOperations jdbc;

    /*
     * (non-Javadoc)
     * 
     * @see
     * hu.kuncystem.mydata.dao.user.UserDao#add(hu.kuncystem.mydata.pojo.user.
     * User)
     */
    public User addUser(final User user) {
        KeyHolder holder = new GeneratedKeyHolder();
        int rows = 0;

        rows = jdbc.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[] { "id" });

                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getName());
                ps.setString(4, user.getAddress());
                ps.setBoolean(5, user.isInaktiv());

                return ps;
            }
        }, holder);
        if (rows > 0) {
            return new User.UserBuilder(user).setId(holder.getKey().longValue()).build();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * hu.kuncystem.mydata.dao.user.UserDao#update(hu.kuncystem.mydata.pojo.user
     * .User)
     */
    public User update(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * hu.kuncystem.mydata.dao.user.UserDao#remove(hu.kuncystem.mydata.pojo.user
     * .User)
     */
    public boolean remove(User user) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see hu.kuncystem.mydata.dao.user.UserDao#getUser(java.lang.String,
     * java.lang.String)
     */
    public User getUser(String email, String password) {
        String sql = SQL_SELECT.replace("$CONDITION$", " u.email = ? AND u.password = ?");
        try {
            return jdbc.queryForObject(sql, new UserMapper(), email, password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see hu.kuncystem.mydata.dao.user.UserDao#getUser(long)
     */
    public User getUser(long id) {
        String sql = SQL_SELECT.replace("$CONDITION$", " u.id = ?");
        try {
            return jdbc.queryForObject(sql, new UserMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see hu.kuncystem.mydata.dao.user.UserDao#getUsers()
     */
    public List<User> getUsers(int limit, int offset, String order) {
        String sql = SQL_SELECT;
        if (order != null) {
            sql += " ORDER BY " + order;
        }
        if (limit > -1) {
            sql += " LIMIT " + limit;
        }
        if (offset > -1) {
            sql += " OFFSET " + offset;
        }
        try {
            return jdbc.query(sql, new UserMapper());
        } catch(Exception e) {
            return null;
        }
    }

}
