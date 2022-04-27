package com.example.demo.pocoPackage;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Customer implements POCO{
    public long id;
    public String firstName;
    public String lastName;
    public String address;
    public String phoneNumber;
    public String creditCardNumber;
    public long userId;

    public Customer(long id, String firstName, String lastName, String address, String phoneNumber, String creditCardNumber, long userId) {
        if(!phoneNumber.matches("[0-9]+")) {
            try {
                throw new Exception("phone number must contains only digits");
            } catch (Exception e) {
                e.printStackTrace();
            }
            phoneNumber = null;
        }
//        if(!creditCardNumber.matches("[0-9]+")) {
//            try {
//                throw new Exception("credit card number number must contains only digits");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            creditCardNumber = null;
//        }
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.creditCardNumber = creditCardNumber;
        this.userId = userId;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", userId=" + userId +
                '}';
    }
}
