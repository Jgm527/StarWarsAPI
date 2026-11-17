import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Printer {

    public Printer() {
    }

    API api = new API();
    GetRequestRepository repository = new GetRequestRepository(api);

    public void printDetailsFilms(JsonArray results) {

        if (results.size() != 0) {

            for (int i = 0; i < results.size(); i++) {

                JsonObject film = results.get(i).getAsJsonObject();
                System.out.println("Title : " + film.get("title"));
                System.out.println("episode number : " + film.get("episode_id"));
                JsonElement crawl = film.get("opening_crawl");
                String crawlfixed = crawl.toString().replace("\\r\\n", " ");
                System.out.println("Opening crawl : " + crawlfixed);
                System.out.println("Director : " + film.get("director"));
                System.out.println("Producer : " + film.get("producer"));
                System.out.println("Release Date : " + film.get("release_date"));

                JsonArray characters = film.getAsJsonArray("characters");
                System.out.println("");
                System.out.println("characters :");
                System.out.println("");
                printSubCall("name", characters);
                System.out.println("");

                JsonArray planets = film.getAsJsonArray("planets");
                System.out.println("");
                System.out.println("planets :");
                System.out.println("");
                printSubCall("name", planets);
                System.out.println("");

                JsonArray starships = film.getAsJsonArray("starships");
                System.out.println("");
                System.out.println("starships :");
                System.out.println("");
                printSubCall("name", starships);
                System.out.println("");

                JsonArray vehicles = film.getAsJsonArray("vehicles");
                System.out.println("");
                System.out.println("vehicles :");
                System.out.println("");
                printSubCall("name", vehicles);
                System.out.println("");

                JsonArray species = film.getAsJsonArray("species");
                System.out.println("");
                System.out.println("species :");
                System.out.println("");
                printSubCall("name", species);
                System.out.println("");

                System.out.println("");
                System.out.println("");
            }
        } else {
            System.out.println("Your search didn't get any results");
        }
    }

    public void printDetailsPlanets(JsonArray planetresults) {

        if (planetresults.size() != 0)
            for (int i = 0; i < planetresults.size(); i++) {
                JsonObject planet = planetresults.get(i).getAsJsonObject();
                System.out.println("Planet : " + planet.get("name"));
                System.out.println("Rotation Period : " + planet.get("rotation_period"));
                System.out.println("Orbital Period : " + planet.get("orbital_period"));
                System.out.println("Diameter : " + planet.get("diameter"));
                System.out.println("Gravity : " + planet.get("gravity"));
                System.out.println("Terrain : " + planet.get("terrain"));
                System.out.println("Surface water : " + planet.get("surface_water"));
                System.out.println("Population : " + planet.get("population"));

                JsonArray residents = planet.getAsJsonArray("residents");
                System.out.println("");
                System.out.println("Residents :");
                System.out.println("");
                printSubCall("name", residents);
                System.out.println("");

                JsonArray films = planet.getAsJsonArray("films");
                System.out.println("");
                System.out.println("Films :");
                System.out.println("");
                printSubCall("title", films);
                System.out.println("");
            }
        else {
            System.out.println("Your search didn't get any results");
        }
    }

    public void printDetailsStarships(JsonArray starshipresults) {
        if (starshipresults.size() != 0)
            for (int i = 0; i < starshipresults.size(); i++) {

                JsonObject starship = starshipresults.get(i).getAsJsonObject();

                System.out.println("Name : " + starship.get("name"));
                System.out.println("Model : " + starship.get("model"));
                System.out.println("Manufacturer : " + starship.get("manufacturer"));
                System.out.println("Cost in credits : " + starship.get("cost_in_credits"));
                System.out.println("Length : " + starship.get("length"));
                System.out.println("Max Atmosphering Speed : " + starship.get("max_atmosphering_speed"));
                System.out.println("Crew : " + starship.get("crew"));
                System.out.println("Passengers : " + starship.get("passengers"));
                System.out.println("Cargo Capacity : " + starship.get("cargo_capacity"));
                System.out.println("Consumables : " + starship.get("consumables"));
                System.out.println("Hyperdrive Rating : " + starship.get("hyperdrive_rating"));
                System.out.println("MGLT : " + starship.get("MGLT"));
                System.out.println("Starship Class : " + starship.get("starship_class"));

                JsonArray pilots = starship.getAsJsonArray("pilots");
                System.out.println("");
                System.out.println("Pilots :");
                System.out.println("");
                printSubCall("name", pilots);
                System.out.println("");

                JsonArray films = starship.getAsJsonArray("films");
                System.out.println("");
                System.out.println("Films :");
                System.out.println("");
                printSubCall("title", films);
                System.out.println("");
            }
        else {
            System.out.println("Your search didn't get any results");
        }
    }

    public void printDetailsPeople(JsonArray peopleResults) {
        if (peopleResults.size() != 0)
            for (int i = 0; i < peopleResults.size(); i++) {

                JsonObject person = peopleResults.get(i).getAsJsonObject();

                System.out.println("Name : " + person.get("name"));
                System.out.println("Height : " + person.get("height"));
                System.out.println("Mass : " + person.get("mass"));
                System.out.println("Hair color : " + person.get("hair_color"));
                System.out.println("Skin color : " + person.get("skin_color"));
                System.out.println("Eye color : " + person.get("eye_color"));
                System.out.println("Birth year : " + person.get("birth_year"));
                System.out.println("Gender : " + person.get("gender"));

                //the homeworld is a single url instead of an array
                JsonArray homeworld = new JsonArray();
                homeworld.add(person.get("homeworld"));
                System.out.println("");
                System.out.println("Homeworld :");
                System.out.println("");
                printSubCall("name", homeworld);
                System.out.println("");

                JsonArray films = person.getAsJsonArray("films");
                System.out.println("");
                System.out.println("Films :");
                System.out.println("");
                printSubCall("title", films);
                System.out.println("");

                JsonArray species = person.getAsJsonArray("species");
                System.out.println("");
                System.out.println("Species :");
                System.out.println("");
                printSubCall("name", species);
                System.out.println("");

                JsonArray vehicles = person.getAsJsonArray("vehicles");
                System.out.println("");
                System.out.println("Vehicles :");
                System.out.println("");
                printSubCall("name", vehicles);
                System.out.println("");

                JsonArray starships = person.getAsJsonArray("starships");
                System.out.println("");
                System.out.println("Starships :");
                System.out.println("");
                printSubCall("name", starships);
                System.out.println("");

                System.out.println("");
                System.out.println("");
            }
        else {
            System.out.println("Your search didn't get any results");
        }
    }

    public void printDetailsVehicles(JsonArray vehicleResults) {
        if (vehicleResults.size() != 0)
            for (int i = 0; i < vehicleResults.size(); i++) {

                JsonObject vehicle = vehicleResults.get(i).getAsJsonObject();

                System.out.println("Name : " + vehicle.get("name"));
                System.out.println("Model : " + vehicle.get("model"));
                System.out.println("Manufacturer : " + vehicle.get("manufacturer"));
                System.out.println("Cost in credits : " + vehicle.get("cost_in_credits"));
                System.out.println("Length : " + vehicle.get("length"));
                System.out.println("Max Atmosphering Speed : " + vehicle.get("max_atmosphering_speed"));
                System.out.println("Crew : " + vehicle.get("crew"));
                System.out.println("Passengers : " + vehicle.get("passengers"));
                System.out.println("Cargo Capacity : " + vehicle.get("cargo_capacity"));
                System.out.println("Consumables : " + vehicle.get("consumables"));
                System.out.println("Vehicle Class : " + vehicle.get("vehicle_class"));

                JsonArray pilots = vehicle.getAsJsonArray("pilots");
                System.out.println("");
                System.out.println("Pilots :");
                System.out.println("");
                printSubCall("name", pilots);
                System.out.println("");

                JsonArray films = vehicle.getAsJsonArray("films");
                System.out.println("");
                System.out.println("Films :");
                System.out.println("");
                printSubCall("title", films);
                System.out.println("");
            }
        else {
            System.out.println("Your search didn't get any results");
        }
    }

    public void printDetailsSpecies(JsonArray speciesResults) {
        if (speciesResults.size() != 0)
            for (int i = 0; i < speciesResults.size(); i++) {

                JsonObject species = speciesResults.get(i).getAsJsonObject();

                System.out.println("Name : " + species.get("name"));
                System.out.println("Classification : " + species.get("classification"));
                System.out.println("Designation : " + species.get("designation"));
                System.out.println("Average Height : " + species.get("average_height"));
                System.out.println("Skin Colors : " + species.get("skin_colors"));
                System.out.println("Hair Colors : " + species.get("hair_colors"));
                System.out.println("Eye Colors : " + species.get("eye_colors"));
                System.out.println("Average Lifespan : " + species.get("average_lifespan"));
                System.out.println("Language : " + species.get("language"));

                //the homeworld is a single url instead of an array, it can also be null for unknown origins
                JsonElement homeworldElement = species.get("homeworld");
                if (homeworldElement != null && !homeworldElement.isJsonNull()) {
                    JsonArray homeworld = new JsonArray();
                    homeworld.add(homeworldElement);
                    System.out.println("");
                    System.out.println("Homeworld :");
                    System.out.println("");
                    printSubCall("name", homeworld);
                } else {
                    System.out.println("");
                    System.out.println("Homeworld : unknown");
                }
                System.out.println("");

                JsonArray people = species.getAsJsonArray("people");
                System.out.println("");
                System.out.println("People :");
                System.out.println("");
                printSubCall("name", people);
                System.out.println("");

                JsonArray films = species.getAsJsonArray("films");
                System.out.println("");
                System.out.println("Films :");
                System.out.println("");
                printSubCall("title", films);
                System.out.println("");
            }
        else {
            System.out.println("Your search didn't get any results");
        }
    }

    //prints the underlying api calls in the array of the original call.
    private void printSubCall(String entity, JsonArray jsonArray) {
        if (jsonArray.size() != 0) {
            for (int j = 0; j < jsonArray.size(); j++) {
                JsonElement character = jsonArray.get(j);
                String uri = character.getAsString();
                JsonObject response = repository.innerRequest(uri);
                System.out.println(response.get(entity));
            }
        } else {
            System.out.println("nothing here");
        }
    }
}
