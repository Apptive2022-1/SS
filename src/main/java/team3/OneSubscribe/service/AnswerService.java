package team3.OneSubscribe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team3.OneSubscribe.domain.Answer;
import team3.OneSubscribe.domain.Writing;
import team3.OneSubscribe.repository.AnswerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {


    //자동 autowired
    private final AnswerRepository answerRepository;

    /**
     * 글 저장
     * writing의 id반환.
     */

    public boolean insertWriting(Answer answer) {
        // TODO: 2022-05-12 글, 내용 보안 철저히
        if (answerRepository.save(answer) != 0) {
            return true;
        }
        return false;
    }

    public List<Answer> findAllAnswerByWriting(Writing w) {
        return w.getAnswers().stream().map(i->answerRepository.findOneById(i.getId())).collect(Collectors.toList());
    }

    @Transactional
    public void updateAnswer(){

    }

    @Transactional
    public void deleteAnswer(){

    }
}
