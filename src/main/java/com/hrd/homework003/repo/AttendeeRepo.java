package com.hrd.homework003.repo;

import com.hrd.homework003.model.entity.Attendees;
import com.hrd.homework003.model.request.AttendeeRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepo {

    @Select("""
            select * from attendees limit #{size} offset #{size} * (#{page} - 1);
            """)
    @Results(id = "Attendees", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
    List<Attendees> getAllAttendeesPagination(Integer page, Integer size);

    @Select("""
            select * from attendees where attendee_id = #{attendeeId};""")
    @ResultMap("Attendees")
    Attendees getAttendeeById(Integer attendeeId);

    @Select("""
                insert into attendees (attendee_name, email) values (#{attendee.attendeeName},#{attendee.email}) returning *;
            """)
    @ResultMap("Attendees")
    Attendees createAttendee(@Param("attendee") AttendeeRequest attendeeRequest);

    @Select("""
            delete from attendees where attendee_id = #{attendeeId} returning *;;
            """)
    Attendees deleteAttendeeById(Integer attendeeId);

    @Select("""
            update attendees
            set attendee_name = #{attendee.attendeeName}, email=#{attendee.email}
            where attendee_id = #{attendeeId}
            returning *;
            """)
    @ResultMap("Attendees")
    Attendees updateAttendeeById(Integer attendeeId,@Param("attendee") AttendeeRequest attendeeRequest);
}