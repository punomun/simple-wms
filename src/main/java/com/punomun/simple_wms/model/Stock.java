package com.punomun.simple_wms.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"product_id", "warehouse_id"}) // Prevent duplicates
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @Column(nullable = false)
    private Integer quantity;
}