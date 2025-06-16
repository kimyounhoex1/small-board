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
}
