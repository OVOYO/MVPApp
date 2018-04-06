package io.github.ovoyo.mvpapp.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity(nameInDb = "options")
public class Option{

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "optoion_text")
    private String optionText;

    @Property(nameInDb = "question_id")
    private Long questionId;

    @Property(nameInDb = "is_correct")
    private boolean isCorrect;

    @Property(nameInDb = "create_at")
    private String createAt;

    @Property(nameInDb = "update_at")
    private String updateAt;

    @Generated(hash = 546738526)
    public Option(Long id, String optionText, Long questionId, boolean isCorrect, String createAt, String updateAt) {
        this.id = id;
        this.optionText = optionText;
        this.questionId = questionId;
        this.isCorrect = isCorrect;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    @Generated(hash = 104107376)
    public Option() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public boolean getIsCorrect() {
        return this.isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}
