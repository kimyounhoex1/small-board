package com.jungle.board.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public Long select(){
//        jdbcTemplate.query("select ")
//    }
}
