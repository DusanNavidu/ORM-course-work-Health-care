package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class TherapyProgramDTO {
    private String programId;
    private String name;
    private String duration;
    private BigDecimal fee;
    private String description;
}
