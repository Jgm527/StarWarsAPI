import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Locale;

public class ArgumentSwitcher {

    private static final API apiCalls = new API();
    private GetRequestRepository repository = new GetRequestRepository(apiCalls);
    Printer printer = new Printer();

    public void switcher(String command, String searchquery) {

        JsonObject jsonObject;

        //accept categories in any case, eg. "Films"
        switch (command.toLowerCase(Locale.ROOT)) {
            case "films":
                jsonObject = repository.getAll("films", searchquery);
                if (jsonObject == null) {
                    System.out.println("Could not get the films, check your internet connection.");
                    return;
                }
                JsonArray filmresults = jsonObject.getAsJsonArray("results");
                printer.printDetailsFilms(filmresults);
                break;
            case "planets":
                jsonObject = repository.getAll("planets", searchquery);
                if (jsonObject == null) {
                    System.out.println("Could not get the planets, check your internet connection.");
                    return;
                }
                JsonArray planetresults = jsonObject.getAsJsonArray("results");
                printer.printDetailsPlanets(planetresults);
                break;
            case "starships":
                jsonObject = repository.getAll("starships", searchquery);
                if (jsonObject == null) {
                    System.out.println("Could not get the starships, check your internet connection.");
                    return;
                }
                JsonArray starshipresults = jsonObject.getAsJsonArray("results");
                printer.printDetailsStarships(starshipresults);
                break;
            case "people":
                jsonObject = repository.getAll("people", searchquery);
                if (jsonObject == null) {
                    System.out.println("Could not get the people, check your internet connection.");
                    return;
                }
                JsonArray peopleresults = jsonObject.getAsJsonArray("results");
                printer.printDetailsPeople(peopleresults);
                break;
            case "vehicles":
                jsonObject = repository.getAll("vehicles", searchquery);
                if (jsonObject == null) {
                    System.out.println("Could not get the vehicles, check your internet connection.");
                    return;
                }
                JsonArray vehicleresults = jsonObject.getAsJsonArray("results");
                printer.printDetailsVehicles(vehicleresults);
                break;
            case "species":
                jsonObject = repository.getAll("species", searchquery);
                if (jsonObject == null) {
                    System.out.println("Could not get the species, check your internet connection.");
                    return;
                }
                JsonArray speciesresults = jsonObject.getAsJsonArray("results");
                printer.printDetailsSpecies(speciesresults);
                break;
            default:
                System.out.println(command + " is not an available command, available commands: films, planets, starships, people, vehicles, species");
                break;
        }
    }
}
