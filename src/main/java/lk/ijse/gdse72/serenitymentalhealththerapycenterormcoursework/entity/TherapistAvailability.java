package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "therapist_availability")
public class TherapistAvailability implements SuperEntity {
    @Id
    private String availability_id;

    @ManyToOne
    @JoinColumn(name = "therapist_id", nullable = false)
    private Therapist therapist;

    @Column(nullable = false)
    private LocalDate available_date;

    @Column(nullable = false)
    private LocalTime start_time;

    @Column(nullable = false)
    private LocalTime end_time;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "availability_slots", joinColumns = @JoinColumn(name = "availability_id"))
    @Column(name = "slot")
    private List<String> available_slots = new ArrayList<>(); // e.g., ["09:00-09:30", "09:30-10:00"]

    @Column(nullable = false)
    private boolean is_available = true;

}
