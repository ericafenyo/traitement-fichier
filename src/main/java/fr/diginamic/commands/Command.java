package fr.diginamic.commands;

/**
 * This interface represents a command to be executed.
 */
public interface Command {
    /**
     * Executes the command, performing a specific action or operation.
     */
    void execute() throws Exception;
}