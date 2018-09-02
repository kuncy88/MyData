package hu.kuncystem.mydata.pojo.user;

import java.util.Date;

/**
 * Builder Design Pattern
 *
 * @author Csaba Kun <kuncy88@gmail.com>
 * @date Sep 2, 2018
 *  
 * @version 1.0
 */
public class User {
    private long id;
    
    private String name;
    private String email;
    private String password;
    private String address;
    
    private Date lastLogin;
    private Date createdDate;
    
    private boolean inaktiv = false;
    
    private User(UserBuilder builder) {
        this.id = builder.id;
        
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.address = builder.address;
        
        this.lastLogin = builder.lastLogin;
        this.createdDate = builder.createdDate;
        
        this.inaktiv = builder.inaktiv;
    }
    
    
    
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }



    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }



    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }



    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }



    /**
     * @return the lastLogin
     */
    public Date getLastLogin() {
        return lastLogin;
    }



    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }



    /**
     * @return the inaktiv
     */
    public boolean isInaktiv() {
        return inaktiv;
    }



    public static final class UserBuilder{
        private long id;
        
        private String name;
        private String email;
        private String password;
        private String address;
        
        private Date lastLogin;
        private Date createdDate;
        
        private boolean inaktiv;
        
        public UserBuilder(long id, String email, String password) {
            this.id = id;
            this.email = email;
            this.password = password;
        }
        
        public UserBuilder(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.password = user.getPassword();
            
            setName(user.getName());
            setAddress(user.getAddress());
            setLastLogin(user.getLastLogin());
            setCreatedDate(user.getCreatedDate());
            setInaktiv(user.isInaktiv());
        }
        
        

        /**
         * @param id the id to set
         */
        public UserBuilder setId(long id) {
            this.id = id;
            
            return this;
        }

        /**
         * @param email the email to set
         */
        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        /**
         * @param password the password to set
         */
        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * @param name the name to set
         */
        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * @param address the address to set
         */
        public UserBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        /**
         * @param lastLogin the lastLogin to set
         */
        public UserBuilder setLastLogin(Date lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        /**
         * @param createdDate the createdDate to set
         */
        public UserBuilder setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        /**
         * @param inaktiv the inaktiv to set
         */
        public UserBuilder setInaktiv(boolean inaktiv) {
            this.inaktiv = inaktiv;
            return this;
        }
        
        public User build() {
            return new User(this);
        }
    }
}
