package kr.ac.knu.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import kr.ac.knu.domain.Board;
import kr.ac.knu.domain.KnuUser;
import kr.ac.knu.dto.BoardDTO;
import kr.ac.knu.exception.BadRequestException;
import kr.ac.knu.repository.BoardRepository;
import kr.ac.knu.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by rokim on 2017. 5. 30..
 */
@RestController
@RequestMapping("/board-service")
@Slf4j
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "Page Number", required = false, dataType = "integer", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "Page Size", required = false, dataType = "integer", paramType = "query", defaultValue = "10")
    })
    @GetMapping(value = "/v1/boards")
    public Page<Board> findBoardList(@ApiIgnore @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10)  Pageable pageable) {
        Page<Board> boardPage = boardService.findBoards(pageable);
        return boardPage;
    }

    @GetMapping(value = "/v1/boards/{idx}")
    public Board findBoard(@PathVariable int idx) {
        Board board = boardRepository.findByIdx(idx);
        if (board == null) {
            throw new BadRequestException();
        }
        return board;
    }

    @PostMapping(value = "/v1/boards")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBoard(@RequestBody BoardDTO boardDTO, @ApiIgnore @AuthenticationPrincipal KnuUser knuUser) {
        Board board =  boardService.insertBoard(knuUser, boardDTO);
        log.info("{}", board);

    }

    @PutMapping(value = "/v1/boards/{idx}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBoard(@PathVariable int idx, @RequestBody BoardDTO boardDTO, @ApiIgnore @AuthenticationPrincipal KnuUser knuUser) {
        Board board =  boardService.updateBoard(knuUser, idx, boardDTO);
        log.info("{}", board);
    }

    @DeleteMapping(value = "/v1/boards/{idx}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoard(@PathVariable int idx, @ApiIgnore @AuthenticationPrincipal KnuUser knuUser) {
        Board board = boardService.deleteBoard(knuUser, idx);
        if (board == null) {
            throw new BadRequestException();
        }
    }

    @PostMapping(value = "/v1/boards/{idx}/like")
    @ResponseStatus(HttpStatus.CREATED)
    public void likeBoard(@PathVariable int idx, @ApiIgnore @AuthenticationPrincipal KnuUser knuUser) {
    }

    @PostMapping(value = "/v1/boards/{idx}/dislike")
    @ResponseStatus(HttpStatus.CREATED)
    public void disLikeBoard(@PathVariable int idx, @ApiIgnore @AuthenticationPrincipal KnuUser knuUser) {
    }
}
