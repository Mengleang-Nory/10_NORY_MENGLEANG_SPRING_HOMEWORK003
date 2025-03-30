package com.hrd.homework003.service.serviceImpl;

import com.hrd.homework003.exception.UserNotFoundExceptionHandler;
import com.hrd.homework003.model.entity.Venues;
import com.hrd.homework003.model.request.VenuesRequest;
import com.hrd.homework003.repo.VenueRepo;
import com.hrd.homework003.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepo venueRepo;

    @Override
    public List<Venues> getAllVenuesPagination(Integer page, Integer size) {
        return venueRepo.getAllVenuesPagination(page, size);
    }

    @Override
    public Venues getVenueById(Integer venueId) {

        Venues venue = venueRepo.getVenueById(venueId);

        if (venue == null) throw new UserNotFoundExceptionHandler("The venue id " + venueId + " has not been founded.");
        else return venue;
    }

    @Override
    public Venues createVenue(VenuesRequest venuesRequest) {
        return venueRepo.createVenue(venuesRequest);
    }

    @Override
    public Venues updateVenueById(Integer venueId, VenuesRequest venuesRequest) {
        Venues venue = venueRepo.getVenueById(venueId);
        if (venue == null) throw new UserNotFoundExceptionHandler("The venue id " + venueId + " has not been founded.");
        else return venue;
    }

    @Override
    public Venues deleteVenueById(Integer venueId) {
        Venues venue = venueRepo.deleteVenueById(venueId);
        if (venue == null) throw new UserNotFoundExceptionHandler("The venue id " + venueId + " has not been founded.");
        else return null;
    }
}
