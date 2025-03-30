package com.hrd.homework003.service.serviceImpl;

import com.hrd.homework003.exception.UserNotFoundExceptionHandler;
import com.hrd.homework003.model.entity.Events;
import com.hrd.homework003.model.request.EventRequest;
import com.hrd.homework003.repo.EventRepo;
import com.hrd.homework003.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepo;

    @Override
    public List<Events> getAllEventsPagination(Integer page, Integer size) {
        return eventRepo.getAllEventsPagination(page, size);
    }

    @Override
    public Events getEventById(Integer eventId) {
        Events event = eventRepo.getEventById(eventId);

        if (event == null) throw new UserNotFoundExceptionHandler("The event id " + eventId + " has not been founded.");
        else return event;
    }

    @Override
    public Events createEvent(EventRequest eventRequest) {
        Integer newEventId = eventRepo.createEvent(eventRequest);

        for (Integer attendeeId : eventRequest.getAttendeeId()) {
            eventRepo.insertEvantAttendee(newEventId, attendeeId);
        }

        return eventRepo.getEventById(newEventId);
    }

    @Override
    public Events updateEventById(Integer eventId, EventRequest eventRequest) {

        Integer tempEventId = eventRepo.updateDeleteOldEvantById(eventId);

        if (tempEventId == null)
            throw new UserNotFoundExceptionHandler("The event id " + eventId + " has not been founded.");
        else {
            Integer newEventId = eventRepo.updateInsertEventById(tempEventId, eventRequest);

            for (Integer attendeeId : eventRequest.getAttendeeId()) {
                eventRepo.insertEvantAttendee(newEventId, attendeeId);
            }

            return eventRepo.getEventById(newEventId);
        }
    }

    @Override
    public Events deleteEventById(Integer eventId) {
        Events event = eventRepo.deleteEventById(eventId);

        if (event == null) throw new UserNotFoundExceptionHandler("The event id " + eventId + " has not been founded.");
        else return null;
    }
}
