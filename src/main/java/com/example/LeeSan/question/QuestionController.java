package com.example.LeeSan.question;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/")
    public String root(){
        return "redirect:/question/list";
    }

    @GetMapping("/question/list")
    public String question_list(Model model){
        List<Question> questionList = this.questionService.getList();

        model.addAttribute("questionList", questionList);
        return "article_list";
    }

    @GetMapping("/question/create")
    public String question_create(){
        return "article_create";
    }

    @PostMapping("/question/create")
    public String create(@RequestParam String title, @RequestParam String content){
        this.questionService.createQuestion(title, content);
        return "redirect:/question/list";
    }

    @GetMapping("/question/detail/{id}")
    public String question_detail(@PathVariable int id, Model model){
        Question question = this.questionService.getArticle(id);
        model.addAttribute("question", question);
        return "article_detail";
    }

    @GetMapping("/question/modify/{id}")
    public String question_modify(@PathVariable int id, Model model){
        Question question = this.questionService.getArticle(id);
        model.addAttribute("question", question);
        return "article_modify";
    }

    @PostMapping("/question/modify/{id}")
    public String modify(@PathVariable int id, @RequestParam String title, @RequestParam String content){
        this.questionService.modify(id, title, content);
        return "redirect:/question/detail/" + id;
    }

    @GetMapping("question/delete/{id}")
    public String question_delete(@PathVariable int id){
        Question question = this.questionService.getArticle(id);
        this.questionService.delete(question);
        return "redirect:/";
    }
}
