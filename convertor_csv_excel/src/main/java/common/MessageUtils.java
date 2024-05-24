package common;

/**
 * *Developer: ALireza Abolhasani
 * 2/25/2024
 * 8:52 PM
 **/


public class MessageUtils {
    //Common Exceptions Range must strat from  0000000
    public static final int ERROR_COMMON_0000001_RECORD_NOT_FOUND    = 0000001;

    //Account Exceptions Range must strat from 1000000
    public static final int ERROR_ACCOUNT_BALANCE_IS_NOT_VALID       = 1000001;
    public static final int ERROR_ACCOUNT_TYPE_IS_NOT_VALID          = 1000002;
    public static final int ERROR_ACCOUNT_NUMBER_IS_NOT_VALID        = 1000003;

    //CUSTOMER Exceptions  Range must strat from 2000000
    public static final int ERROR_CUSTOMER_TYPE_IS_NOT_VALID             = 2000001;
    public static final int ERROR_CUSTOMER_BIRTHDATE_IS_NOT_VALID        = 2000002;
    public static final int ERROR_CUSTOMER_MATCH_ACCOUNTS_TO_CUSTOMER    = 2000003;
    public static final int ERROR_CUSTOMER_ID_IS_NOT_MATCH               = 2000004;

    //Unknown Error
    public static final int ERROR_COMMON_999999_UNKNOWN                  = 999999;


    public static String getErrorMessageKey(int errorCode) {

        String message;
        switch (errorCode) {
            case ERROR_ACCOUNT_BALANCE_IS_NOT_VALID:
                message = "ERROR_ACCOUNT_BALANCE_IS_NOT_VALID.1000001";
                break;
            case ERROR_ACCOUNT_TYPE_IS_NOT_VALID:
                message = "ERROR_ACCOUNT_TYPE_IS_NOT_VALID.1000002";
                break;
            case ERROR_ACCOUNT_NUMBER_IS_NOT_VALID:
                message = "ERROR_ACCOUNT_NUMBER_IS_NOT_VALID";
                break;
            case ERROR_CUSTOMER_TYPE_IS_NOT_VALID:
                message = "ERROR_CUSTOMER_TYPE_IS_NOT_VALID.2000001";
                break;
            case ERROR_CUSTOMER_BIRTHDATE_IS_NOT_VALID:
                message = "ERROR_CUSTOMER_BIRTHDATE_IS_NOT_VALID.2000002";
                break;
            case ERROR_CUSTOMER_MATCH_ACCOUNTS_TO_CUSTOMER:
                message = "ERROR_CUSTOMER_MATCH_ACCOUNTS_TO_CUSTOMER.2000002";
                break;
            case ERROR_CUSTOMER_ID_IS_NOT_MATCH:
                message = "ERROR_CUSTOMER_ID_IS_NOT_MATCH.2000004 ";
                break;
            // Common Error Codes
            case ERROR_COMMON_0000001_RECORD_NOT_FOUND:
                message = "ERROR_COMMON_0000001_RECORD_NOT_FOUND.0000001";
                break;
            default:
                message = "ERROR.UNKNOWN ( " + String.valueOf(errorCode)+" ) PLEASE TAKE AN SCREENSHOT AND CONTACT WITH ADMIN...";
                break;
        }
        return message;
    }
}
