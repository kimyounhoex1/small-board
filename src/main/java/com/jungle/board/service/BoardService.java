package com.jungle.board.service;

import com.jungle.board.domain.Board;
import com.jungle.board.repository.BoardRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BoardService {
  @Autowired
  private BoardRepository boardRepository;

  public List<Board> getAllBoard(){
    return boardRepository.getAllBoards();
  }

  public boolean registerBoard(Board board, long id){
    return boardRepository.registerBoard(board, id);
  }

  public Board getBoardByBoardId(long boardId) {
    Board resultBoard = boardRepository.getBoardByBoardId(boardId);
    return resultBoard;
  }

  public boolean updateBoard(Board board, long boardId) {
//    boardDao.getBoardByTitle()
    return boardRepository.updateBoard(board, boardId);
  }

  public boolean deleteBoard(long boardId) {
    return boardRepository.deleteBoard(boardId);
  }

  public List<Board> getBoardByKeyword(String keyword) {

    return boardRepository.getBoardByKeyword(keyword);
  }

  public List<Board> getBoardByMemberId(long memberId) {
    List<Board> findBoards = boardRepository.getBoardByMemberId(memberId);
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
