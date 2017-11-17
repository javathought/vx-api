package io.github.javathought.winecellar;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.fr.Etantdonné;
import cucumber.api.java.fr.Quand;
import cucumber.api.java8.En;
import io.restassured.specification.RequestSpecification;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;

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
