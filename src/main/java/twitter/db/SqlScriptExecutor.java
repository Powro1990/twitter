package twitter.db;

public interface SqlScriptExecutor {
    void execute(String classpath) throws SqlScriptExecutorException;
}
