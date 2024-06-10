import Utilities.XMLGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.Customer;
import repository.CustomerServices;
import repository.JointingServicesForXMLSRV;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @auteur ALireza Abolhasani
 * @date: 5/24/2024
 * @time: 10:49 AM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/
public class RunThisFile {
    static final Logger logger = Logger.getLogger(RunThisFile.class.getName());

    public static void main(String[] args) throws ParserConfigurationException, NoSuchFieldException, TransformerException {
        try{
            RunThisFile.JSONGenerator();
            RunThisFile.XMLGeneratorCaller();
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
            logger.warning(e.getMessage());
        }
    }

    public static void JSONGenerator(){
        List<Customer> customerList = new ArrayList<>();
        String strJSON;
        JointingServicesForXMLSRV jointingServices = new JointingServicesForXMLSRV();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            customerList = jointingServices.getCustomerWithASpecialBalanceHikari();

//            objectMapper.writeValue(new File("output/_customerList_.json"), new Customer());
            try(FileWriter fileWriter = new FileWriter("output/_customerList_.json"))
            {
                /**Below code create a json for sending in webservice
                 * or something like that
                 * */
                strJSON = gson.toJson(customerList);
                System.out.println(strJSON);
                /**Below code Write accountList in fileWriter that define in top code
                 * */
                gson.toJson(customerList,fileWriter);

            }catch (IOException e){
                logger.warning(e.getMessage());
            }
        }catch (Exception e){
            logger.warning(e.getMessage());
        }finally {
            logger.warning("Ok");
        }
    }
    public static void XMLGeneratorCaller()
        throws ParserConfigurationException, NoSuchFieldException, TransformerException {
            List<Customer> customerList = new ArrayList<>();
            CustomerServices customerServices = new CustomerServices();
            customerList = customerServices.getAllRecordWithHikari();
            XMLGenerator xmlGenerator = new XMLGenerator();
            Boolean flg = xmlGenerator.XMLGenerator(customerList,"Customer");
    }
}
