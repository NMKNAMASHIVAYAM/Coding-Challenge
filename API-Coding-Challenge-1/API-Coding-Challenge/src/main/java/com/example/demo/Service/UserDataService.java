package com.example.demo.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.demo.Model.UserData;
import com.example.demo.Model.UserDataDetails;
import com.example.demo.repository.UserDataRepository;


@Service
public class UserDataService implements UserDetailsService {

	
	@Autowired
     private UserDataRepository repository;

	@Autowired
	private JdbcTemplate jdbc;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserData> userDetail = repository.findByName(username);
        return userDetail.map(UserDataDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(UserData userData) {
    	userData.setEmail(userData.getEmail());
		userData.setName(userData.getName());
		
		userData.setPassword(new BCryptPasswordEncoder().encode(userData.getPassword()));
		userData.setRoles(userData.getRoles());
		repository.save(userData);
        return "User Added Successfully...";
    }
    public UserData searchByUserName(String userName) {
        String cmd = "SELECT * FROM user_data WHERE name = ?"; 
        List<UserData> userList = jdbc.query(cmd, new Object[]{userName}, new RowMapper<UserData>() {
            @Override
            public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserData user = new UserData();
                user.setUid(rs.getInt("uid")); 
                user.setName(rs.getString("name")); 
                user.setPassword(rs.getString("password"));
                user.setRoles(rs.getString("role"));
               
                return user;
            }
        });
        
        if (!userList.isEmpty()) {
            return userList.get(0);
        }
        return null;
    }


	
	public int login(String user, String pwd) {
	    
	    String cmd = "select password from user_data where name = ?";
	    
	    List<Object> list = jdbc.query(cmd, new Object[] {user}, new RowMapper<Object>() {

	        @Override
	        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	            // Retrieve the encrypted password from the result set
	            Object encryptedPassword = rs.getString("password");
	            return encryptedPassword;
	        }
	    });

	    if (list.isEmpty()) {
	       
	        System.out.println("User not found");
	        return 0;
	    }

	   
	    String encryptedPassword = (String) list.get(0);
	    System.out.println("Encrypted password from DB: " + encryptedPassword);

	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    if (passwordEncoder.matches(pwd, encryptedPassword)) {
	     
	        System.out.println("Login successful");
	        return 1;
	    } else {
	     
	        System.out.println("Login failed");
	        return 0;
	    }
	}

}
