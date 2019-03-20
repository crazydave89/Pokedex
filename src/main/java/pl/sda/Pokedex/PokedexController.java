package pl.sda.Pokedex;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class PokedexController {
    public Pokedex readPokedex(){
        ObjectMapper objectMapper= new ObjectMapper();
        Pokedex pokedex=null;
        try {
             pokedex = objectMapper.readValue(new File("pokedex.json"), Pokedex.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pokedex;
    }
}
