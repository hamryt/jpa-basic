package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
//            Member member = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + member.getId());

            // 대상이 테이블이 아니고 객체이다.
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                .setFirstResult(1)
                .setMaxResults(8)
                .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
