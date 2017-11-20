package kr.ac.knu.repository;

import kr.ac.knu.domain.Board;
import kr.ac.knu.domain.KnuUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rokim on 2017. 5. 31..
 */
public interface BoardRepository extends JpaRepository<Board, Integer>{
    Page<Board> findByIsDel(boolean isDel, Pageable pageable);
    Board findByIdx(int idx);
    Board findByIdxAndIsDel(int idx, boolean isDel);
    Board findByIdxAndKnuUser(int idx, KnuUser knuUser);
}
