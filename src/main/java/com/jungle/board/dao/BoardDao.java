package com.jungle.board.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    public boolean registerBoard(Board board, long id){
        long idx = board.getIdx();
        String title = board.getTitle();
        String contents = board.getContents();

        String sql = "insert into board "
                + "(idx, title, contents, created_by)  "
                + "values (?, ?, ?, ?)";
        int update;
        try {
            update = jdbcTemplate.update(sql, idx, title, contents, id);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage() + " 오류 발생");
            throw new RuntimeException(e);
        }

        if(update != 1){
            return false;
        }
        return true;
    }

    public Board getBoardByBoardId(long boardId) {
        String sql = "select * from board where idx = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{boardId}, (rs, rowNum) -> {
                Board board = new Board();
                board.setIdx(rs.getLong("idx"));
                board.setTitle(rs.getString("title"));
                board.setContents(rs.getString("contents"));
                board.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                board.setCreatByMemberId(rs.getLong("created_by")); // DB 컬럼명 맞춰야 함
                return board;
            });
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Board> getBoardByKeyword(String keyword){
        String sql = "select * from board where title like ? or contents like ?";
        try {
            List<Board> result = jdbcTemplate.query(sql,
                    new Object[]{"%" + keyword + "%", "%" + keyword + "%"},
                    new BeanPropertyRowMapper<>(Board.class));
            return result;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Board> getBoardByMemberId(long memberId) {
        String sql = "select * from board where created_by = ?";
        try {
            return jdbcTemplate.query(sql, new Object[]{memberId}, new BeanPropertyRowMapper<>(Board.class));
//            return jdbcTemplate.queryForObject(sql, new Object[]{memberId}, (rs, rowNum) -> {
//                Board board = new Board();
//                board.setIdx(rs.getLong("idx"));
//                board.setTitle(rs.getString("title"));
//                board.setContents(rs.getString("contents"));
//                board.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
//                board.setCreatByMemberId(rs.getLong("created_by")); // DB 컬럼명 맞춰야 함
//                return board;
//            });
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateBoard(Board board, long boardId) {
        String sql = "UPDATE board SET title = ?, contents = ? WHERE idx = ?";
        try {
            int updated = jdbcTemplate.update(sql, board.getTitle(), board.getContents(), boardId);
            return updated == 1;
        } catch (DataAccessException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBoard(long boardId) {
        String sql = "delete from board where idx = ?";
        try {
            int update = jdbcTemplate.update(sql, boardId);
            if(update != 1) {
                return false;
            }
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
