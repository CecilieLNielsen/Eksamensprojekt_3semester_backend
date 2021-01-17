/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author cecilie
 */
public class BreedFactsDTO {
    private String[] facts;

    public BreedFactsDTO(String[] facts) {
        this.facts = facts;
    }

    public String[] getFacts() {
        return facts;
    }
    
}
