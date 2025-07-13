package com.web.backend.entity;

import com.web.backend.constant.InvoiceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "invoices")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "total_amount")
    private Integer totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Builder.Default
    private InvoiceStatus status = InvoiceStatus.CREATED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    @ToString.Exclude
    private Account account;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Ticket> tickets;
}
