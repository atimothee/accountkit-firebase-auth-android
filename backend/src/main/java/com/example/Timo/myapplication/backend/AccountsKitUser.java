package com.example.Timo.myapplication.backend;

/**
 * Created by Timo on 20/08/16.
 */
public class AccountsKitUser {

    public AccountsKitUser(){

    }

    private String id;
    private Phone phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    static class Phone{
        public Phone(){

        }

        private String number;
        private String countryPrefix;
        private String nationalNumber;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getCountryPrefix() {
            return countryPrefix;
        }

        public void setCountryPrefix(String countryPrefix) {
            this.countryPrefix = countryPrefix;
        }

        public String getNationalNumber() {
            return nationalNumber;
        }

        public void setNationalNumber(String nationalNumber) {
            this.nationalNumber = nationalNumber;
        }
    }
}
