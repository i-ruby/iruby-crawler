package work.iruby.entity;

/**
 * @author Ruby
 * @date 2020/12/24 11:10
 */
public class Message<T> {
    private boolean success;
    private T content;

    public Message(boolean success, T content) {
        this.success = success;
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Message{");
        sb.append("success=").append(success);
        sb.append(", content=").append(content);
        sb.append('}');
        return sb.toString();
    }
}
