package reader;

import common.utility.GeneralHardCodes;
import dao.Customer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import repository.CustomerServices;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @auteur ALireza Abolhasani
 * @date: 5/21/2024
 * @time: 8:10 AM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/
public class CsvCustomer implements FileReaderIMPL,Runnable{
    final static Logger logger = Logger.getLogger(CsvCustomer.class.getName());

    /**
     * @return void
     * This method use for Customer CsvReader
     */
    @Override
    public void run() {
        List<Customer> customersList = new ArrayList<>();
        try{
            customersList = (List<Customer>) this.reader_CSV(GeneralHardCodes.SAMPLE_CUSTOMER_CSV_FILE_PATH);
            CustomerServices customerServices = new CustomerServices();
            customerServices.saveAListOfRecordWithHikari(customersList);
        }catch (Exception e){
          logger.warn(e.getMessage());
        }
    }
    /**
     * @param fileAddress
     * @return List<Customer>
     * Read csv customer file and insert a list and return it
     * */
    @Override
    public List<?> reader_CSV(String fileAddress) throws Exception {
        List<Customer> customerList = new ArrayList<>();
        Reader reader = Files.newBufferedReader(Paths.get(fileAddress));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        Customer customer;
        try {
            for (CSVRecord csvRecord : csvParser) {
                customer = new Customer();
                customer.setName(String.valueOf(csvRecord.get(0)));
                customer.setFamily(String.valueOf(csvRecord.get(1)));
                customer.setEmail(String.valueOf(2));
                customer.setMobile(String.valueOf(3));
                customer.setCountry(String.valueOf(4));
                customer.setCustomerType(Short.parseShort(csvRecord.get(5)));
                customer.setCustomerId(Long.parseLong(csvRecord.get(6)));
                customer.setBirthDate(Integer.parseInt(csvRecord.get(7)));
                customer.setAddress(csvRecord.get(8));
                customer.setNationalId(csvRecord.get(9));
                customer.setCity(Integer.parseInt(csvRecord.get(10)));
                customerList.add(customer);
                try {
                    /**
                     * Making the thread pause for a certain
                     * time using sleep() method
                     * */
                    Thread.sleep(10);
                    logger.info("Thread 2");
                }
                // Catch block to handle the exceptions
                catch (Exception e) {
                    logger.info("Thread 2 Exception=" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
        return customerList;
    }
}
