package rodl.projecttigabe.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import rodl.projecttigabe.entities.exam.Question;
import rodl.projecttigabe.entities.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Set<Question> findByQuiz(Quiz quiz);
    
}
