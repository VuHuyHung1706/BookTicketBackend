package com.web.backend.controller;

import com.web.backend.dto.request.booking.BookingRequest;
import com.web.backend.dto.response.ApiResponse;
import com.web.backend.dto.response.booking.BookingResponse;
import com.web.backend.service.booking.BookingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@SecurityRequirement(name = "Bearer")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ApiResponse<BookingResponse> bookTickets(@Valid @RequestBody BookingRequest request) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.bookTickets(request))
                .build();
    }

    @GetMapping("/{invoiceId}")
    public ApiResponse<BookingResponse> getBookingById(@PathVariable Integer invoiceId) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.getBookingById(invoiceId))
                .build();
    }

    @GetMapping("/my-bookings")
    public ApiResponse<List<BookingResponse>> getMyBookings() {
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getMyBookings())
                .build();
    }
}
