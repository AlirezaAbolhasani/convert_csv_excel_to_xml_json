package common.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * @auteur ALireza Abolhasani
 * @date: 5/21/2024
 * @time: 8:10 AM
 * @mail: Abolhasany.Alireza@yahoo.com
 **/
public class HikariCPBuilder {
    private static DataSource dataSource;
    final static Logger logger = Logger.getLogger(HikariCPBuilder.class.getName());

    private static DataSource getDataSource(){
        try{
        ResourceBundle resourceBundle = ResourceBundle.getBundle("DB");
        if(dataSource == null){
                HikariConfig hikariConfig = new HikariConfig();
                hikariConfig.setJdbcUrl(resourceBundle.getString("MYSQL_DB_URL"));
                hikariConfig.setUsername(resourceBundle.getString("MYSQL_USER"));
                hikariConfig.setPassword(resourceBundle.getString("MYSQL_PASS"));

                hikariConfig.setMaximumPoolSize(100);
                hikariConfig.setAutoCommit(false);

                hikariConfig.setAutoCommit(true);
                hikariConfig.addDataSourceProperty( "cachePrepStmts" , "true" );
                hikariConfig.addDataSourceProperty( "prepStmtCacheSize" , "250" );
                hikariConfig.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );

                dataSource = new HikariDataSource(hikariConfig);
            }
        }catch (Exception e){
            logger.warning(e.getMessage());
        }
        return  dataSource;
    }

    public static DataSource getInstance(){
        if(dataSource == null){
            dataSource = getDataSource();
        }
        return dataSource;
    }

}
