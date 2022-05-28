package jpabook.jpashop.domain.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    private String content;

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;

    /*@Column(name = "board_id")
    private Long boardId;*/
}
