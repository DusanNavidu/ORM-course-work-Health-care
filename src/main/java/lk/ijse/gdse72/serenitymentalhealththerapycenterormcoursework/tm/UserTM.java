package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserTM {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String role;
}
