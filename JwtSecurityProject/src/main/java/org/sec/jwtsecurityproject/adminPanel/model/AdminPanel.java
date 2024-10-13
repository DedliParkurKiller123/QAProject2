package org.sec.jwtsecurityproject.adminPanel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPanel {
    @SequenceGenerator(
            name="seq_admin_panel_gen",
            sequenceName = "seq_admin_panel_gen",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_admin_panel_gen"
    )
    @Id
    private Long IDAdminPanel;
    private String name;
    private Double price;
    private LocalDate dataAdded;
    private String description;

    public AdminPanel(String name, Double price, LocalDate dataAdded, String description) {
        this.name = name;
        this.price = price;
        this.dataAdded = dataAdded;
        this.description = description;
    }
}
