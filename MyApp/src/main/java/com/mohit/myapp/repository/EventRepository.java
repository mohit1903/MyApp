package com.mohit.myapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mohit.myapp.modal.Event;

@Repository("eventRepository")
public interface EventRepository extends CrudRepository<Event, Long> {

	
	
}
