package com.hrd.homework003.controller;

import com.hrd.homework003.model.entity.Attendees;
import com.hrd.homework003.model.entity.Venues;
import com.hrd.homework003.model.request.VenuesRequest;
import com.hrd.homework003.model.respone.ApiRespone;
import com.hrd.homework003.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @GetMapping
    @Operation(summary = "Get all venues")
    public ResponseEntity<ApiRespone<List<Venues>>> getAllVenuesPagination(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        List<Venues> venues = venueService.getAllVenuesPagination(page, size);
        ApiRespone<List<Venues>> respone = ApiRespone.<List<Venues>>builder()
                .message("All venues have been successfully fetched.")
                .payload(venues)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(respone, HttpStatus.OK);
    }

    @GetMapping("/{venue-id}")
    @Operation(summary = "Get venue by ID")
    public ResponseEntity<ApiRespone<Venues>> getVenueById(@PathVariable("venue-id") Integer venueId) {
        Venues venue = venueService.getVenueById(venueId);
        ApiRespone<Venues> respone = ApiRespone.<Venues>builder()
                .message("The venue has been successfully founded.")
                .payload(venue)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(respone, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add a new venue")
    public ResponseEntity<ApiRespone<Venues>> createVenue(@RequestBody VenuesRequest venuesRequest) {
        Venues venue = venueService.createVenue(venuesRequest);
        ApiRespone<Venues> respone = ApiRespone.<Venues>builder()
                .message("The Venue has been successfully added.")
                .payload(venue)
                .status(HttpStatus.CREATED)
                .build();

        return new ResponseEntity<>(respone, HttpStatus.CREATED);
    }

    @PutMapping("/{venue-id}")
    @Operation(summary = "Update venue by ID")
    public ResponseEntity<ApiRespone<Venues>> updateVenueById(
            @PathVariable("venue-id") Integer venueId,
            @RequestBody VenuesRequest venuesRequest) {
        Venues venue = venueService.updateVenueById(venueId,venuesRequest);
        ApiRespone<Venues> respone = ApiRespone.<Venues>builder()
                .message("The venue has been successfully updated.")
                .payload(venue)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(respone, HttpStatus.OK);
    }

    @DeleteMapping("/{venue-id}")
    @Operation(summary = "Delete venue by ID")
    public ResponseEntity<ApiRespone<Venues>> deleteVenueById(@PathVariable("venue-id") Integer venueId) {
        Venues venue = venueService.deleteVenueById(venueId);
        ApiRespone<Venues> respone = ApiRespone.<Venues>builder()
                .message("The attendee has been successfully deleted.")
                .payload(venue)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(respone, HttpStatus.OK);
    }
}
