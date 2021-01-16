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
public class PlanetDTO {
    private String name;
    private int rotation_period;
    private int orbital_period;
    private int diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private int surface_water;
    private int population;
    private String[] residents;
    private String[] films;
    private String created;
    private String edited;
    private String url;

    public PlanetDTO(String name, int rotation_period, int orbital_period, int diameter, String climate, String gravity, String terrain, int surface_water, int population, String[] residents, String[] films, String created, String edited, String url) {
        this.name = name;
        this.rotation_period = rotation_period;
        this.orbital_period = orbital_period;
        this.diameter = diameter;
        this.climate = climate;
        this.gravity = gravity;
        this.terrain = terrain;
        this.surface_water = surface_water;
        this.population = population;
        this.residents = residents;
        this.films = films;
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public int getRotation_period() {
        return rotation_period;
    }

    public int getOrbital_period() {
        return orbital_period;
    }

    public int getDiameter() {
        return diameter;
    }

    public String getClimate() {
        return climate;
    }

    public String getGravity() {
        return gravity;
    }

    public String getTerrain() {
        return terrain;
    }

    public int getSurface_water() {
        return surface_water;
    }

    public int getPopulation() {
        return population;
    }

    public String[] getResidents() {
        return residents;
    }

    public String[] getFilms() {
        return films;
    }

    public String getCreated() {
        return created;
    }

    public String getEdited() {
        return edited;
    }

    public String getUrl() {
        return url;
    }
    
    
    
}
