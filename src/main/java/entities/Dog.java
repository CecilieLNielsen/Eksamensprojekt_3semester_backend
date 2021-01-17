/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import DTO.DogDTO;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author cecilie
 */
@Entity
public class Dog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String dateOfBirth;
    private String info;
    private String breed;

    @ManyToOne
    private User user;

    //  @ManyToOne
    //  private Breed breed;
    public Dog() {
    }

    public Dog(int id, String name, String dateOfBirth, String info, String breed) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.info = info;
        this.breed = breed;
    }

    public Dog(DogDTO dogDTO) {
        this.name = dogDTO.getName();
        this.dateOfBirth = dogDTO.getDateOfBirth();
        this.info = dogDTO.getInfo();
        this.breed = dogDTO.getBreedName();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            user.getDogs().add(this);
        }
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

}
