package com.jungle.board.dao;

import java.util.List;

import com.jungle.board.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Member> getAllMembers(){
        String sql = "select * from member";
        List<Member> members = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class));
        return members;
    }
}
