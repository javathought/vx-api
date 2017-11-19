package io.github.javathought.winecellar;

import io.github.javathought.winecellar.handlers.AddBottleHandler;
import io.github.javathought.winecellar.handlers.GetBottleByBottleIdHandler;
import io.github.javathought.winecellar.handlers.GetBottlesHandler;
import io.github.javathought.winecellar.handlers.GetCellarInformationHandler;
import io.github.javathought.winecellar.security.handlers.RolesSecurityHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.ext.web.Router;
import io.vertx.core.Future;
import org.jboss.weld.vertx.web.WeldWebVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle {
  private static final Logger LOG = LoggerFactory.getLogger(MainVerticle.class);
  static final int PORT = 9090;

  private HttpServer server;

  @Override
  public void start(Future future) {
    final WeldWebVerticle weldVerticle = new WeldWebVerticle();
    vertx.deployVerticle(weldVerticle, result -> {
      if (result.succeeded()) {
        OpenAPI3RouterFactory.createRouterFactoryFromFile(this.vertx, getClass().getResource("/swagger.json").getFile(), openAPI3RouterFactoryAsyncResult -> {
          if (openAPI3RouterFactoryAsyncResult.succeeded()) {
            OpenAPI3RouterFactory routerFactory = openAPI3RouterFactoryAsyncResult.result();

            // Enable automatic response when ValidationException is thrown
            routerFactory.enableValidationFailureHandler(true);

            // Add routes handlers
            routerFactory.addHandlerByOperationId("getBottles", new GetBottlesHandler(vertx));
            routerFactory.addHandlerByOperationId("addBottle", new AddBottleHandler(vertx));
            routerFactory.addHandlerByOperationId("getBottleByBottleId", new GetBottleByBottleIdHandler(vertx));
            routerFactory.addHandlerByOperationId("getCellarInformation", new GetCellarInformationHandler());

            // Add security handlers
            routerFactory.addSecurityHandler("Roles", new RolesSecurityHandler());

            // Generate the router
            Router router = routerFactory.getRouter();
            server = vertx.createHttpServer(new HttpServerOptions().setPort(PORT).setHost("localhost"));
            server.requestHandler(router::accept).listen();
            future.complete();
          } else {
            // Something went wrong during router factory initialization
            Throwable exception = openAPI3RouterFactoryAsyncResult.cause();
            LOG.error("RouterFactory Failed ! ", exception);
            vertx.close();
          }
        });
      }
    });
  }

  @Override
  public void stop(){
    this.server.close();
  }

}
