package com.jungle.board.dao;

import com.jungle.board.domain.Member;
import com.mysql.cj.protocol.Resultset;
import java.util.List;
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
        List<Member> name = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class));

        return name;
    }
}
