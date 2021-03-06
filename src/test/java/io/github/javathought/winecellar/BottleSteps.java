package io.github.javathought.winecellar;

import cucumber.api.java8.En;
import io.github.javathought.winecellar.model.Bottle;
import io.restassured.response.ValidatableResponse;
import io.vertx.core.json.Json;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class BottleSteps extends BaseSteps implements En {

    private static final String BOTTLES_PATH = "/bottles";
    private static final String BOTTLES_PATH_ID = "/bottles/{id}";
    private Bottle bottle;
    private ValidatableResponse callReponse;

    public BottleSteps() {
        super();

        Given("^la bouteille de \"([^\"]*)\" de provenance de \"([^\"]*)\" \\(id=(\\d+)\\)$",
                (String nom, String pays, Integer id) -> {
                        bottle = new Bottle();
                        bottle.setId(id.longValue());
                        bottle.setName(nom);
                        bottle.setPays(pays);
                }
        );
        Then("^l'ajout d' une bouteille renvoie le code (\\d+)$", (Integer code) ->
            api.header("Content-Type","application/json")
                    .body(Json.encode(bottle))
                    .when().post(BOTTLES_PATH)
                    .then().statusCode(code)
        );
        When("^on récupère la liste des bouteilles$", () ->
            callReponse = api
                    .when().get(BOTTLES_PATH).then()
        );
        Then("^la liste renvoie le code (\\d+)$", (Integer status) ->
            callReponse.statusCode(status)
        );
        And("^la liste est vide$", () ->
            callReponse.body("", hasSize(0))
        );
        And("^la liste est contient (\\d+) élément$", (Integer nbBottles) ->
            callReponse.body("", hasSize(nbBottles))
        );
        When("^on récupère la bouteille avec l'id (\\d+)$", (Integer id) ->
            callReponse = api
                    .when().pathParam("id", id).get(BOTTLES_PATH_ID).then()
        );
        And("^correspond à la bouteille de \"([^\"]*)\" de provenance de \"([^\"]*)\" \\(id=(\\d+)\\)$",
                (String nom, String pays, Integer id) ->
                    callReponse.body("name", is(nom))
        );
    }

}
