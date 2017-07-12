package com.amarpreetsinghprojects.pokemonapi;

import java.util.ArrayList;

/**
 * Created by kulvi on 07/05/17.
 */

public class PokeList {
    String name,weight,height;
    Sprites sprites;
    ArrayList<Types> types;



    public PokeList(String name, String weight, String height, Sprites sprites, ArrayList<Types> types) {

        this.name = name;
        this.weight = weight;
        this.height = height;
        this.sprites = sprites;
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }
    public ArrayList<Types> getTypes() {
        return types;
    }
}
