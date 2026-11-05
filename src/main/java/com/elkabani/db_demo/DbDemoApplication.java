package com.elkabani.db_demo;

import com.elkabani.db_demo.entities.Address;
import com.elkabani.db_demo.entities.User;
import com.elkabani.db_demo.repositories.UserRepository;
import com.elkabani.db_demo.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DbDemoApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(DbDemoApplication.class, args);
       // var repository = context.getBean(UserRepository.class);
      //  var service = context.getBean(UserService.class);

      //  service.showEntityState();

     //   service.showRelatedEntities();

    //    service.persistRelated();
      //   service.deleteRelated();
      //  var user = User.builder()
      //          .name("John Doe")
      //          .email("john@example.com")
      //          .password("password")
      //          .build();

      //  var address = Address.builder()
      //          .city("New York")
      //          .state("NY")
      //          .street("123 Main St")
      //          .zipCode("10001")
      //          .build();

       // user.addAddress(address);

       // System.out.println(user);
       //  repository.save(user);
       //  user = repository.findById(1L).orElseThrow();
       //  System.out.println(user.getEmail());
       //  repository.deleteById(1L);
    }

}
