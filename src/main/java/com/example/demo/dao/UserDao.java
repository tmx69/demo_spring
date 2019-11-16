package com.example.demo.dao;

import entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbc;

    private NamedParameterJdbcTemplate namedJdbc;

    public UserDao(JdbcTemplate jdbc, NamedParameterJdbcTemplate namedJdbc) {
        this.jdbc = jdbc;
        this.namedJdbc = namedJdbc;
    }

    public int addUser(User user) {
        return jdbc.update(
                "INSERT INTO public.user(name, age) VALUES(? ,?)",
                user.getName(), user.getAge());
    }

    public List<User> getUser(Integer id) {
        return jdbc.query("SELECT * FROM public.user WHERE id = ?", new UserMapper(), id);
    }

    public List<User> getAllUsers() {
        return jdbc.query("SELECT * FROM public.user", new UserMapper());
    }



    public class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setAge(rs.getInt("age"));
            return user;
        }
    }

}
