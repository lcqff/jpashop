package jpabook.jpashop;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.MemberDTO;
import jpabook.jpashop.domain.Team;

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
      Team team = new Team();
      team.setName("teamA");
      team.getMember().add(member);
      member.changeTeam(team);

      em.persist(member);
      em.persist(team);

      /** TypeQuery, Query **/
      TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class) ;
      Query query2 = em.createQuery("select m.username, m.age from Member m") ;

      /** 결과 조회 api **/
      TypedQuery<Member> singleResQuery = em.createQuery("select m from Member m where m.id = 1", Member.class);
      List<Member> members = query.getResultList();
      Member singleMember = singleResQuery.getSingleResult();

      /** 파라미터 바인딩 **/
      Member member1 =
          em.createQuery("select m from Member m where m.username = :username", Member.class)
              .setParameter("username","memberA")
              .getSingleResult();
      System.out.println("파라미터 바인딩:" + member1.getUsername());

     System.out.println("=============================================");
      /**명시적 JOIN 사용 **/
//      Team team1 =
//          em.createQuery("select m.team from Member m", Team.class)
//              .getSingleResult();
      Team team1 = em.createQuery("select m.team from Member m join m.team t", Team.class)
          .getSingleResult();


      /** 프로젝션 - 여러값 조회 **/
      //Query 타입으로 조회
      List resultList = em.createQuery("SELECT m.username, m.age FROM Member m")
              .getResultList();
      Object o = resultList.get(0);
      Object[] result = (Object[]) o;
      System.out.println("username: " + result[0]);
      System.out.println("age: " + result[1]);

      //Object[] 타입으로 조회
      List<Object[]> resultList2 = em.createQuery("SELECT m.username, m.age FROM Member m")
              .getResultList();
      Object[] result2 = (Object[]) o;
      System.out.println("username: " + result2[0]);
      System.out.println("age: " + result2[1]);

      //new 명령어로 조회
      List<MemberDTO> result3 = em.createQuery("SELECT new jpabook.jpashop.domain.MemberDTO(m.username, m.age) FROM Member m", MemberDTO.class)
          .getResultList();
      MemberDTO memberDTO = result3.get(0);
      System.out.println("username: " + memberDTO.getUsername());
      System.out.println("age: " + memberDTO.getAge());

      /** 페이징 API **/
      for (int i=0; i<10; i++) {
        Member m = new Member();
        m.setUsername("Member" + i);
        m.setAge(i);
        em.persist(m);
      }
      List<Member> pagingRes = em.createQuery("select m from Member m order by m.age desc ", Member.class)
              .setFirstResult(1)
              .setMaxResults(5)
              .getResultList();
      for (Member m:pagingRes) {
        System.out.println("paging member: " + m.getUsername());
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
