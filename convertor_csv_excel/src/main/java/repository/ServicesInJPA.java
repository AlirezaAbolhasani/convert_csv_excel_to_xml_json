package repository;

import common.exceptions.BusinessException;
import dao.Customer;
import org.junit.BeforeClass;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

/**
 * @auteur ALireza Abolhasani
 * @date: 5/30/2024
 * @time: 4:43 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/

public class ServicesInJPA {

//    private static EntityManagerFactory factory = null;
//    private static EntityManager entityManager = null;
//    @BeforeClass
//    public static void init() {
//        factory = Persistence.createEntityManagerFactory("easyappartment");
//        entityManager = factory.createEntityManager();
//    }

    public static void main(String[] args) throws BusinessException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("easyappartment");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
//*********************Type 1 SELECT
        Customer customer = em.find(Customer.class, 80);
        System.out.println(customer.getFamily());
//*********************Type 2 SELECT
        Query q = em.createQuery("SELECT c.id from Customer c where c.id =80");
        List<Integer> s = q.getResultList();
        for (Integer ww : s) {
            System.out.println(ww);
        }
//*********************type 3 SELECT
        Query query = em.createNativeQuery("select name from Customer ");
        List<String> ss = query.getResultList();
        for (String ww : ss) {
            System.out.println(ww);
        }

//*******************Type 1 Update
        Customer customer2 = em.find(Customer.class, 85);
        customer2.setFamily("Abdolian");
        em.getTransaction().commit();
//******************
        em.close();
        emf.close();
//*******************Type 2 Update
        emf = Persistence.createEntityManagerFactory("easyappartment");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        customer.setId(78);
        customer.setName("AlijaniAsl");
        Customer customer1 = em.merge(customer);
        em.getTransaction().commit();
//*******************Type 1 Insert
//        EntityManagerFactory emfIns = Persistence.createEntityManagerFactory("easyappartment");
//        EntityManager emIns = emfIns.createEntityManager();
//        emIns.getTransaction().begin();
//        Customer customer3 = new Customer();
//        customer3.setName("حجت");
//        customer3.setFamily("اشرف زاده");
//        customer3.setCountry("Iran");
//        customer3.setId(90);
//        customer3.setCustomerId(2323200323l);
//        customer3.setCity(26);
//        customer3.setAddress("تهران - دروس - بنبست راز - پلاک 5");
//        emIns.persist(customer3);
//        emIns.getTransaction().commit();
//        emIns.close();
//        emfIns.close();
//*******************Call Store Procedure

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("easyappartment") ;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        StoredProcedureQuery sp = entityManager.createNamedStoredProcedureQuery("gAccount1");
        sp.setParameter("PI_ID",  String.valueOf(90));
        sp.execute();
        customer = new Customer();
        Integer PO_ERROR = (Integer) sp.getOutputParameterValue("PO_ERROR");
        customer.setName((String) sp.getOutputParameterValue("PO_NAME"));
        customer.setFamily((String)sp.getOutputParameterValue("PO_FAMILY"));
        Integer PO_STEP  = (Integer) sp.getOutputParameterValue("PO_STEP");
        sp.getParameters().stream().forEach(parameter -> System.out.println(parameter.getName()));
        System.out.println(customer.getName() +"-"+customer.getFamily());

//*******************Type 2 STORE PROCEDURE with result set
      EntityManagerFactory emfSP = Persistence.createEntityManagerFactory("easyappartment") ;
      EntityManager emSP = emfSP.createEntityManager();
      emSP.getTransaction().begin();
      StoredProcedureQuery spCusrsour = emSP.createNamedStoredProcedureQuery("customerList");
      spCusrsour.execute();
      List<Customer> cusL = (List<Customer>) spCusrsour.getResultList();
      for (Customer cus : cusL){
          System.out.println(cus.getName());
      }


    }
}
