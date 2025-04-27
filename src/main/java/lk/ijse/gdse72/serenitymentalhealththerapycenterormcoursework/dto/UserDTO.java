package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String role;
}
