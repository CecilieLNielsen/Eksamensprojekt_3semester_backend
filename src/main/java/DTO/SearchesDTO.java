/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Searches;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author cecilie
 */
public class SearchesDTO {
    private int id;
    private DateTime dateTime;
    private String breedName;

    public SearchesDTO(Searches searches) {
        this.id = searches.getId();
        this.dateTime = searches.getDateTime();
        this.breedName = null;
    }

}
