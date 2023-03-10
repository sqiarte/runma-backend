package com.gable.runma.service;

import java.util.List;
import java.util.Optional;

import com.gable.runma.model.Organizer;
import com.gable.runma.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gable.runma.model.Event;
import com.gable.runma.model.RaceType;
import com.gable.runma.repository.EventRepository;
import com.gable.runma.repository.RaceTypeRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository repo;
	@Autowired
	private RaceTypeRepository rtRepo;
	@Autowired
	private OrganizerRepository organizerRepository;

	public List<Event> findAll() {
		return repo.findAll();
	}

	// post event

//	public void save(Event theEvent, Integer ogId) {
//		Optional<Organizer> byId = organizerRepository.findById(ogId);
//
//		if (byId.isPresent()) {
//			Organizer organizer = byId.get();
//			List<Event> eventList = organizer.getEventList();
//
//			eventList.add(theEvent);
//			organizer.setEventList(eventList);
//
//			organizerRepository.save(organizer);
//		} else {
//			throw new RuntimeException("Organizer with id " + ogId + " not found.");
//		}

public Event newEvent(Event event) {
	Event e = repo.save(event);
	if (event.getRaceTypeList() != null) {
		for (RaceType rt : event.getRaceTypeList()) {
			rt.setEvent(e);
			rtRepo.save(rt);

		}
	}
	if (event.getOrganizerList() != null) {

	}

	return repo.save(event);
}

	public Event update(Event data) {
		Event event = repo.findById(data.getId()).orElseThrow();
		if (event != null) {
		
			return repo.save(data);
		}
		return event;
	}

	public void delete(Integer id) {
		repo.deleteById(id);
		/*
		Event event = repo.findById(id).orElse(null);
		if (event != null) {
		
			repo.delete(event);
		}
		*/
	}

	public Event findOne(Integer id) {
		Optional<Event> op = repo.findById(id);
		Event result = op.orElseThrow();
		return result;
	}

	public RaceType newRaceType(RaceType raceType) {
		Event event = repo.findById(raceType.getEvent().getId()).orElseThrow();
		raceType.setEvent(event);
		
		
		return rtRepo.save(raceType);
	}

}
