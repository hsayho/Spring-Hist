package jpabook.jpashop.domain.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "board")
    /*@OneToMany
    @JoinColumn(name = "board_id")*/
    @Builder.Default
    private List<Comment> commentList = new ArrayList<>();

    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }
}
