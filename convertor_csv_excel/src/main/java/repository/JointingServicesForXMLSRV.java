package repository;

import common.connection.HikariCPBuilder;
import dao.Customer;
import org.apache.log4j.Logger;
import org.hibernate.jpa.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
public class JointingServicesForXMLSRV {
    static final Logger logger = Logger.getLogger(JointingServicesForXMLSRV.class.getName());
    Connection connection =null;
    PreparedStatement preparedStatement =null;
/**
 * @param
 * @return List<Customer>
 *     Get a list of customers with account balance > than 1000
 *     With Hikari connection
 * **/
    public List<Customer> getCustomerWithASpecialBalanceHikari(){
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

    /**
     * @param
     * @return List<Customer>
     *     Get a list of customers with account balance > than 1000
     *     With JPA connection and Hibernate provider and JPQL(Query)
     * **/
    public List<Customer> getCustomerWithASpecialBalanceJPA(){
        List<Customer> customerList = new ArrayList<>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("easyappartment");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            Query query = em.createQuery("SELECT DISTINCT cu.id, cu.customerId, cu.city, cu.name, cu.family, cu.email, cu.mobile, cu.birthDate, cu.nationalId, cu.zipCode, cu.address FROM Customer cu " +
                    " JOIN Account ac ON (cu.customerId=ac.customerId) WHERE ac.balance>1000 AND cu.customerId = ac.customerId");

            query.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH,false);
            customerList= query.getResultList();

        }catch (Exception e){
           logger.warn(e.getMessage());
        }


        return customerList;
    }

}
