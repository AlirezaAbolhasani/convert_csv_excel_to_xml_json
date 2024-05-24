package repository;

import common.connection.HikariCPBuilder;
import common.connection.SingletonBuilder;
import common.exceptions.BusinessException;
import dao.Customer;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @auteur ALireza Abolhasani
 * @date: 5/22/2024
 * @time: 3:46 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/
public class CustomerServices {
    final static Logger logger = Logger.getLogger(CustomerServices.class.getName());
    Connection connection =null;
    PreparedStatement preparedStatement =null;


    /** JDBC
     * @param customer
     * @return flg
     * This method save a row in customer table and return
     * true if insert successfully and
     * return false if insert was not successful.
     */
    public boolean saveARecordJDBC(Customer customer){
        Boolean flg=true;
        Statement statement;
        int i= 0;
        try{
            ResourceBundle resourceBundle = ResourceBundle.getBundle("DB");
            Class.forName(resourceBundle.getString("MYSQL_DB_URL"));
            connection = SingletonBuilder.getSingletonConnection();
            statement = connection.createStatement();
            String sqlQuery = "INSERT INTO easyappartment.customer(customerID ,city ,name ,family ,email ,mobile ,birthDate ,nationalId ,zipCode ,address)"+
                    "VALUES(" +
                    customer.getCustomerId()      +","+
                    customer.getCity()  +","+
                    customer.getName() +","+
                    customer.getFamily()    +","+
                    customer.getEmail()     +","+
                    customer.getMobile()+","+
                    customer.getBirthDate()   +","+
                    customer.getNationalId()   +","+
                    customer.getZipCode()   +","+
                    customer.getAddress()   +
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
     * @return customerList
     * This method get all data from customer Table as a list with JPA provider
     */
    public List<Customer> getAllRecordWithJDBC(){
        Statement statement;
        List<Customer> customerList = new ArrayList<>();
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("DB");
            Class.forName(resourceBundle.getString("MYSQL_DB_URL"));
            connection = SingletonBuilder.getSingletonConnection();
            statement = connection.createStatement();
            int size;
            String str = "SELECT * FROM easyappartment.customer ;";
            ResultSet resultSet = statement.executeQuery(str);
            customerList = getCustomerListFromAResultSet(resultSet);

        }catch (SQLException | BusinessException | ClassNotFoundException e){
            e.printStackTrace();
            logger.warn(e.getMessage());
        }
        return customerList;
    }

    /** Hikari
     * @param
     * @return customerList
     * This method get all data in customer Table as a list with Hikari JDBC provider
     */
    public List<Customer> getAllRecordWithHikari(){
        ResultSet resultSet ;
        List<Customer> customerList = new ArrayList<>();
        try {
            DataSource dataSource = HikariCPBuilder.getInstance();
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM easyappartment.customer ;");
            resultSet = preparedStatement.executeQuery();
            customerList = getCustomerListFromAResultSet(resultSet);
        }catch (BusinessException e){
            e.printStackTrace();
            logger.warn(e.getMessage());
        }catch (SQLException e){
            e.printStackTrace();
            logger.warn(e.getMessage());
        }
        return customerList;
    }
    /**
     * @param customerList
     * @return Boolean
     * Save a list of record with hikari jdbc connection
     * */
    public Boolean saveAListOfRecordWithHikari(List<Customer> customerList){
        ResultSet resultSet ;
        Boolean flg = true;
        long i = 0;
        Statement statement = null;
        try {
            DataSource dataSource = HikariCPBuilder.getInstance();
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            for(Customer customer: customerList){
                String prepareStatement =
                        "INSERT INTO easyappartment.customer(customerID ,city ,name ,family ,email ,mobile ,birthDate ,nationalId ,zipCode ,address)"+
                        "VALUES(" +
                        customer.getCustomerId()      +","+
                        customer.getCity()  +",'"+
                        customer.getName() +"','"+
                        customer.getFamily()    +"','"+
                        customer.getEmail()     +"','"+
                        customer.getMobile()+"',"+
                        customer.getBirthDate()   +",'"+
                        customer.getNationalId()   +"','"+
                        customer.getZipCode()   +"','"+
                        customer.getAddress()   +
                        "');";

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
     *     Get a Resultset as a parameter and with a while change it to a List of Customer
     * **/
    private List<Customer> getCustomerListFromAResultSet(ResultSet resultSet) throws SQLException, BusinessException {

        Customer customer = new Customer();
        List<Customer> customerList = new ArrayList<>();
        if (resultSet != null) {
            while (resultSet.next()) {
                customer.setId(Long.parseLong(resultSet.getString(1)));
                customer.setCustomerId(Long.parseLong(resultSet.getString(2)));
                customer.setCity(Integer.parseInt(resultSet.getString(3)));
                customer.setName(String.valueOf(resultSet.getString(4)));
                customer.setFamily(String.valueOf(resultSet.getString(5)));
                customer.setEmail(String.valueOf(resultSet.getString(6)));
                customer.setMobile(String.valueOf(resultSet.getString(7)));
                customer.setBirthDate(Integer.parseInt(resultSet.getString(8)));
                customer.setNationalId(String.valueOf(resultSet.getString(9)));
                customer.setZipCode(String.valueOf(resultSet.getString(10)));
                customer.setAddress(String.valueOf(resultSet.getString(11)));
            }
            customerList.add(customer);
        }

        return customerList;
    }


}
