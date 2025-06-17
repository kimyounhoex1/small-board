package com.jungle.board.controller;

import com.jungle.board.domain.Board;
import com.jungle.board.service.BoardService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/api/board"))
public class BoardController {

    @Autowired
    private BoardService boardService;

    /**
     * 전체 게시글 조회
     * 요청: localhost:8080/api/board
     * @return
     */
    @GetMapping
    public List<Board>  getAllBoards(){
        List<Board> allBoard = boardService.getAllBoard();
        System.out.println(allBoard);
        return allBoard;
    }
    /**
     * 게시글 boardId 조회
     * 요청: localhost:8080/api/board/boardid/1
     * @param boardId
     * @return
     */
    @GetMapping("/boardid/{boardId}")
    public Board getBoardByBoardId(@PathVariable long boardId){
        Board findBoard = boardService.getBoardByBoardId(boardId);
        return findBoard;
    }

    /**
     * 게시글 memberId 조회
     * 요청: localhost:8080/api/board/memberid/1
     * @param memberId
     * @return
     */
    @GetMapping("/memberid/{memberId}")
    public List<Board> getBoardByMemberId(@PathVariable long memberId){
        List<Board> findBoard = boardService.getBoardByMemberId(memberId);
        return findBoard;
    }
    /**
     * 게시글 등록
     * 요청: localhost:8080/api/write
     * dummy body:
     * {
     *     "contents": "updated",
     *     "title": "hi",
     *     "creatByMemberId": "1"
     * }
     * @param board
     * @return bool
     */
     @PostMapping("/write")
     public boolean registerBoard(@RequestBody Board board){
        long id = 1;
        return boardService.registerBoard(board, id);
     }
    /**
     * 게시글 수정 ( ID 수정 필요, Create_AT 시간 설정 현재 시간으로 매번 갱신 필요)
     * @param board, boardid
     * @return bool
     */
     @PutMapping("/update/{boardId}")
     public boolean updateBoard(@RequestBody Board board, @PathVariable long boardId){
         return boardService.updateBoard(board, boardId);
     }
    /**
     * 게시글 삭제 ( 추가, 세션있는지, 아이디 있는지에 대한 절차 필요)
     * @param boardId
     * @return bool
     */
     @DeleteMapping("/delete/{boardId}")
     public boolean deleteBoard(@PathVariable long boardId){
        return boardService.deleteBoard(boardId);
     }
    /**
     * 게시글 등록 ( 키워드에 맞춰서 검색 기능 구현 )
     * @param keyword
     * @return List<Board>
     */
     @GetMapping("/search/{keyword}")
     public List<Board> searchBoardByKeyword(@RequestParam("keyword") String keyword){
         List<Board> list = boardService.getBoardByKeyword(keyword);
         return list;
     }
    /**
     * 게시글 등록
     * @param page, size
     * @return List<Board>
     */
     @GetMapping("/page")
    public List<Board> getBoardByPage(@RequestParam int page, @RequestParam int size){
         return boardService.getBoardByPage(page, size);
     }




















    /**
     * 게시글 memberId별 board 조회
     * @param memberId
     * @return
     */
//    @GetMapping("/memberid/{memberId}")
//    public Board getBoardByMemberId(@PathVariable long memberId){
//        Board findBoard = boardService.getBoardByMemberId(memberId);
//        return findBoard;
//    }


}
