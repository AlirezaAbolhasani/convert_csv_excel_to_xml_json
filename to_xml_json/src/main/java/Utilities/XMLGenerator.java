package Utilities;

import dao.Account;
import dao.Customer;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;


/**
 * @auteur ALireza Abolhasani
 * @date: 5/26/2024
 * @time: 7:46 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 * In this Lib I used below reference
 * >>>> https://mkyong.com/java/how-to-create-xml-file-in-java-dom/
 * */
public class XMLGenerator {
    final static Logger logger = Logger.getLogger(XMLGenerator.class.getName());

    /**
     * @param doc get
     * @param output where you want save your file containing file name.
     * @throws Exception
     * write doc to output stream
     * */

    private static void writeXml(Document doc,
                                 OutputStream output)
            throws TransformerException {
        try {

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(output);
            transformer.transform(source, result);

        }catch(Exception e){

            e.printStackTrace();
        }

    }

    /**
     * @param customerList get a list of customers for our XML file
     * @param modelName our model name in this code use for master XML tags
     * @return if our process finish successfully return true else return false
     * */
    public Boolean XMLGenerator(List<Customer> customerList,String modelName) throws NoSuchFieldException, ParserConfigurationException, TransformerException {
        Boolean flg = Boolean.TRUE;
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("Customers");
        doc.appendChild(rootElement);

        for(Customer customer : customerList) {
            // add xml elements
            Element cs = doc.createElement("Customer");
            // add staff to root
            rootElement.appendChild(cs);
            // add xml attribute
            cs.setAttribute("id", customer.getId().toString());

            Element name = doc.createElement("name");
            name.setTextContent(customer.getName().toString());
            cs.appendChild(name);

            Element family = doc.createElement("family");
            family.setTextContent(customer.getFamily().toString());
            cs.appendChild(family);

            Element zipCode = doc.createElement("zipCode");
            zipCode.setTextContent(customer.getZipCode().toString());
            cs.appendChild(zipCode);

            Element address = doc.createElement("Address");
            address.setTextContent(customer.getAddress());
            cs.appendChild(address);

            Element city = doc.createElement("city");
            city.setTextContent(String.valueOf(customer.getCity()));
            cs.appendChild(city);

            Element email = doc.createElement("email");
            email.setTextContent(customer.getEmail().toString());
            cs.appendChild(email);

            Element mobile = doc.createElement("mobile");
            mobile.setTextContent(customer.getMobile().toString());
            cs.appendChild(mobile);

            Element nationalID = doc.createElement("nationalID");
            nationalID.setTextContent(customer.getNationalId());
            cs.appendChild(nationalID);

            Element customerID = doc.createElement("customerID");
            customerID.setTextContent(customer.getCustomerId().toString());
            cs.appendChild(customerID);

        }
        // print XML to system console
        //writeXml(doc, System.out);
        // write dom document to a file
        try (FileOutputStream output =
                     new FileOutputStream("output/customer.xml")) {
            writeXml(doc, output);
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
            logger.warn(e.getMessage());
        }
        return flg;
    }


   public XMLGenerator(){

        for (Field s : Account.class.getDeclaredFields()){
            System.out.println(s.getName()+">>"+s.getType() +"");
        }

    }
}
