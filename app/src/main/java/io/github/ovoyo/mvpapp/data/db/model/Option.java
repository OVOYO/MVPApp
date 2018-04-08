package io.github.ovoyo.mvpapp.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity(nameInDb = "options")
public class Option{

    @Expose
    @SerializedName("id")
    @Id(autoincrement = true)
    private Long id;

    @Expose
    @SerializedName("option_text")
    @Property(nameInDb = "option_text")
    private String optionText;

    @Expose
    @SerializedName("question_id")
    @Property(nameInDb = "question_id")
    private Long questionId;

    @Expose
    @SerializedName("is_correct")
    @Property(nameInDb = "is_correct")
    private boolean isCorrect;

    @Expose
    @SerializedName("created_at")
    @Property(nameInDb = "create_at")
    private String createAt;

    @Expose
    @SerializedName("updated_at")
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
