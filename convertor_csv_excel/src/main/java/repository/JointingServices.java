package repository;

import com.zaxxer.hikari.HikariDataSource;
import common.connection.HikariCPBuilder;
import dao.Customer;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @auteur ALireza Abolhasani
 * @date: 5/26/2024
 * @time: 8:59 AM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/
public class JointingServices {
    static final Logger logger = Logger.getLogger(JointingServices.class.getName());
    Connection connection =null;
    PreparedStatement preparedStatement =null;

    public List<Customer> getCustomerWithASpecialBalance(){
        List<Customer> customerList = new ArrayList<>();
        ResultSet resultSet = null;
        CustomerServices customerServices = new CustomerServices();
        try{
            DataSource dataSource = HikariCPBuilder.getInstance();
            connection = dataSource.getConnection();
            String sql="SELECT DISTINCT(cu.id), cu.customerID, cu.city, cu.name, cu.family, cu.email, cu.mobile, cu.birthDate, cu.nationalId, cu.zipCode, cu.address FROM easyappartment.customer cu " +
                    "JOIN easyappartment.account ac ON (cu.customerID=ac.customerId) WHERE ac.balance>1000 AND cu.customerID = ac.customerId;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            customerList = customerServices.getCustomerListFromAResultSet(resultSet);
        }catch (Exception e){
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
        return customerList;
    }
}
