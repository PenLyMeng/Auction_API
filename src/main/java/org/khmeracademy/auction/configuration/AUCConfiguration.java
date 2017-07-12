package org.khmeracademy.auction.configuration;

import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableScheduling
@MapperScan("org.khmeracademy.auction.repositories")
@EnableWebMvc
public class AUCConfiguration  extends WebMvcConfigurerAdapter{
		@Bean 
		public DataSource getDataSource(){
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("org.postgresql.Driver");
			dataSource.setUrl("jdbc:postgresql://120.136.24.174:5432/AUCTION_SR_DB");
			dataSource.setUsername("AUCTION_SR");
			dataSource.setPassword("AUCTION_SR");
//			dataSource.setUrl("jdbc:postgresql://localhost:5432/AUCTION_SR_DB");
//			dataSource.setUsername("postgres");
//			dataSource.setPassword("123");
			return dataSource;
		}
		
		@Bean
	    public DataSourceTransactionManager transactionManager() {
	        return new DataSourceTransactionManager(getDataSource());
	    }

	 
	    @Bean
	    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
	        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	        sessionFactory.setDataSource(getDataSource());
	        return sessionFactory;
	    }
	    
	    
	    // Upload file bean
	    @Bean(name="multipartResolver") 
	    public CommonsMultipartResolver multipartResolver() throws IOException{
	        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	         
	        //Set the maximum allowed size (in bytes) for each individual file.
	        resolver.setMaxUploadSizePerFile(5242880);//5MB
	         
	        //You may also set other available properties.
	        return resolver;
	    }
		
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/upload").setViewName("upload");
		}
		
		@Bean
		public ViewResolver viewResolver(){
			InternalResourceViewResolver resolver = new InternalResourceViewResolver();
			resolver.setPrefix("/WEB-INF/pages/");
			resolver.setSuffix(".jsp");
			return resolver;
		}
		
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/resources/**").addResourceLocations("/resources/","file:/opt/AUCTION/");
			registry.addResourceHandler("/files/images/**").addResourceLocations("file:/opt/images/");
			registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}
		
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
					.allowedMethods("GET","POST","DELETE","PUT","OPTIONS","PATCH")
					.allowedOrigins("*");
		}
		

}
