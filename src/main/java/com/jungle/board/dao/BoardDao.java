package com.jungle.board.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.jungle.board.domain.Board;

@Repository
public class BoardDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Board> getAllBoards(){
        String sql = "select * from board";
        List<Board> boards = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Board.class));

        return boards;
    }
}
