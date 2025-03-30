package com.hrd.homework003.controller;

import com.hrd.homework003.model.entity.Events;
import com.hrd.homework003.model.request.EventRequest;
import com.hrd.homework003.model.respone.ApiRespone;
import com.hrd.homework003.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    @Operation(summary = "Get all events")
    public ResponseEntity<ApiRespone<List<Events>>> getAllEventsPagination(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        List<Events> events = eventService.getAllEventsPagination(page, size);
        ApiRespone<List<Events>> respone = ApiRespone.<List<Events>>builder()
                .message("All events have been successfully fetched.")
                .payload(events)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(respone, HttpStatus.OK);
    }

    @GetMapping("/{event-id}")
    @Operation(summary = "Get event by ID")
    public ResponseEntity<ApiRespone<Events>> getEventById(
            @PathVariable("event-id") Integer eventId) {
        Events events = eventService.getEventById(eventId);
        ApiRespone<Events> respone = ApiRespone.<Events>builder()
                .message("The event has been successfully founded.")
                .payload(events)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(respone, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add a new event")
    public ResponseEntity<ApiRespone<Events>> createEvent(
            @RequestBody EventRequest eventRequest) {
        Events event = eventService.createEvent(eventRequest);
        ApiRespone<Events> respone = ApiRespone.<Events>builder()
                .message("The event has been successfully added.")
                .payload(event)
                .status(HttpStatus.CREATED)
                .build();

        return new ResponseEntity<>(respone, HttpStatus.CREATED);
    }

    @PutMapping("/{event-id}")
    @Operation(summary = "Update event by ID")
    public ResponseEntity<ApiRespone<Events>> updateEventById(
            @PathVariable("event-id") Integer eventId,
            @RequestBody EventRequest eventRequest) {
        Events event = eventService.updateEventById(eventId,eventRequest);
        ApiRespone<Events> respone = ApiRespone.<Events>builder()
                .message("The event has been successfully updated.")
                .payload(event)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(respone, HttpStatus.OK);
    }

    @DeleteMapping("/{event-id}")
    @Operation(summary = "Delete event by ID")
    public ResponseEntity<ApiRespone<Events>> deleteEventById(
            @PathVariable("event-id") Integer eventId) {
        Events event = eventService.deleteEventById(eventId);
        ApiRespone<Events> respone = ApiRespone.<Events>builder()
                .message("The event has been successfully deleted.")
                .payload(event)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(respone, HttpStatus.OK);
    }
}
