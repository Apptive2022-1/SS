package team3.OneSubscribe.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team3.OneSubscribe.domain.Answer;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.domain.Tag;
import team3.OneSubscribe.domain.Writing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnswerRepository {

    @PersistenceContext
    private EntityManager em;

    public long save(Answer answer){
        em.persist(answer);
        return answer.getId();
    }

    public Answer findOneById(Long id){
        return em.find(Answer.class, id);
    }

    public Answer findOneByNickName(String nickName){
        return em.createQuery("select i from Answer i where i.nickName = : nickName", Answer.class)
                .setParameter("nickName", nickName)
                .getSingleResult();
    }

    public List<Answer> findAll(){
        return em.createQuery("select i from Answer i", Answer.class)
                .getResultList();
    }

    public List<Answer> findByWriting(Writing writing){
        return em.createQuery("select i from Answer i where i.writing = :writing", Answer.class)
                .setParameter("writing", writing)
                .getResultList();
    }

    // writing의 answer 수 제공하기
    public int findAnswerNumberByWriting(Writing w){
        List<Answer> answers =  em.createQuery("select i from Answer i where i.writing = :writing", Answer.class)
                .setParameter("writing", w)
                .getResultList();

        return answers.size();
    }

    //삭제 기능 // 테스트 해야 함
    public void deleteOne(Long id){
        em.createQuery("delete from Answer i where i.id = : id", Answer.class)
                .setParameter("id", id)
                .executeUpdate();
        return;
        //em.getTransaction().commit();
    }
}
