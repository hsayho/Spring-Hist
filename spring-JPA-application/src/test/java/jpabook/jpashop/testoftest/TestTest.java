package jpabook.jpashop.testoftest;

import jpabook.jpashop.domain.test.Board;
import jpabook.jpashop.domain.test.Comment;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@DataJpaTest
@Transactional
class TestTest {

    @Autowired
    EntityManager em;

    @Test
    public void 테스트(){
        Comment comment1 = Comment.builder()
                .author("1")
                .content("test")
                .build();

        Comment comment2 = Comment.builder()
                .author("2")
                .content("test")
                .build();

        Comment comment3 = Comment.builder()
                .author("3")
                .content("test")
                .build();

        em.persist(comment1);
        em.persist(comment2);
        em.persist(comment3);

        Board board = Board.builder()
                .title("test")
                .build();

        board.addComment(comment1);
        board.addComment(comment2);
        board.addComment(comment3);

        em.persist(board);
        em.flush();

        Comment comment4 = Comment.builder()
                .author("4")
                .content("test")
                .build();

        Comment comment5 = Comment.builder()
                .author("5")
                .content("test")
                .build();
        em.persist(comment4);
        em.persist(comment5);
        System.out.println("board 새로 불러옴");
        Board board1 = em.find(Board.class, 1L);
        System.out.println(board1.getId());
        board1.addComment(comment4);
        board1.addComment(comment5);
        em.persist(board1);
        em.flush();
        Board board3 = em.find(Board.class, 1L);
        System.out.println("사이즈 : " + board3.getCommentList().size());
        System.out.println("2번째 board 불러옴");

        Board board2 = em.find(Board.class, 1L);
        List<Comment> commentList = board2.getCommentList();
        Comment comment6 = em.find(Comment.class, 1L);
        commentList.removeIf(comment -> comment.getId().equals(1L));
        em.remove(comment6);
        em.flush();
    }


}