import common.exceptions.BusinessException;
import org.apache.log4j.Logger;
import reader.CsvAccount;
import reader.CsvCustomer;

/**
 * @auteur ALireza Abolhasani
 * @date: 5/21/2024
 * @time: 8:10 AM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/
public class RunThisFile {

    final static Logger logger = Logger.getLogger(RunThisFile.class.getName());

    public static void main(String[] args) throws BusinessException {
       try {
           /**
            * Define two object once csvAccount and once csvCustomer
            * in tow different way but do same action
            * run Thread
            */
           CsvAccount csvAccount = new CsvAccount();
           Runnable csvCustomer = new CsvCustomer();

           Thread accThread = new Thread(csvAccount);
           Thread customerThread = new Thread(csvCustomer);
            /**
             * Start Thread and Save CSV Files (Account and Customer)
             * in database.
            * */
           accThread.start();
           customerThread.start();
        } catch (Exception e) {
           e.printStackTrace();
           logger.info(e.getMessage());
           throw new BusinessException(e.getMessage());
        }finally {
           logger.info(".......main method ran successfully.......");
       }
    }
}
