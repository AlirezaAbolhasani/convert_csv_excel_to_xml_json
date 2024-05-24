package dao;

import common.MessageUtils;
import common.exceptions.BusinessException;
import common.utility.GeneralHardCodes;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auteur ALireza Abolhasani
 * @Date: 2/24/2024
 * @Time: 3:33 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/

public class Account {
    final static Logger logger = Logger.getLogger(Account.class.getName());
    private Long    id;
    private Long    accNo;
    private Long    customerId;
    private int     accountType;
    private int     openDate;
    private double  balance;

    private short   accountLimit;
    private int     intCustId;
    private int     extCustId;


    /**
     * @param balance
     * @return control balance > 0
     */
    public void setBalance(Float balance) throws BusinessException {
        if(balance< GeneralHardCodes.standardBalance){
            throw new BusinessException(MessageUtils.getErrorMessageKey(1000001));
        }else {
            this.balance = balance;
        }
    }

    /**
     * @param accountType
     * @return control account type and should be longer than 0 and smaller than 5
     */
    public void setAccountType(int accountType) throws BusinessException {
        if(accountType <1 || accountType > 3){
            throw new BusinessException(MessageUtils.getErrorMessageKey(1000002));
        }
        this.accountType = accountType;
    }

    public Account() {
    }

    public Long getAccNo() {
        return accNo;
    }

    /**
     * @param accNo
     * @return accNo
     * Control length of Account number
     */
    public void setAccNo(Long accNo) throws BusinessException {
        Pattern pattern = Pattern.compile("^[0-9]{1,22}$");
        Matcher matcher = pattern.matcher(String.valueOf(accNo));
        Boolean FLG = matcher.find();
        if(FLG){
            this.accNo = accNo;
        }else{
           throw new BusinessException(MessageUtils.getErrorMessageKey(1000003));
        }
    }

    public Account(Long id, Long accNo, Long customerId, int accountType, int openDate, double balance, short accountLimit, int intCustId, int extCustId) {
        this.id = id;
        this.accNo = accNo;
        this.customerId = customerId;
        this.accountType = accountType;
        this.openDate = openDate;
        this.balance = balance;
        this.accountLimit = accountLimit;
        this.intCustId = intCustId;
        this.extCustId = extCustId;
    }

    public static Logger getLogger() {
        return logger;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public int getAccountType() {
        return accountType;
    }

    public int getOpenDate() {
        return openDate;
    }

    public void setOpenDate(int openDate) {
        this.openDate = openDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public short getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(short accountLimit) {
        this.accountLimit = accountLimit;
    }

    public int getIntCustId() {
        return intCustId;
    }

    public void setIntCustId(int intCustId) {
        this.intCustId = intCustId;
    }

    public int getExtCustId() {
        return extCustId;
    }

    public void setExtCustId(int extCustId) {
        this.extCustId = extCustId;
    }
}
