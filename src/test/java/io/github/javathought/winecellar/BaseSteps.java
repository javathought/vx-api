package io.github.javathought.winecellar;

import io.restassured.specification.RequestSpecification;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.junit.After;
import org.junit.Before;

import java.util.concurrent.CountDownLatch;

import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.RestAssuredConfig.newConfig;

class BaseSteps {
    RequestSpecification api;

    BaseSteps() {
        this.api = given().port(9090).config(
                // TODO : remove when issue is closed
                // temporary : due to issue https://github.com/vert-x3/vertx-web/issues/724 
                newConfig().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))
        );
    }

}
