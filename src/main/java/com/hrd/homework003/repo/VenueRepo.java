package com.hrd.homework003.repo;

import com.hrd.homework003.model.entity.Venues;
import com.hrd.homework003.model.request.VenuesRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepo {

    @Select("""
            select * from venues limit #{size} offset #{size} * (#{page} - 1);
            """)
    @Results(id = "Venues", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
            @Result(property = "location", column = "location")
    })
    List<Venues> getAllVenuesPagination(Integer page, Integer size);


    @Select("""
            select * from venues where venue_id = #{venueId};
            """)
    @ResultMap("Venues")
    Venues getVenueById(Integer venueId);

    @Select("""
            insert into venues (venue_name, location)
            values (#{venue.venueName}, #{venue.location})
            returning *;
            """)
    @ResultMap("Venues")
    Venues createVenue(@Param("venue") VenuesRequest venuesRequest);

    @Select("""
            update venues
            set venue_name = #{venue.venueName},
                location = #{venue.location}
            where venue_id = #{venueId}
            returning *;
            """)
    @ResultMap("Venues")
    Venues updateVenueById(Integer venueId,@Param("venue") VenuesRequest venuesRequest);

    @Select("""
            delete from venues where venue_id = #{venueId} returning *;
            """)
    Venues deleteVenueById(Integer venueId);

}
