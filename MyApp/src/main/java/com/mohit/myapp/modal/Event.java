package com.mohit.myapp.modal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Synchronize;

@Entity
@Table(name="event")
public class Event implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int Id;
	@Column(name="eventname" , nullable=false)
	String eventName;
	@Column(name="eventid" , nullable=false)
	String eventId;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getEventName() {
		return eventName;
	}
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (Id != other.Id)
			return false;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		return true;
	}
	
	
	

}
