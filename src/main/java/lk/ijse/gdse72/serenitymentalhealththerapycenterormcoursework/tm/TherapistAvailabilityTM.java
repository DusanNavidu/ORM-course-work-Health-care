package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TherapistAvailabilityTM {
    private String availabilityId;
    private String therapistId;
    private LocalDate availableDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean available;
}
