package com.pansauce.security;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.pansauce.constants.query.UserQuery.GET_USER_BY_USERNAME;
import static com.pansauce.constants.rowMapper.ModelRowMapper.USER_ROW_MAPPER;

@Repository(value = "userRepo")
public class UserDetailsRepository {

    private final JdbcTemplate jdbc;

    public UserDetailsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public UserDetails loadUserByUsername(String username) {
        List<User> result = jdbc.query(GET_USER_BY_USERNAME, USER_ROW_MAPPER, username);
        return result.isEmpty() ? null : result.getFirst();
    }

}