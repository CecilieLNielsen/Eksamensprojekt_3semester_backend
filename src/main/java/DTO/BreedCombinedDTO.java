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
public class BreedCombinedDTO {

    private String breed;
    private String info;
    private String wikipedia;
    private String image;
    private String[] facts;

    public BreedCombinedDTO(BreedInformationDTO breedInformationDTO, BreedImageDTO breedImageDTO, BreedFactsDTO breedFactsDTO) {
        this.breed = breedInformationDTO.getBreed();
        this.info = breedInformationDTO.getInfo();
        this.wikipedia = breedInformationDTO.getWikipedia();
        this.image = breedImageDTO.getImage();
        this.facts = breedFactsDTO.getFacts();
    }

    public String getBreed() {
        return breed;
    }

    public String getInfo() {
        return info;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public String getImage() {
        return image;
    }

    public String[] getFacts() {
        return facts;
    }
    
}
