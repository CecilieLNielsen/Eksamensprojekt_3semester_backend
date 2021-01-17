/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Dog;

/**
 *
 * @author cecilie
 */
public class DogDTO {

    private int id;
    private String name;
    private String dateOfBirth;
    private String info;
    private String breedName;

    public DogDTO(Dog dog) {
        this.id = dog.getId();
        this.name = dog.getName();
        this.dateOfBirth = dog.getDateOfBirth();
        this.info = dog.getInfo();
        this.breedName = dog.getBreed();
 

    }

    public DogDTO(String name, String dateOfBirth, String info, String breed) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.info = info;
        this.breedName = breed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }
    
    

}
