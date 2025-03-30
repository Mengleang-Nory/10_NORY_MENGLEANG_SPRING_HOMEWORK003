package com.hrd.homework003.service.serviceImpl;

import com.hrd.homework003.exception.UserNotFoundExceptionHandler;
import com.hrd.homework003.model.entity.Attendees;
import com.hrd.homework003.model.request.AttendeeRequest;
import com.hrd.homework003.repo.AttendeeRepo;
import com.hrd.homework003.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {

    private final AttendeeRepo attendeeRepo;

    @Override
    public List<Attendees> getAllAttendeesPagination(Integer page, Integer size) {
        return attendeeRepo.getAllAttendeesPagination(page, size);
    }

    @Override
    public Attendees getAttendeesById(Integer attendeeId) {
        Attendees attendee = attendeeRepo.getAttendeeById(attendeeId);

        if (attendee == null)
            throw new UserNotFoundExceptionHandler("The attendee id " + attendeeId + " has not been founded.");
        else return attendee;
    }

    @Override
    public Attendees createAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepo.createAttendee(attendeeRequest);
    }

    @Override
    public Attendees deleteAttendeeById(Integer attendeeId) {
        Attendees attendee = attendeeRepo.deleteAttendeeById(attendeeId);

        if (attendee == null)
            throw new UserNotFoundExceptionHandler("The attendee id " + attendeeId + " has not been founded.");
        else return null;
    }

    @Override
    public Attendees updateAttendeeById(Integer attendeeId, AttendeeRequest attendeeRequest) {
        Attendees attendee = attendeeRepo.updateAttendeeById(attendeeId, attendeeRequest);

        if (attendee == null)
            throw new UserNotFoundExceptionHandler("The attendee id " + attendeeId + " has not been founded.");
        else return attendee;
    }
}
