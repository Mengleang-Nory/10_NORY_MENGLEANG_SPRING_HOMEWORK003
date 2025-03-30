package com.hrd.homework003.service;

import com.hrd.homework003.model.entity.Attendees;
import com.hrd.homework003.model.request.AttendeeRequest;

import java.util.List;

public interface AttendeeService {
    List<Attendees> getAllAttendeesPagination(Integer page, Integer size);

    Attendees getAttendeesById(Integer attendeeId);

    Attendees createAttendee(AttendeeRequest attendeeRequest);

    Attendees deleteAttendeeById(Integer attendeeId);

    Attendees updateAttendeeById(Integer attendeeId,AttendeeRequest attendeeRequest);
}
