/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.demo;

/**
 *
 * @author cecilie
 */
public class AwesomeDTO {
    private String message;
    private String subtitle;

    public AwesomeDTO(String message, String subtitle) {
        this.message = message;
        this.subtitle = subtitle;
    }

    public String getMessage() {
        return message;
    }

    public String getSubtitle() {
        return subtitle;
    }
    
    
}
