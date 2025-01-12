package org.david.crytocurrency.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "report_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="market")
    private String market;

    @Column(name="price")
    private String price;

    @Column(name="reportAt")
    private LocalDateTime reportAt;

    public ReportHistory(String market, String price) {
        this.market = market;
        this.price = price;
        this.reportAt = LocalDateTime.now();
    }
}
