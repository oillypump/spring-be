package rodl.projecttigabe.services;

import java.util.Set;

import rodl.projecttigabe.entities.exam.Question;
import rodl.projecttigabe.entities.exam.Quiz;

public interface QuestionService {
    
    public Question addQueston(Question question);

    public Question updateQuestion(Question question);

    public Set<Question> getQuestion();

    public Question getQuestion(Long questionId);

    public Set<Question> getQuestionOfQuiz(Quiz quiz);

    public void deleteQuestion(Long quesId);

    public Question get(Long questionsId);
}
