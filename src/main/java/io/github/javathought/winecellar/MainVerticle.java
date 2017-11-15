package io.github.javathought.winecellar;

import io.github.javathought.winecellar.handlers.AddBottleHandler;
import io.github.javathought.winecellar.handlers.GetBottleByBottleIdHandler;
import io.github.javathought.winecellar.handlers.GetBottlesHandler;
import io.github.javathought.winecellar.handlers.GetCellarInformationHandler;
import io.github.javathought.winecellar.securityHandlers.CertSecurityHandler;
import io.github.javathought.winecellar.securityHandlers.RolesSecurityHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.api.contract.openapi3.OpenAPI3RouterFactory;
import io.vertx.ext.web.Router;
import io.vertx.core.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle {
  private static final Logger LOG = LoggerFactory.getLogger(MainVerticle.class);
  public static final int PORT = 9090;

  HttpServer server;

  @Override
  public void start(Future future) {
    OpenAPI3RouterFactory.createRouterFactoryFromFile(this.vertx, getClass().getResource("/swagger.json").getFile(), openAPI3RouterFactoryAsyncResult -> {
      if (openAPI3RouterFactoryAsyncResult.succeeded()) {
        OpenAPI3RouterFactory routerFactory = openAPI3RouterFactoryAsyncResult.result();

        // Enable automatic response when ValidationException is thrown
        routerFactory.enableValidationFailureHandler(true);

        // Add routes handlers
        routerFactory.addHandlerByOperationId("getBottles", new GetBottlesHandler());
        routerFactory.addHandlerByOperationId("addBottle", new AddBottleHandler());
        routerFactory.addHandlerByOperationId("getBottleByBottleId", new GetBottleByBottleIdHandler());
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

  @Override
  public void stop(){
    this.server.close();
  }

}
