package kr.ac.knu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by rokim on 2017. 5. 31..
 */
@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @ManyToOne(targetEntity = KnuUser.class)
    @JoinColumn(name="user_idx")
    private KnuUser knuUser;

    private String title;
    private String contents;

    @OneToMany(mappedBy = "parentBoard")
    @JsonIgnore
    private List<Comment> comments;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    private boolean isDel;
    private int countLike;
    private int countDisLike;

    @Override
    public String toString() {
        return String.format("[Board] IDX : %s, Title : %s, contents : %s, user : %s", idx, title, contents, knuUser.getName());
    }
}
