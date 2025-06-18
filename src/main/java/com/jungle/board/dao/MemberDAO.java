package com.jungle.board.dao;

import com.jungle.board.domain.Board;
import java.util.List;

import com.jungle.board.domain.Member;
import javax.xml.crypto.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Member> getAllMembers() {
        String sql = "select * from member";
        List<Member> members = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Member.class));
        return members;
    }

    public Member findMemberById(Long memberId) {
        String sql = "select * from member where id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{memberId}, (rs, rowNum) -> {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                member.setNickname(rs.getString("nickname"));
                member.setPassword(rs.getString("password"));
                member.setAge(rs.getInt("age"));
                return member;
            });
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean join(Member member) {
        String sql = "insert into member (name, nickname, password, age) values(?, ?, ?, ?)";
        int update;
        try {
            update = jdbcTemplate.update(sql,
                    member.getName(),
                    member.getNickname(),
                    member.getPassword(),
                    member.getAge());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
        if (update != 1) {
            return false;
        }
        return true;
    }

    public boolean isDuplicateNickname(String nickname){
        String sql = "select Count(*) from member where nickname=?";
        int count;
        try {
            count = jdbcTemplate.queryForObject(sql, Integer.class, nickname);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
        return count > 0;
    }


    public String findPasswordByNickname(String nickname) {
        String sql = "SELECT password FROM member WHERE nickname = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, nickname);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}