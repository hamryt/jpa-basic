package hellojpa;

import hellojpa.domain.Member;
import hellojpa.domain.Order;
import hellojpa.domain.OrderItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import org.hibernate.sql.ordering.antlr.OrderByAliasResolver;

/**
 * jpa에서 데이터가 조작되는 모든 단위는 transaction단위로 돼야한다.
 */
public class JpaMain {
        public static void main(String[] args) {
        // 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // thread간 공유하면 안된다. 한번 쓰고 버려야 한다.
        EntityManager em = emf.createEntityManager();
        // 데이터 변경은 트랜잭션 안에서만 이루어져야 한다.
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");

            em.persist(book);
            // 커밋을 해야 디비에 쿼리를 보낸다.

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); 
        }

        emf.close();
    }
}
