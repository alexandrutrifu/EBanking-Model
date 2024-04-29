package App;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public interface Proxy {
    File logFile = new File("./src/main/resources/appLogs/log.txt");
    default void logSuccess(String userEmail, String action) {
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(logFile, true))) {
            printWriter.println("User with email: " + userEmail + " has executed: " + action);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    default void logFailure(String userEmail, String error) {
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(logFile, true))) {
            printWriter.println("Error encountered by user with " + userEmail + ": " + error);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}
