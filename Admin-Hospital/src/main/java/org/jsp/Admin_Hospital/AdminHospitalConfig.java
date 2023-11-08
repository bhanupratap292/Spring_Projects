package org.jsp.Admin_Hospital;


	import javax.persistence.EntityManager;
	import javax.persistence.Persistence;

	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.ComponentScan;
	import org.springframework.context.annotation.Configuration;

	@Configuration
	@ComponentScan(basePackages = "org.jsp.Admin_Hospital")
	public class AdminHospitalConfig {
		@Bean
		public EntityManager getEntityManager()
		{
			return Persistence.createEntityManagerFactory("dev").createEntityManager();
			
		}
	}



