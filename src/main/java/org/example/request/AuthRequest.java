package org.example.request;

import me.geso.tinyvalidator.constraints.Email;
import me.geso.tinyvalidator.constraints.Size;

public class AuthRequest implements BaseRequest {

    @Email
    private String email;

    @Size(min = 8)
    private String password;
    private String username;

    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String position;

    @Email
    private String workEmail;

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWorkEmail() {
        return workEmail;
    }

}