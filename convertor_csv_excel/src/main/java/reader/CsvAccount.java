package reader;

import common.enumerable.AccountType;
import common.utility.GeneralHardCodes;
import dao.Account;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import repository.AccountServices;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @auteur ALireza Abolhasani
 * @Date: 2/24/2024
 * @Time: 3:33 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/

public class CsvAccount implements FileReaderIMPL,Runnable{

    final static Logger logger = Logger.getLogger(CsvAccount.class.getName());

    /**
     * @return void
     * This method use for Account CsvReader
     */
    @Override
    public void run() {
        List<Account> accountList = new ArrayList<Account>();
        try {
            accountList = (List<Account>) reader_CSV(GeneralHardCodes.SAMPLE_ACCOUNT_CSV_FILE_PATH);
            AccountServices accountServices = new AccountServices();
            accountServices.saveAListOfRecordWithHikari(accountList);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @param fileAddress
     * @return accountList
     * reader_CSV_Account method is a method for convert a CSV file to a list of account
     * This method work just for Account model
     */

    @Override
    public List<?> reader_CSV(String fileAddress) throws Exception{
        Reader reader = Files.newBufferedReader(Paths.get(fileAddress));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        List<Account> accountList = new ArrayList<Account>();
        Account account;
        try {
            for (CSVRecord csvRecord : csvParser) {
                account = new Account();
                AccountType accountType = AccountType.valueOf(csvRecord.get(3));
                account.setId(Long.parseLong(csvRecord.get(0)));
                account.setAccNo(Long.parseLong(csvRecord.get(1)));
                account.setCustomerId(Long.parseLong(csvRecord.get(2)));
                account.setAccountType(accountType.getIntValue());
                account.setOpenDate(Integer.parseInt(csvRecord.get(4)));
                account.setBalance(Float.parseFloat(csvRecord.get(5)));
                accountList.add(account);
                try {
                    /**
                     * Making the thread pause for a certain
                     * time using sleep() method
                     * */
                    Thread.sleep(10);
                    logger.info("Thread 1");
                }
                // Catch block to handle the exceptions
                catch (Exception e) {
                    logger.info("Thread 1 Exception="+e.getMessage());
                }
            }
        }catch (Exception e){
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
        return accountList;
    }
}
