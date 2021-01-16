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
public class ChuckDTO {

    private String[] categories;
  private String created_at;
  private String icon_url;
  private String id;
  private String updated_at;
  private String url;
  private String value;

    public ChuckDTO(String[] categories, String created_at, String icon_url, String id, String updated_at, String url, String value) {
        this.categories = categories;
        this.created_at = created_at;
        this.icon_url = icon_url;
        this.id = id;
        this.updated_at = updated_at;
        this.url = url;
        this.value = value;
    }

    public String getValue() {
        return value;
    }



    
    
    

}
