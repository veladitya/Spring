package com.fedex;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fedex.lhso.ils.entity.Customer;
import com.fedex.lhso.ils.entity.Role;
import com.fedex.lhso.ils.entity.User;
import com.fedex.lhso.ils.repository.CustomerRepository;
import com.fedex.lhso.ils.repository.RoleRepository;
import com.fedex.lhso.ils.repository.UserRepository;

@SpringBootApplication
public class UiApplication extends SpringBootServletInitializer {
	private static final Logger log = LoggerFactory.getLogger(UiApplication.class);

	static ApplicationContext context = null;

	public static void main(String[] args) {
		context = SpringApplication.run(UiApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(UiApplication.class);
	}

	@Bean
	@Order(1)
	public CommandLineRunner loadUsers(UserRepository userRepository, RoleRepository roleRepository) {
		return (args) -> {

			// save a couple of roles
			roleRepository.save(new Role("ROLE_USER"));
			roleRepository.save(new Role("ROLE_ADMIN"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Role role : roleRepository.findAll()) {
				log.info(role.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Role role = roleRepository.findOne(1);
			log.info("Role found with findOne(1L):");
			log.info("--------------------------------");
			log.info(role!= null? role.toString(): "No Role Found");
			log.info("");

			// save an user with Admin roles
			User userAdmin = new User("admin", "admin", "admin@blogger.com", "ROLE_ADMIN");
			userAdmin.setRoles(roleRepository.findAllByOrderByNameAsc());

			userRepository.save(userAdmin);
		};
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer!= null?customer.toString(): "No Customer Found");
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByLastName("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");
		};
	}
	
	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
		b.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		return b;
	}		
}
