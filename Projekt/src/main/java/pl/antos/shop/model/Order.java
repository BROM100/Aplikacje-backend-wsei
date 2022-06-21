package pl.antos.shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "items")
    private String items;
    @Column(name = "status")
    private String status;
    @Column(name = "address")
    private String address;
    @Column(name = "invoice_address")
    private String invoiceAddress;
    @Column(name = "special_notes")
    private String specialNotes;
    @Column(name = "reference")
    private String reference;
    @Column(name = "created_at")
    Instant createdAt;
}