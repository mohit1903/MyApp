package com.mohit.myapp.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.myapp.modal.Event;
import com.mohit.myapp.repository.EventRepository;

@Service("eventService")
public class EventService  {
	
	@Autowired
	EventRepository eventRepository;
	
	EntityManager entityManager;


	
	public EventService(EventRepository eventRepository, EntityManager entityManager) {
		super();
		this.eventRepository = eventRepository;
		this.entityManager = entityManager;
	}
	public boolean booleanfindEvent(String id)
	{
		Query query = entityManager.createNativeQuery("SELECT * FROM event as em " +
			    "WHERE em.eventid LIKE "+id, Event.class);
		if(query.getResultList().size()==0)
			return false;
		else
			return true;
	}
	public void save(Event event)
	{
		eventRepository.save(event);
	}
	public long count()
	{
		return eventRepository.count();
	}
	public void delete(Event entity)
	{
		eventRepository.delete(entity);
	}
	public Iterable<Event> findallevent()
	{
		return eventRepository.findAll();
		
	}
	
	
	

}
