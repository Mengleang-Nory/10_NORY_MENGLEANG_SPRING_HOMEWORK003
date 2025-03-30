package com.hrd.homework003.model.request;

import com.hrd.homework003.model.entity.Attendees;
import com.hrd.homework003.model.entity.Venues;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private String eventName;
    private String eventDate;
    private Integer venueId;
    private List<Integer> attendeeId;
}
