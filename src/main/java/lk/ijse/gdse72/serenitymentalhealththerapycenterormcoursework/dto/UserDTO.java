package lk.ijse.gdse72.serenitymentalhealththerapycenterormcoursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
    private int userId;
    private String userName;
    private String email;
    private int phone;
    private int password;

    public UserDTO(String userName, String email, int phone, int password) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
