package kr.ac.knu.repository;

import kr.ac.knu.domain.Board;

import java.util.List;

/**
 * Created by rokim on 2017. 6. 9..
 */
public interface MybatisRepositoryXml {
    List<Board> selectBoardList(int size);
}
