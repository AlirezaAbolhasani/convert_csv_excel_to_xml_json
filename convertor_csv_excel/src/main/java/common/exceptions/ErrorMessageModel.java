package common.exceptions;
import java.time.LocalDateTime;

/**
 * @auteur ALireza Abolhasani
 * @date: 5/21/2024
 * @time: 8:10 AM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/

public class ErrorMessageModel {

    private int    statusCode;
    private LocalDateTime   timestamp;
    private String message;
    private String level;
    private String description;

    public ErrorMessageModel() {
    }

    public ErrorMessageModel(int statusCode, LocalDateTime timestamp, String message, String level, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.level = level;
        this.description = description;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
