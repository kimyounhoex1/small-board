package com.jungle.board.controller;

import com.jungle.board.domain.Board;
import com.jungle.board.service.BoardService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board")
    public List<Board>  getAllBoards(){
        List<Board> allBoard = boardService.getAllBoard();
        System.out.println(allBoard);
        return allBoard;
    }
}
