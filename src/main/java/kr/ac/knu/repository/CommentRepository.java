package kr.ac.knu.repository;

import kr.ac.knu.domain.Board;
import kr.ac.knu.domain.KnuUser;
import kr.ac.knu.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by rokim on 2017. 5. 31..
 */
public interface CommentRepository extends JpaRepository<Comment, Integer>{
    Comment findByIdx(int idx);
    Comment findByIdxAndKnuUser(int idx, KnuUser knuUser);
    List<Comment> findAllByParentBoard(Board board);
}
