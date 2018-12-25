package com.mohit.myapp.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mohit.myapp.modal.Event;
import com.mohit.myapp.modal.User;
import com.mohit.myapp.service.EmailService;
import com.mohit.myapp.service.EventService;
import com.mohit.myapp.service.UserService;

import lombok.Synchronized;

@Controller
public class MyController {
	
	private UserService userService;
	private EmailService emailService;
	private EventService eventService;
	
	
	
	public MyController(UserService userService, EmailService emailService, EventService eventService) {
		super();
		this.userService = userService;
		this.emailService = emailService;
		this.eventService = eventService;
	}
	@RequestMapping("/")  
	public String login() {   
	 return "login"; 
	 }
	@RequestMapping("/admin")  
	public String admin() {   
	 return "admin"; 
	 }
@RequestMapping("/about")  
public String about() {   
 return "about"; 
 }
 @RequestMapping("/user")  
	public String user() {   
	 return "user"; 
	 }
	 @RequestMapping("/home")  
		public String home() {   
		 return "home"; 
		 }
	 @RequestMapping("/logout")  
		public String error() {   
		 return "logout"; 
		 }
	 
	 @RequestMapping("/signup")  
		public String signup() {   
		 return "signup"; 
		 }
	 @RequestMapping("/transaction")  
		public String transaction() {   
		 return "transaction"; 
		 }
	 
	 @RequestMapping(value = "/register", method = RequestMethod.POST)
		public ModelAndView register(@RequestParam Map<String,String> requestParams, ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request) {
		 
			// Lookup user in database by e-mail
		 user.setName(requestParams.get("username"));
			user.setPassword(requestParams.get("password"));
			user.setEmail(requestParams.get("Email"));
			User userExists = userService.findByEmail(user.getEmail());
			if(userService.count()==0l)
			{
				user.setPrivilage("admn");
			}
			else
				user.setPrivilage("user");
			
			
			System.out.println(userExists);
			  
			
			if (userExists != null) {
				modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
				modelAndView.setViewName("user");
				bindingResult.reject("email");
				
			}
				
			if (bindingResult.hasErrors()) { 
				modelAndView.setViewName("signup");	
				modelAndView.addObject("error", "User Already Exist");
			} else { // new user so we create user and send confirmation e-mail
						
				// Disable user until they click on confirmation link in email
			    user.setEnabled(false);
			    modelAndView.setViewName("home");
			    
			    // Generate random 36-character string token for confirmation link
			    user.setConfirmationToken(UUID.randomUUID().toString());
			        
			    //userService.saveUser(user);
			    String appUrl = request.getScheme() + "://" + request.getServerName();
				
				SimpleMailMessage registrationEmail = new SimpleMailMessage();
				
				registrationEmail.setTo(user.getEmail());
				registrationEmail.setSubject("CrowdFunding Registration Confirmation");
				registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
						+ appUrl + "/confirm?token=" + user.getConfirmationToken());
				registrationEmail.setFrom("mohit190392@gmail.com");
				
				emailService.sendEmail(registrationEmail);
				
				modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
				modelAndView.setViewName("login");
				userService.saveUser(user);
			}
			
	 return modelAndView;

}
	 
	 @Synchronized
	 @RequestMapping(value = "/loginuser", method = RequestMethod.POST)
		public ModelAndView loginuser(@RequestParam Map<String,String> requestParams, ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request) {
		 
			// Lookup user in database by e-mail
		 //user.setName(requestParams.get("username"));
			user.setPassword(requestParams.get("password"));
			user.setEmail(requestParams.get("username"));
			boolean validate = false;
			User users = userService.findByEmail(user.getEmail());
			
			if(users != null && users.getPassword().equals(user.getPassword()))
				validate=true;
			if (validate == false) {
				modelAndView.addObject("error", "Oops!  There is no user registered with the email provided.");
				modelAndView.setViewName("login");
				bindingResult.reject("email");
				
			}
			else
			{
				modelAndView.addObject("Welcome", "Welcome to our CrowFunding Application");
				modelAndView.addObject("name", users.getName().toUpperCase());
				if(users.getPrivilage().equals("user"))
				{
				modelAndView.setViewName("user");
				 Iterator<Event> iter = eventService.findallevent().iterator();
				 List<Event> data=new ArrayList<>();
				 
				 while (iter.hasNext()) {
					 data.add(iter.next());

				 }
				 modelAndView.addObject("events", data);
				
				}
				else
			    modelAndView.setViewName("admin");
				bindingResult.reject("email");
			}
			
			
			
			
			return modelAndView;
} 
	
}
	 
