package kr.ac.knu.service;

import kr.ac.knu.domain.Board;
import kr.ac.knu.domain.KnuUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rokim on 2017. 6. 4..
 */
@Service
public class ExampleService {
    public List<Board> getDummyBoardList() {
        List<Board> boardList = new ArrayList<>();

        Board board = new Board();
        board.setIdx(1);
        board.setTitle("Title1");
        board.setKnuUser(new KnuUser() {{setName("Robin");}});
        boardList.add(board);

        Board board2 = new Board();
        board2.setIdx(2);
        board2.setTitle("Title2");
        board2.setKnuUser(new KnuUser() {{setName("Tony");}});
        boardList.add(board2);

        Board board3 = new Board();
        board3.setIdx(3);
        board3.setTitle("Title3");
        board3.setKnuUser(new KnuUser() {{setName("Ian");}});
        boardList.add(board3);

        return boardList;
    }
}
