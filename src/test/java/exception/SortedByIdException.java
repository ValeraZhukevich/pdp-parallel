package exception;

import io.qameta.allure.Allure;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SortedByIdException extends AssertionError{
    public SortedByIdException(String errorMessage) {
        super(errorMessage);
        try {
            Allure.addAttachment("Humans suck", new FileInputStream("src/test/resources/kill_humans.jpeg"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
