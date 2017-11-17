package io.github.javathought.winecellar;

import cucumber.api.java8.En;

public class BottleSteps extends BaseSteps implements En {

    public BottleSteps() {
        super();
        
        Given("^la bouteille de \"([^\"]*)\" de provenance de \"([^\"]*)\" \\(id=(\\d+)\\)$",
                (String nom, String pays, Integer id) ->         
                        api.header("Content-Type","application/json")
                        .body(
                                String.format("{ \"id\": %d, \"name\": \"%s\", \"pays\": \"%s\"}",
                                        id, nom, pays))
                        .when().post("/bottles")
                        .then().statusCode(201)
        );
    }

}
