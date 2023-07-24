package com.example.LeeSan.question;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public void createQuestion(String title, String content){
        Question question = new Question();
        question.setTitle(title);
        question.setContent(content);
        question.setWriter("홍길동");
        question.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }

    public List<Question> getList(){
        List<Question> qList = this.questionRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return qList;
    }

    public Question getArticle(int id){
        Optional<Question> question = this.questionRepository.findById(id);
        return question.get();
    }

    public void modify(int id, String title, String content){
        Optional<Question> oQuestion = this.questionRepository.findById(id);
        Question question = oQuestion.get();
        question.setTitle(title);
        question.setContent(content);
        this.questionRepository.save(question);
    }

    public void delete(Question question){
        this.questionRepository.delete(question);
    }
}
