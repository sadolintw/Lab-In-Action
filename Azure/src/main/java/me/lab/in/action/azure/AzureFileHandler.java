package me.lab.in.action.azure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
		@PropertySource(value = "file:C:\\lab\\application.properties")
})
public class AzureFileHandler {

	public static void main(String[] args) {
		SpringApplication.run(AzureFileHandler.class, args);
	}

}
