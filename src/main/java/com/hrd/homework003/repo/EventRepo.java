package com.hrd.homework003.repo;

import com.hrd.homework003.model.entity.Attendees;
import com.hrd.homework003.model.entity.Events;
import com.hrd.homework003.model.request.EventRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventRepo {

    @Select("""
            select * from events limit #{size} offset #{size} * (#{page} - 1);
            """)
    @Results(id = "Events", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id",
                    one = @One(select = "com.hrd.homework003.repo.VenueRepo.getVenueById")),
            @Result(property = "attendees", column = "event_id",
                    many = @Many(select = "gatAllAttendeesByVenueId"))
    })
    List<Events> getAllEventsPagination(Integer page, Integer size);

    @Select("""
            select *
            from attendees a
                     inner join evant_attendee ea on a.attendee_id = ea.attendee_id
            where ea.event_id = #{eventId};
            """)
    @Results(id = "Attendees", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
    List<Attendees> gatAllAttendeesByVenueId(Integer eventId);

    @Select("""
            select * from events where event_id = #{eventId};
            """)
    @ResultMap("Events")
    Events getEventById(Integer eventId);

    @Select("""
            insert into events (event_name, event_date, venue_id)
            values (#{event.eventName},#{event.eventDate},#{event.venueId})
            returning event_id;
            """)
    Integer createEvent(@Param("event") EventRequest eventRequest);

    @Select("""
            insert into evant_attendee (event_id, attendee_id)
            values (#{eventId},#{attendeeId});
            """)
    void insertEvantAttendee(Integer eventId, Integer attendeeId);

    @Select("""
            delete from events where event_id = #{eventId} returning event_id;
            """)
    Integer updateDeleteOldEvantById(Integer eventId);

    @Select("""
            insert into events (event_id, event_name, event_date, venue_id)
            values (#{eventId},#{event.eventName},#{event.eventDate},#{event.venueId})
            returning event_id;
            """)
    Integer updateInsertEventById(Integer eventId,@Param("event") EventRequest eventRequest);

    @Select("""
            delete from events where event_id = #{eventId} returning *;
            """)
    Events deleteEventById(Integer eventId);
}
