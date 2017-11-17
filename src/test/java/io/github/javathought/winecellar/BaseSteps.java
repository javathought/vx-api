package io.github.javathought.winecellar;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;

public class BaseSteps {
    RequestSpecification api;

    public BaseSteps() {
        this.api = given().port(9090).config(
                // TODO : remove when issue is closed
                // temporary : due to issue https://github.com/vert-x3/vertx-web/issues/724 
                newConfig().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))
        );
    }

}
