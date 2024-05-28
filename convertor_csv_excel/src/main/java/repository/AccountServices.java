package repository;

import common.connection.HikariCPBuilder;
import common.connection.SingletonBuilder;
import common.exceptions.BusinessException;
import dao.Account;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @auteur ALireza Abolhasani
 * @date: 5/21/2024
 * @time: 8:10 AM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/

public class AccountServices {
    final static Logger logger = Logger.getLogger(AccountServices.class.getName());
    Connection connection =null;
    PreparedStatement preparedStatement =null;


    /** JDBC
     * @param account
     * @return flg
     * This method save a row in account table and return
     * true if insert successfully and
     * return false if insert was not successful.
     */
    public boolean saveARecordJDBC(Account account){
        Boolean flg=true;
        Statement statement;
        int i= 0;
        try{
            ResourceBundle resourceBundle = ResourceBundle.getBundle("DB");
            Class.forName(resourceBundle.getString("MYSQL_DB_URL"));
            connection =SingletonBuilder.getSingletonConnection();
            statement = connection.createStatement();
            String sqlQuery = "INSERT INTO easyappartment.account(accNo,customerId,accountType,openDate," +
                    "balance,accountLimit,intCustId,extCustId)"+
                "VALUES(" +
                account.getAccNo()       +","+
                account.getCustomerId()  +","+
                account.getAccountType() +","+
                account.getOpenDate()    +","+
                account.getBalance()     +","+
                account.getAccountLimit()+","+
                account.getIntCustId()   +","+
                account.getExtCustId()   +
                ");";
            i = statement.executeUpdate(sqlQuery);
            if(i == 2) flg = false;
        }catch (SQLException | ClassNotFoundException e){
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
        return flg;
    }

    /**
     * @param
     * @return accountList
     * This method get all data from Account Table as a list with JDBC provider
     */
    public List<Account> getAllRecordWithJDBC(){
        Statement statement;
        List<Account> accountList = new ArrayList<>();
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("DB");
            Class.forName(resourceBundle.getString("MYSQL_DB_URL"));
            connection = SingletonBuilder.getSingletonConnection();
            statement = connection.createStatement();
            String str = "SELECT * FROM easyappartment.account ;";
            ResultSet resultSet = statement.executeQuery(str);
            accountList = getAccountListFromAResultSet(resultSet);
        }catch (SQLException | BusinessException | ClassNotFoundException e){
            e.printStackTrace();
            logger.warn(e.getMessage());
        }
        return accountList;
    }

    /** Hikari
     * @param
     * @return accountList
     * This method get all data in Account Table as a list with Hikari Hikari provider
     */
    public List<Account> getAllRecordWithHikari(){
        ResultSet resultSet ;
        List<Account> accountList = new ArrayList<>();
        try {
            DataSource dataSource = HikariCPBuilder.getInstance();
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM easyappartment.account ;");
            resultSet = preparedStatement.executeQuery();
            accountList = getAccountListFromAResultSet(resultSet);
        }catch (BusinessException e){
            e.printStackTrace();
            logger.warn(e.getMessage());
        }catch (SQLException e){
            e.printStackTrace();
            logger.warn(e.getMessage());
        }
        return accountList;
    }
    /**
     * @param accountList
     * @return Boolean
     * Save a list of record with hikari jdbc connection
     * */
    public Boolean saveAListOfRecordWithHikari(List<Account> accountList){
        ResultSet resultSet ;
        Boolean flg = true;
        long i = 0;
        Statement statement = null;
        try {
            DataSource dataSource = HikariCPBuilder.getInstance();
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            for(Account account: accountList){
                String prepareStatement =
                "INSERT INTO easyappartment.account(accNo,customerId,accountType,openDate," +
                    "balance,accountLimit,intCustId,extCustId)"+
                    "VALUES(" +
                    account.getAccNo()       +","+
                    account.getCustomerId()+","+
                    account.getAccountType()+","+
                    account.getOpenDate()   +","+
                    account.getBalance() +","+
                    account.getAccountLimit()+","+
                    account.getIntCustId()+","+
                    account.getExtCustId()+
                    ");";

                i = statement.executeUpdate(prepareStatement);
                if (i==2) flg=false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            logger.warn("MethodName=saveAListOfRecordWithHikari ---" + e.getMessage());
        }
        return flg;
    }


/**
 * @param resultSet
 * @return List<Account>
 *     Get a Resultset as a parameter and with a while change it to a List of Account
 * **/
    private List<Account> getAccountListFromAResultSet(ResultSet resultSet) throws SQLException, BusinessException {

        Account account ;
        List<Account> accountList = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                account = new Account();
                account.setId(Long.parseLong(resultSet.getString(1)));
                account.setAccNo(Long.parseLong(resultSet.getString(2)));
                account.setCustomerId(Long.parseLong(resultSet.getString(3)));
                account.setAccountType(Integer.parseInt(resultSet.getString(4)));
                account.setOpenDate(Integer.parseInt(resultSet.getString(5)));
                account.setBalance(Float.parseFloat(resultSet.getString(6)));
                account.setAccountLimit(Short.parseShort(resultSet.getString(7)));
                account.setIntCustId(Integer.parseInt(resultSet.getString(8)));
                account.setExtCustId(Integer.parseInt(resultSet.getString(9)));

                accountList.add(account);
            }
        }
        return accountList;
    }

}