package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserTM {
    private int userId;
    private String userName;
    private String email;
    private int phone;
    private int password;
}