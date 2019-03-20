package pl.sda;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.sda.Pokedex.PokedexController;
import pl.sda.Pokedex.Pokemon;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RootController implements Initializable {

    @FXML
    private Label witaj;
    @FXML
    private Button szukaj;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField wprowadz;
    @FXML
    private Label nextEvo;
    @FXML
    private Label prevEvo;
    @FXML
    private Label type;
    @FXML
    private Button nextEvoPic;
    @FXML
    private Label weakness;
    @FXML
    private Button goToPrevEvo;
    @FXML
    private Spinner<String> spinner;
    @FXML
    private Button findBySpinner;

    List<Pokemon> pokemonList = new PokedexController().readPokedex().getPokemon();

    public ObservableList<String> makeObservableList(){
        ObservableList<String> observableList= FXCollections.observableArrayList();
        pokemonList.sort(Comparator.comparing(pokemon -> pokemon.getName()));
        for (Pokemon pokemon : pokemonList) {
            observableList.add(pokemon.getName());
        }
        return observableList;
    }

    public void pressSpinnerButton(){
        for (Pokemon pokemon : pokemonList) {
            if (pokemon.getName().equals(spinner.getValue())){
                wprowadz.setText(String.valueOf(pokemon.getId()));
                getPokemon();
            }
        }

    }

    public List<Pokemon> getPokemonList(){
        List<Pokemon> pokemon = pokemonList.stream().filter(pokemon1 -> pokemon1.getId() == Integer.parseInt(wprowadz.getText())).collect(Collectors.toList());
        return pokemon;
    }

    public void getPokemon() {
        List<Pokemon> pokemonList = getPokemonList();
        String imageUrl = new StringBuilder(pokemonList.get(0).getImg()).substring(4);
        imageView.setImage(new Image("https"+imageUrl));
        witaj.setText(pokemonList.get(0).getName());
        if (pokemonList.get(0).getNext_evolution()!=null) {
            nextEvo.setText("Next evolution: " + pokemonList.get(0).getNext_evolution().get(0).getName());
            nextEvoPic.setText("przejdz");
            nextEvoPic.visibleProperty().setValue(true);
        }
        else {
            nextEvo.setText("Next evolution: It's FINAL FORM!");
            nextEvoPic.visibleProperty().setValue(false);
        }
        if (pokemonList.get(0).getPrev_evolution()!=null) {
            prevEvo.setText("Previous evolution: " + pokemonList.get(0).getPrev_evolution().get(0).getName());
            goToPrevEvo.visibleProperty().setValue(true);
        }
        else {
            prevEvo.setText("Previous evolution: It's first form ^^");
            goToPrevEvo.visibleProperty().setValue(false);
        }

        type.setText("type: "+pokemonList.get(0).getType().toString().replace("[","").replace("]",""));

        weakness.setText("weakness: "+pokemonList.get(0).getWeaknesses().get(0));
    }



    public void getNextEvoButton(){
        List<Pokemon> pokemon = pokemonList.stream().filter(pokemon1 -> pokemon1.getId() == Integer.parseInt(wprowadz.getText())).collect(Collectors.toList());
        List<Pokemon> nextEvoPokemon = pokemonList.stream().filter(x -> x.getName().equals(pokemon.get(0).getNext_evolution().get(0).getName())).collect(Collectors.toList());
        wprowadz.setText(String.valueOf(nextEvoPokemon.get(0).getId()));
        getPokemon();
    }

    public void getPrevEvoButton(){
        int index= Integer.parseInt(wprowadz.getText())-1;
        wprowadz.setText(String.valueOf(index));
        getPokemon();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        spinner.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<>(makeObservableList()));
}
}
