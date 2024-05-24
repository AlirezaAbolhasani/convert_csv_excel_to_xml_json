package common.exceptions;

import java.net.ConnectException;

/**
 * @auteur ALireza Abolhasani
 * @date: 5/21/2024
 * @time: 8:10 AM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/

public class ConnectionExceptionHandler extends ConnectException {
    public ConnectionExceptionHandler(String message){
        super(message);
    }
}
