package com.web.backend.service.ticket;

import com.web.backend.dto.request.ticket.ScanTicketRequest;
import com.web.backend.dto.response.ticket.ScanTicketResponse;
import com.web.backend.dto.response.ticket.TicketDetailResponse;

import java.util.List;

public interface TicketService {
    List<TicketDetailResponse> getMyTickets();
    TicketDetailResponse getTicketById(Integer id);
    byte[] generateTicketQRCode(Integer ticketId);
    ScanTicketResponse scanTicket(ScanTicketRequest request);
    List<TicketDetailResponse> getTicketsByShowtime(Integer showtimeId);
}
