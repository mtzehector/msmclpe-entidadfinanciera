/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.gob.imss.dpes.entidadfinancieraback.config;


import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
//import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author antonio
 */
@Configuration
@ComponentScan(basePackages = {
  "mx.gob.imss.dpes.entidadfinancieraback.repository"
})
@PropertySource({ "classpath:application.properties" })
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "mx.gob.imss.dpes.entidadfinancieraback.repository")
public class TestContext {
  
  @Autowired
  private Environment env;
  
  
  @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
    
  @Bean
  public DataSource dataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(env.getProperty("db.driver"));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.user"));
      dataSource.setPassword(env.getProperty("db.pass")); 
      return dataSource;
  }
  
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
                  
      entityManagerFactoryBean.setPackagesToScan("mx.gob.imss.dpes.entidadfinancieraback");
      
      Properties jpaProperties = new Properties();
      jpaProperties.put("hibernate.show_sql", "true" );
      jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect" );
      
      entityManagerFactoryBean.setJpaProperties(jpaProperties);
      
      
      entityManagerFactoryBean.setDataSource(dataSource());
      entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter() );            
      entityManagerFactoryBean.setJpaDialect( new HibernateJpaDialect() );
      
      return entityManagerFactoryBean;
  }

}
