package com.wiss.quizbackend.mapper;

import com.wiss.quizbackend.dto.QuestionDTO;
import com.wiss.quizbackend.dto.QuestionFormDTO;
import com.wiss.quizbackend.entity.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionMapper {

    /**
     * Konvertiert eine Question entity in ein QuestionDTO
     * für das Anzeigen in einem Quiz Game
     * @param entity Die Frage formatiert für die Datenbank
     * @return Das DTO das zum Anzeigen in einem Quiz Game benötigt wird
     */
    public static QuestionDTO toDTO(Question entity) {
        if (entity == null) {
            return null;
        }

        // Alle Antworten sammeln
        List<String> allAnswers = new ArrayList<>();
        allAnswers.addAll(entity.getIncorrectAnswers());
        allAnswers.add(entity.getCorrectAnswer());

        // Antworten mischen - wichtig für Frontend!
        Collections.shuffle(allAnswers);

        return new QuestionDTO(
                entity.getId(),               // ← ID hinzufügen!
                entity.getQuestion(),
                entity.getCorrectAnswer(),
                allAnswers,
                entity.getCategory(),
                entity.getDifficulty()
        );
    }

    public static QuestionFormDTO toFormDTO(Question entity) {
        if (entity == null) {
            return null;
        }

        return new QuestionFormDTO(
                entity.getId(),
                entity.getQuestion(),
                entity.getCorrectAnswer(),
                entity.getIncorrectAnswers(),
                entity.getCategory(),
                entity.getDifficulty()
        );
    }

    public static Question toEntity(QuestionDTO dto) {
        if (dto == null) {
            return null;
        }

        // correctAnswer aus answers herausfiltern
        List<String> incorrectAnswers = dto.getAnswers().stream()
                .filter(answer -> !answer.equals(dto.getCorrectAnswer()))
                .toList();

        return new Question(
                dto.getQuestion(),
                dto.getCorrectAnswer(),
                incorrectAnswers,
                dto.getCategory(),
                dto.getDifficulty()
        );
    }

    // Utility Methoden für Listen
    public static List<QuestionDTO> toDTOList(List<Question> entities) {
        return entities.stream()
                .map(QuestionMapper::toDTO)
                .toList();
    }

    // Utility Methoden für Listen
    public static List<QuestionFormDTO> toFormDTOList(List<Question> entities) {
        return entities.stream()
                .map(QuestionMapper::toFormDTO)
                .toList();
    }
}
