package com.hrd.homework003.controller;

import com.hrd.homework003.model.entity.Attendees;
import com.hrd.homework003.model.request.AttendeeRequest;
import com.hrd.homework003.model.respone.ApiRespone;
import com.hrd.homework003.service.AttendeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;

    @GetMapping
    @Operation(summary = "Get all attendees")
    public ResponseEntity<ApiRespone<List<Attendees>>> getAllAttendeesPagination(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        List<Attendees> attendees = attendeeService.getAllAttendeesPagination(page, size);
        ApiRespone<List<Attendees>> respone = ApiRespone.<List<Attendees>>builder()
                .message("All attendees have been successfully fetched.")
                .payload(attendees)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(respone, HttpStatus.OK);
    }

    @GetMapping("/{attendee-id}")
    @Operation(summary = "Get attendee by ID")
    public ResponseEntity<ApiRespone<Attendees>> getAttendeeById(@PathVariable("attendee-id") Integer attendeeId) {
        Attendees attendee = attendeeService.getAttendeesById(attendeeId);
        ApiRespone<Attendees> respone = ApiRespone.<Attendees>builder()
                .message("The attendee has been successfully founded.")
                .payload(attendee)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(respone, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add a new attendee")
    public ResponseEntity<ApiRespone<Attendees>> createAttendee(@RequestBody AttendeeRequest attendeeRequest) {
        Attendees attendee = attendeeService.createAttendee(attendeeRequest);
        ApiRespone<Attendees> respone = ApiRespone.<Attendees>builder()
                .message("The attendee has been successfully added.")
                .payload(attendee)
                .status(HttpStatus.CREATED)
                .build();

        return new ResponseEntity<>(respone, HttpStatus.CREATED);
    }

    @DeleteMapping("/{attendee-id}")
    @Operation(summary = "Delete attendee by ID")
    public ResponseEntity<ApiRespone<Attendees>> deleteAttendeeById(@PathVariable("attendee-id") Integer attendeeId) {
        Attendees attendee = attendeeService.deleteAttendeeById(attendeeId);
        ApiRespone<Attendees> respone = ApiRespone.<Attendees>builder()
                .message("The attendee has been successfully deleted.")
                .payload(attendee)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(respone, HttpStatus.OK);
    }

    @PutMapping("/{attendee-id}")
    @Operation(summary = "Update attendee by ID")
    public ResponseEntity<ApiRespone<Attendees>> updateAttendeeById(
            @PathVariable("attendee-id") Integer attendeeId
            ,@RequestBody AttendeeRequest attendeeRequest
    ) {
        Attendees attendee = attendeeService.updateAttendeeById(attendeeId,attendeeRequest);
        ApiRespone<Attendees> respone = ApiRespone.<Attendees>builder()
                .message("The attendee has been successfully updated.")
                .payload(attendee)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(respone, HttpStatus.OK);
    }

}
