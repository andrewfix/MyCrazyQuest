package exception;

public class NewQuestException extends Throwable {
    public NewQuestException(CloneNotSupportedException e) {
        super(e);
    }
}
