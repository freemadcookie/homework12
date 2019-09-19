package com.github.siberianintegrationsystems.restApp.service;

import com.github.siberianintegrationsystems.restApp.controller.dto.*;
import com.github.siberianintegrationsystems.restApp.data.AnswerRepository;
import com.github.siberianintegrationsystems.restApp.data.QuestionRepository;
import com.github.siberianintegrationsystems.restApp.data.SelectedAnswerRepository;
import com.github.siberianintegrationsystems.restApp.data.SessionRepository;
import com.github.siberianintegrationsystems.restApp.entity.Answer;
import com.github.siberianintegrationsystems.restApp.entity.Question;
import com.github.siberianintegrationsystems.restApp.entity.Selected_Answer;
import com.github.siberianintegrationsystems.restApp.entity.Session;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final SessionRepository sessionRepository;
    private final SelectedAnswerRepository selectedAnswerRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository,
                               AnswerRepository answerRepository,
                               SessionRepository sessionRepository,
                               SelectedAnswerRepository selectedAnswerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.sessionRepository = sessionRepository;
        this.selectedAnswerRepository = selectedAnswerRepository;
    }


    //создание вопроса
    @Override
    public QuestionsItemDTO createQuestion(QuestionsItemDTO dto) {
        Question question = new Question();
        question.setName(dto.name);
        questionRepository.save(question);

        for (AnswerItemDTO answerDTO : dto.answers) {
            Answer answer = new Answer();
            answer.setName(answerDTO.answerText);
            answer.setCorrect(answerDTO.isCorrect);
            answer.setQuestion(question);

            answerRepository.save(answer);
        }

        return new QuestionsItemDTO(question,
                answerRepository.findByQuestion(question));
    }
    //редактирование вопроса
    @Override
    public QuestionsItemDTO editQuestion(QuestionsItemDTO dto) {

        Long id = Long.parseLong(dto.id);
        //ищем нужный вопрос по его айди из дто
        Question question = questionRepository.findById(id).get();
        //присваиваем новые значения
        question.setName(dto.name);
        //находим список ответов для данного вопроса и удаляем их
        List<Answer> answerList = answerRepository.findByQuestion(question);
        answerRepository.deleteAll(answerList);

        //добавляем отредактированные ответы
        for (AnswerItemDTO answerDTO : dto.answers) {
            Answer answer = new Answer();
            answer.setName(answerDTO.answerText);
            answer.setCorrect(answerDTO.isCorrect);
            answer.setQuestion(question);

            answerRepository.save(answer);
        }

        return new QuestionsItemDTO(question,
                answerRepository.findByQuestion(question));
    }

    // получаем вопросы для новой сессии
    @Override
    public List<QuestionsItemDTO> getQuestionsForSession() {
        List<QuestionsItemDTO> questionList = new ArrayList<>();
        for (Question question: questionRepository.findAll())
        {
            QuestionsItemDTO questionsItemDTO = new QuestionsItemDTO(question,
                                                answerRepository.findByQuestion(question));
            questionList.add(questionsItemDTO);

        }

        return questionList;
    }

    //создаем сессию
    @Override
    public String createSession(SessionEntityDTO dto) {
        Session session = new Session();
        session.setFio(dto.getName());
        session.setInsertDate(LocalDateTime.now());

        double total = 0;
        double correct = 0;

        sessionRepository.save(session);
        for (AnsweredDTO answeredDTO : dto.getQuestionsList()){

            //получаем количество всех ответов
            total = total + answeredDTO.getAnswersList().size();
            System.out.println("total: " + total);

            for (SelectedAnswersDTO selectedAnswers : answeredDTO.getAnswersList())
            {
                //просматриваем answerRepository по каждому ответу
                Answer answer = answerRepository.findAnswerById(Long.parseLong(selectedAnswers.getId()));
                System.out.println("answer: " + selectedAnswers.getId());
                System.out.println("answer2: " + selectedAnswers.getIsSelected());
                System.out.println("answer3: " + answer.getCorrect());
                //если выбранный ответ правильный увеличиваем счетчик

                if (selectedAnswers.getIsSelected() && answer.getCorrect())
                {
                    correct++;
                }
                //сохраняем все выбранные ответы
                selectedAnswerRepository.save(new Selected_Answer(session, answer));
            }
        }
        System.out.println("total: " + total + " correct: " + correct);
        Double countResult = correct / total * 100;
        System.out.println("countResult: " + countResult);
        session.setPercent(countResult);

        return Double.toString(sessionRepository.save(session).getPercent());
    }
}
