package com.db.tradestorage;
/**
 * Main class of Trade transmission
 * Date Create: 02/05/2021
 * @author Mansi Deshmukh
 *
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradestorageApplication {

	public static void main(String[] args) {

		SpringApplication.run(TradestorageApplication.class, args);
	}

}
