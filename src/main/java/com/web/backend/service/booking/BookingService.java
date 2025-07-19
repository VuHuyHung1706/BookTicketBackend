package com.web.backend.service.booking;

import com.web.backend.dto.request.booking.BookingRequest;
import com.web.backend.dto.response.booking.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse bookTickets(BookingRequest request);
    BookingResponse getBookingById(Integer invoiceId);
    List<BookingResponse> getMyBookings();
}
