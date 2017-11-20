package kr.ac.knu.service;

import kr.ac.knu.domain.Board;
import kr.ac.knu.domain.KnuUser;
import kr.ac.knu.dto.BoardDTO;
import kr.ac.knu.repository.BoardRepository;
import kr.ac.knu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rokim on 2017. 5. 31..
 */
@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;

    public Page<Board> findBoards(Pageable pageable) {
        return boardRepository.findByIsDel(false, pageable);
    }

    public Board insertBoard(KnuUser knuUser, BoardDTO boardDTO) {
        Board board = new Board();
        board.setKnuUser(knuUser);
        board.setTitle(boardDTO.getTitle());
        board.setContents(boardDTO.getContents());
        board.setCreatedAt(new Date());
        board.setUpdatedAt(new Date());
        board.setDel(false);
        board.setCountLike(0);
        board.setCountDisLike(0);

        return boardRepository.save(board);
    }

    public Board updateBoard(KnuUser knuUser, int idx, BoardDTO boardDTO) {
        Board board = boardRepository.findByIdxAndKnuUser(idx, knuUser);
        if(board == null || board.isDel()) {
            return null;
        }

        board.setTitle(boardDTO.getTitle());
        board.setContents(boardDTO.getContents());
        board.setUpdatedAt(new Date());

        return boardRepository.save(board);
        
    }

    public Board deleteBoard(KnuUser knuUser, int idx) {
        Board board = boardRepository.findByIdxAndKnuUser(idx, knuUser);
        if(board == null || board.isDel()) {
            return null;
        }
        board.setDel(true);
        return boardRepository.save(board);
    }
}
