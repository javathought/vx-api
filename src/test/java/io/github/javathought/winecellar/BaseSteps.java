package io.github.javathought.winecellar;

import io.restassured.specification.RequestSpecification;

import static io.github.javathought.winecellar.MainVerticle.PORT;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;

class BaseSteps {
    protected RequestSpecification api;

    BaseSteps() {
        this.api = given().port(PORT).config(
                // TODO : remove when issue is closed
                // temporary : due to issue https://github.com/vert-x3/vertx-web/issues/724 
                newConfig().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))
        );
    }

}
