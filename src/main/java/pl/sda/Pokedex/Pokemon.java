package pl.sda.Pokedex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pokemon {
    private int id;
    private String num;
    private String name;
    private String img;
    private List<String> type;
    private String height;
    private String weight;
    private String candy;
    private int candy_count;
    private String egg;
    private double spawn_chance;
    private int avg_spawns;
    private String spawn_time;
    private List<Double> multipliers;
    private List<String> weaknesses;
    private List<Next_Evolution> next_evolution;
    private List<Prev_Evolution> prev_evolution;
}
