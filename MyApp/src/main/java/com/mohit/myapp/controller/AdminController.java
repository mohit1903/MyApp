package com.mohit.myapp.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mohit.myapp.modal.Event;
import com.mohit.myapp.service.EventService;

import lombok.Synchronized;

@Controller
public class AdminController {
	
	private EventService eventService;
	
	



	public AdminController(EventService eventService) {
		super();
		this.eventService = eventService;
	}




   @Synchronized
	@RequestMapping(value = "/addevent", method = RequestMethod.POST)
		public ModelAndView register(@RequestParam Map<String,String> requestParams, ModelAndView modelAndView, @Valid Event event, BindingResult bindingResult, HttpServletRequest request) {
			
		 event.setEventName(requestParams.get("eventname"));
		 event.setEventId(requestParams.get("eventid"));
		 
		boolean eventExist=eventService.booleanfindEvent(event.getEventId());
		 
		 if(eventExist)
		 {
			 modelAndView.addObject("msg", "Oops!  Event Already exist.");
				modelAndView.setViewName("admin");
				bindingResult.reject("email");
		 }
		 else
		 {
			 eventService.save(event);
			 modelAndView.addObject("msg", "Event Added Successfully.");
				modelAndView.setViewName("admin");
			 
		 }
		 Iterator<Event> iter = eventService.findallevent().iterator();
		 List<Event> data=new ArrayList<>();
		 
		 while (iter.hasNext()) {
			 data.add(iter.next());

		 }
		 modelAndView.addObject("events", data);
		 
		 modelAndView.addObject("numberofevents", "Number of Events Present = "+eventService.count());
		 return modelAndView;
		 
	 }
			 
		 

}
