package io.github.ovoyo.mvpapp.data.db;

import java.util.List;

import io.github.ovoyo.mvpapp.data.db.model.Option;
import io.github.ovoyo.mvpapp.data.db.model.Question;
import io.github.ovoyo.mvpapp.data.db.model.User;
import io.reactivex.Observable;

public interface DbHelper {

    Observable<Long> insertUser(final User user);

    Observable<List<User>> getAllUsers();

    Observable<List<Question>> getAllQuestions();

    Observable<Boolean> isQuestionEmpty();

    Observable<Boolean> isOptionEmpty();

    Observable<Boolean> saveQuestion(Question question);

    Observable<Boolean> saveOption(Option option);

    Observable<Boolean> saveQuestionList(List<Question> questionList);

    Observable<Boolean> saveOptionList(List<Option> optionList);

}
