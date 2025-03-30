package com.hrd.homework003.service;

import com.hrd.homework003.model.entity.Venues;
import com.hrd.homework003.model.request.VenuesRequest;

import java.util.List;

public interface VenueService {
    List<Venues> getAllVenuesPagination(Integer page, Integer size);

    Venues getVenueById(Integer venueId);

    Venues createVenue(VenuesRequest venuesRequest);

    Venues updateVenueById(Integer venueId, VenuesRequest venuesRequest);

    Venues deleteVenueById(Integer venueId);
}
