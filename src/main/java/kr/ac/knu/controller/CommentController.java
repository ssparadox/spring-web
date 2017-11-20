package kr.ac.knu.controller;

import kr.ac.knu.domain.Board;
import kr.ac.knu.domain.KnuUser;
import kr.ac.knu.domain.Comment;
import kr.ac.knu.dto.CommentDTO;
import kr.ac.knu.exception.BadRequestException;
import kr.ac.knu.repository.BoardRepository;
import kr.ac.knu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Created by rokim on 2017. 6. 5..
 */
@RestController
@RequestMapping("/board-service")
public class CommentController {
    @Autowired private CommentService commentService;
    @Autowired private BoardRepository boardRepository;

    //전체보기
    @RequestMapping(value = "/v1/board/{idx}/comments", method = RequestMethod.GET)
    public List<Comment> viewEntireComment(@PathVariable("boardIdx") int boardIdx) {
        Board board = boardRepository.findByIdx(boardIdx);

        if (board == null) {
            throw new BadRequestException();
        }

        return commentService.findComments(board);
    }

    @PostMapping(value = "/v1/board/{idx}/comments")
    @ResponseStatus(HttpStatus.OK)
    public void createComment(@RequestBody CommentDTO commentDTO, @ApiIgnore @AuthenticationPrincipal KnuUser knuUser) {
        commentService.insertComment(knuUser, commentDTO);
    }

    @DeleteMapping(value = "/v1/board/{boardIdx}/comments/{commentIdx}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable int boardIdx, @PathVariable int commentIdx, @ApiIgnore @AuthenticationPrincipal KnuUser knuUser) {
        commentService.deleteComment(commentIdx, knuUser);
    }
}
