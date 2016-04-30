package com.mycompany.exceptionmapper;


public class ErrorDTO {

    private String errorMessage;

    public ErrorDTO() {
        //empty constructor
    }

    public ErrorDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }   
}
