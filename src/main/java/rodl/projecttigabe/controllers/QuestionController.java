package rodl.projecttigabe.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rodl.projecttigabe.entities.exam.Question;
import rodl.projecttigabe.entities.exam.Quiz;
import rodl.projecttigabe.services.QuestionService;
import rodl.projecttigabe.services.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/question")
public class QuestionController {
    
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quisService;

    // add question
    @PostMapping(value = "/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQueston(question));
    }
    
    // update the qustion
    @PutMapping(value ="/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    // get all question of any quiz
    @GetMapping(value = "/quiz/{qid}")
    public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qid") Long qid) {
        // Quiz quiz = new Quiz();
        // quiz.setqId(qid);
        // Set<Question> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
        // return ResponseEntity.ok(questionOfQuiz);

        Quiz quiz = this.quisService.getQuiz(qid);
        Set<Question> questions = quiz.getQuestions();
        List<Question> list = new ArrayList(questions);
        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
        }
        
        // list.forEach((q)->{
        //     q.setAnswer("");
        // });
        

        Collections.shuffle(list);
        return ResponseEntity.ok(list);

        
    }

    @GetMapping(value = "/quiz/all/{qid}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid) {
        Quiz quiz = new Quiz();
        quiz.setqId(qid);
        Set<Question> questionsOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);
        
    }


    // get single question
    @GetMapping(value = "/{quesId}")
    public Question getQuestion(@PathVariable("quesId") Long quesId) {
        return this.questionService.getQuestion(quesId);
    }

    // delete question
    @DeleteMapping(value = "/{quesId}")
    public void deleteQuestion(@PathVariable("quesId") Long quesId) {
        this.questionService.deleteQuestion(quesId);
    }

    // eval quiz
    @PostMapping(value = "/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
        System.out.println(questions);
        double marksGot = (double) 0 ;
        int correctAnswer = 0;
        int attempted = 0;

        for ( Question q: questions) {
            Question question = this.questionService.get(q.getQuesId());
            // System.out.println(q.getGivenAnswer());
            // System.out.println(q.getAnswer());
            // System.out.println(question.getAnswer());

            if (q.getAnswer().equals(q.getGivenAnswer())) {
                correctAnswer++;

                double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
                marksGot += marksSingle;
                // System.out.println(marksGot);                
            }
            if (q.getGivenAnswer() != null) {
                attempted++;
                // System.out.println(attempted);
            }    
        };
        
        Map<String, Object> map = Map.of(
            "marksGot", marksGot,
            "correctAnswer", correctAnswer,
            "attempted", attempted
            );

        return ResponseEntity.ok(map);
    }
}
