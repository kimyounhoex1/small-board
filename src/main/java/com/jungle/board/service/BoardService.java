package com.jungle.board.service;

import com.jungle.board.dao.BoardDAO;
import com.jungle.board.domain.Board;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BoardService {
  @Autowired
  private BoardDAO boardDAO;

  public List<Board> getAllBoard(){
    return boardDAO.getAllBoards();
  }

  public boolean registerBoard(Board board, long id){
    return boardDAO.registerBoard(board, id);
  }

  public Board getBoardByBoardId(long boardId) {
    Board resultBoard = boardDAO.getBoardByBoardId(boardId);
    return resultBoard;
  }

  public boolean updateBoard(Board board, long boardId) {
//    boardDAO.getBoardByTitle()
    return boardDAO.updateBoard(board, boardId);
  }

  public boolean deleteBoard(long boardId) {
    return boardDAO.deleteBoard(boardId);
  }

  public List<Board> getBoardByKeyword(String keyword) {

    return boardDAO.getBoardByKeyword(keyword);
  }

  public List<Board> getBoardByPage(int page, int size) {
    return null;
  }


  public List<Board> getBoardByMemberId(long memberId) {
    List<Board> findBoards = boardDAO.getBoardByMemberId(memberId);
    return findBoards;
  }
}
