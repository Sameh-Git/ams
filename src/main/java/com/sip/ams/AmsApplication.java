package com.sip.ams;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import
org.springframework.boot.autoconfigure.SpringBootApplication;
import
org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.SpringServletContainerInitializer;

import com.sip.ams.controllers.ArticleController;
import com.sip.ams.controllers.ProviderController;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.MessagingException;
import java.io.IOException;
import java.io.File;



@SpringBootApplication
public class AmsApplication    {

	public static void main(String[] args) {
		
		
		new File(ArticleController.uploadDirectory).mkdir();
		new File(ProviderController.uploadDirectory).mkdir();
		SpringApplication.run(AmsApplication.class, args);
	
	}

}
