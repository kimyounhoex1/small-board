package com.jungle.board.service;

import com.jungle.board.dao.BoardDao;
import com.jungle.board.domain.Board;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BoardService {
  @Autowired
  private BoardDao boardDao;

  public List<Board> getAllBoard(){
    return boardDao.getAllBoards();
  }

  public boolean registerBoard(Board board, long id){
    return boardDao.registerBoard(board, id);
  }

  public Board getBoardByBoardId(long boardId) {
    Board resultBoard = boardDao.getBoardByBoardId(boardId);
    return resultBoard;
  }

  public boolean updateBoard(Board board, long boardId) {
//    boardDao.getBoardByTitle()
    return boardDao.updateBoard(board, boardId);
  }

  public boolean deleteBoard(long boardId) {
    return boardDao.deleteBoard(boardId);
  }

  public List<Board> getBoardByKeyword(String keyword) {

    return boardDao.getBoardByKeyword(keyword);
  }

  public List<Board> getBoardByMemberId(long memberId) {
    List<Board> findBoards = boardDao.getBoardByMemberId(memberId);
    return findBoards;
  }

  /**
   * TODO, 아직 미구현
   * @param page
   * @param size
   * @return
   */
  public List<Board> getBoardByPage(int page, int size) {
    return null;
  }


}
