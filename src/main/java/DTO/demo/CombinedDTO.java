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
public class CombinedDTO {

    private String chuck;
    private String dad;
    private String insult;
    private String awesome;
    private String planet;
    

    public CombinedDTO(ChuckDTO chuckDTO, DadDTO dadDTO, InsultDTO insultDTO, AwesomeDTO awesomeDTO, PlanetDTO planetDTO) {
        this.chuck = chuckDTO.getValue();
        this.dad = dadDTO.getJoke();
        this.insult = insultDTO.getInsult();
        this.awesome = awesomeDTO.getMessage();
        //this.planet = planetDTO.getName();
       
    }

    public String getChuck() {
        return chuck;
    }

    public String getDad() {
        return dad;
    }

    public String getInsult() {
        return insult;
    }

    public String getAwesome() {
        return awesome;
    }

    public String getPlanet() {
        return planet;
    }

  
    
}
