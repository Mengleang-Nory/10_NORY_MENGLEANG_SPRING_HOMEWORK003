package com.hrd.homework003.service;

import com.hrd.homework003.model.entity.Events;
import com.hrd.homework003.model.request.EventRequest;

import java.util.List;

public interface EventService {
    List<Events> getAllEventsPagination(Integer page, Integer size);

    Events getEventById(Integer eventId);

    Events createEvent(EventRequest eventRequest);

    Events updateEventById(Integer eventId, EventRequest eventRequest);

    Events deleteEventById(Integer eventId);
}
