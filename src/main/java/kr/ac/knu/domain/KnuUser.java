package kr.ac.knu.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rokim on 2017. 5. 18..
 */
@Data
@Entity
public class KnuUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @Column(unique = true)
    private String userId;

    private String name;
    private String email;
    private String gender;
    private String pictureUrl;

    private int oneDayGoodAndBadCount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastestGoodAndBadAt;

    private long point;

    private int countLike;
    private int countDisLike;
    @Temporal(TemporalType.TIMESTAMP)
    private Date likeAt;

}
