
package pl.com.bottega.hrs.model.commands;

import pl.com.bottega.hrs.model.Address;
import pl.com.bottega.hrs.model.Gender;

import java.time.LocalDate;

public class ChangeEmployeeProfileCommand implements Command {

    private String firstName, lastName;

    private LocalDate birthDate;

    private Address address;

    private Gender gender;

    private Integer empNo;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public void validate(ValidationErrors errors) {
        if (firstName == null || firstName.trim().length() == 0) {
            errors.add("firstName", "can't be blank");
        }
        if (lastName == null || lastName.trim().length() == 0) {
            errors.add("lastName", "can't be blank");
        }
        if (birthDate == null) {
            errors.add("birthDate", "can't be blank");
        }
        if (birthDate != null && birthDate.isAfter(LocalDate.now())) {
            errors.add("birthDate", "must be in the past");
        }
    }

}
