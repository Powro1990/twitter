package twitter.db;

public class SqlScriptExecutorException extends Exception {
    public SqlScriptExecutorException(String message) {
        super(message);
    }

    public SqlScriptExecutorException(String message, Throwable cause) {
        super(message, cause);
    }
}
