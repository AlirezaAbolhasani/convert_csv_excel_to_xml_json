package dao;

import common.MessageUtils;
import common.exceptions.BusinessException;
import org.apache.log4j.Logger;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * *Developer: ALireza Abolhasani
 * 2/24/2024
 * 3:33 PM
 **/
@Entity
@Table(name = "customer")


public class Customer implements Serializable {

    final static Logger logger = Logger.getLogger(Customer.class.getName());
    @Id
    private Integer id;

    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;

    @Column(nullable = false)
    private Long  customerId;

    private Integer    city;
    private String name;
    private String family;
    private String email;
    private String mobile;
    private int    birthDate;
    private String nationalId;
    private String zipCode;
    private String address;
    private String country;
    private Short  customerType;
    
    public Customer() {
    }

    /**
     * @param birthDate
     * @return birthDate
     * length of customer ID Should 10
     */
    public void setBirthDate(int birthDate) throws BusinessException{
        if(birthDate <1995){
            throw new BusinessException(MessageUtils.getErrorMessageKey(2000002));
        }else
            this.birthDate = birthDate;
    }

    /**
     * @param customerId
     * @return customerId
     * length of customer ID Should 10
     */
    public void setCustomerId(Long customerId) throws BusinessException {
        String str = String.valueOf(customerId);
        if(str.length()< 10 || str.length() >10){
            throw new BusinessException(MessageUtils.getErrorMessageKey(2000004));
        }
        this.customerId = customerId;
    }

    public static Logger getLogger() {
        return logger;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Short getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Short customerType) {
        this.customerType = customerType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public int getBirthDate() {
        return birthDate;
    }

}
