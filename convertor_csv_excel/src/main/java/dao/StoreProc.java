package dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.ResultSet;

/**
 * @auteur ALireza Abolhasani
 * @date: 6/1/2024
 * @time: 3:13 PM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/
@Entity
@Getter
@Setter
@Table(name="test")

//*******************Type 1 STORE PROCEDURE for JPA and mysql database and oracle
@NamedStoredProcedureQuery(
        name = "gAccount1",
        procedureName = "gAccount1",
        resultClasses = {Customer.class},
        parameters = {
                @StoredProcedureParameter( name = "PO_ERROR" ,type = int.class      ,mode = ParameterMode.OUT ),
                @StoredProcedureParameter( name = "PI_ID"    ,type = String.class   ,mode = ParameterMode.IN ),
                @StoredProcedureParameter( name = "PO_NAME",type = String.class   ,mode = ParameterMode.OUT),
                @StoredProcedureParameter( name = "PO_FAMILY",type = String.class   ,mode = ParameterMode.INOUT ),
                @StoredProcedureParameter( name = "PO_STEP"  ,type = int.class      ,mode = ParameterMode.INOUT)
        })

//*******************Type 2 STORE PROCEDURE with result set for Oracle
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "customerList",
                procedureName = "customerList",
                resultClasses = Customer.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "customer_list_o", type = Void.class)

                })})
//Finish
public class StoreProc {
    @Id
    private Integer PI_ID;
    private String PO_NAME;
    private String PO_FAMILY;
    private Integer PO_STEP;

}
