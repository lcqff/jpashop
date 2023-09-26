package jpabook.jpashop;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import jpabook.jpashop.domain.Member;

public class JpaMain {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaShop");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      Member member = new Member();
      member.setUsername("memberA");
      member.setAge(20);
      em.persist(member);

      //TypeQuery, Query
      TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class) ;
      Query query2 = em.createQuery("select m.username, m.age from Member m") ;

      //결과 조회 api
      TypedQuery<Member> singleResQuery = em.createQuery("select m from Member m where m.id = 1", Member.class);
      List<Member> members = query.getResultList();
      Member singleMember = singleResQuery.getSingleResult();

      //파라미터 바인딩
      Member member1 =
          em.createQuery("select m from Member m where m.username = :username", Member.class)
              .setParameter("username","memberA")
              .getSingleResult();
      System.out.println("파라미터 바인딩:" + member1.getUsername());


      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }
    emf.close();
  }
}
