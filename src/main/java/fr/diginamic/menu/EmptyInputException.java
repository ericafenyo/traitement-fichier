package fr.diginamic.menu;

public class EmptyInputException extends Exception {
    public EmptyInputException() {
        super("Input should not be empty");
    }
}
