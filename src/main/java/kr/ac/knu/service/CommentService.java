package kr.ac.knu.service;

import kr.ac.knu.domain.Board;
import kr.ac.knu.domain.KnuUser;
import kr.ac.knu.domain.Comment;
import kr.ac.knu.dto.CommentDTO;
import kr.ac.knu.exception.BadRequestException;
import kr.ac.knu.repository.BoardRepository;
import kr.ac.knu.repository.CommentRepository;
import kr.ac.knu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by rokim on 2017. 6. 5..
 */
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;

    public Comment insertComment(KnuUser knuUser, CommentDTO commentDTO) {
        if (!isCommentValidationRight(commentDTO)) {
            throw new BadRequestException();
        }

        Comment comment = new Comment();
        comment.setKnuUser(knuUser);
        comment.setParentBoard(boardRepository.findByIdxAndIsDel(commentDTO.getBoardIdx(), false));
        Comment parentComment = commentRepository.findByIdx(commentDTO.getParentIdx());

        int depth = 1;
        if (parentComment != null) {
            depth += parentComment.getDepth();
            comment.setParentComment(parentComment);
        }

        comment.setDepth(depth);
        comment.setComment(commentDTO.getComment());
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());
        comment.setGoodCount(0);
        comment.setBadCount(0);

        return commentRepository.save(comment);
    }

    public void deleteComment(int idx, KnuUser knuUser) {
        Comment comment = commentRepository.findByIdxAndKnuUser(idx, knuUser);
        commentRepository.delete(comment);
    }

    public void recommendComment(int idx, KnuUser knuUser){
        Comment comment = commentRepository.findByIdx(idx);

        if(isRecommendAndNoRecommendServiceRight(comment, knuUser)) {
            comment.setBadCount(comment.getGoodCount() + 1);
            knuUser.setOneDayGoodAndBadCount(knuUser.getOneDayGoodAndBadCount()+1);
            commentRepository.save(comment);
            userRepository.save(knuUser);
        }else {
            throw new BadRequestException();
        }
    }

    public void noRecommendComment(int idx, KnuUser knuUser){
        Comment comment = commentRepository.findByIdx(idx);

        if(isRecommendAndNoRecommendServiceRight(comment, knuUser)) {
            comment.setBadCount(comment.getBadCount() + 1);
            knuUser.setOneDayGoodAndBadCount(knuUser.getOneDayGoodAndBadCount()+1);
            commentRepository.save(comment);
            userRepository.save(knuUser);
        }else {
            throw new BadRequestException();
        }
    }

    public boolean isCommentValidationRight(CommentDTO commentDTO) {
        if (isBoardNotExist(commentDTO)) {
            return false;
        } else if (isChildAndParentCommentHasDifferentBoardIdx(commentDTO)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isBoardNotExist(CommentDTO commentDTO) {
        return !boardRepository.exists(commentDTO.getBoardIdx());
    }

    public boolean isChildAndParentCommentHasDifferentBoardIdx(CommentDTO commentDTO) {
        if (commentDTO.getParentIdx() == 0) {
            return false;
        }

        Comment parentComment = commentRepository.findByIdx(commentDTO.getParentIdx());
        if (parentComment == null || parentComment.getParentBoard().getIdx() != commentDTO.getBoardIdx()) {
            return true;
        }

        return false;
    }

    public boolean isRecommendAndNoRecommendServiceRight(Comment comment, KnuUser knuUser) {
        Calendar calendar = Calendar.getInstance();
        Calendar latestGoodAndBadCalendar = Calendar.getInstance();
        latestGoodAndBadCalendar.setTime(knuUser.getLastestGoodAndBadAt());

        if(isKnuUserDayLowerThanNowDay(calendar, latestGoodAndBadCalendar)) {
            knuUser.setLastestGoodAndBadAt(calendar.getTime());
            knuUser.setOneDayGoodAndBadCount(0);
            userRepository.save(knuUser);
        }

        if(comment != null && knuUser.getOneDayGoodAndBadCount() < 5) {
            return true;
        }else {
            return false;
        }
    }

    public boolean isKnuUserDayLowerThanNowDay(Calendar nowCalendar, Calendar knuUserCalendar) {
        if(nowCalendar.get(Calendar.YEAR) > knuUserCalendar.get(Calendar.YEAR) ) {
            return true;
        }

        if(nowCalendar.get(Calendar.MONTH) > knuUserCalendar.get(Calendar.MONTH)) {
            return true;
        }

        if(nowCalendar.get(Calendar.DATE) > knuUserCalendar.get(Calendar.DATE)) {
            return true;
        }

        return false;
    }

    //코멘트 보기
    public List<Comment> findComments(Board parentBoard) {
        List<Comment> commentList = commentRepository.findAllByParentBoard(parentBoard);
        Comparator<Comment> comparator = new Comparator<Comment>(){

            @Override
            public int compare(Comment o1, Comment o2) {
                return o1.getCreatedAt().before(o2.getCreatedAt()) ? -1 : o1.getCreatedAt().after(o2.getCreatedAt()) ? 1:0;
            }
        };

        if(!commentList.isEmpty()) {
            Collections.sort(commentList, comparator);
        }
        return commentList;
    }
}