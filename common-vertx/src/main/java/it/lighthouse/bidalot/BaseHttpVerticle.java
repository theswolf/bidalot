package it.lighthouse.bidalot;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.types.HttpEndpoint;

public  class BaseHttpVerticle extends AbstractVerticle{
	
	//Vertx vertx = Vertx.vertx();
	Record record;
	
	
	@Override
	  public void start(Future<Void> fut) throws Exception {
	    vertx
        .createHttpServer()
        .requestHandler(r -> {
          r.response().end("<h1>Hello from my first " +
              "Vert.x 3 application</h1>");
        })
        .listen(8080, result -> {
          if (result.succeeded()) {
        	 register();
            fut.complete();
          } else {
            fut.fail(result.cause());
          }
        });
	  }
	
	
	private void register() {
		ServiceDiscovery discovery = ServiceDiscovery.create(vertx);
		record = HttpEndpoint.createRecord("some-rest-api", "localhost", 8080, "/api");
		discovery.publish(record, ar -> {
		  if (ar.succeeded()) {
		    // publication succeeded
		    Record publishedRecord = ar.result();
		  } else {
		    // publication failed
		  }
		});
		
	}
	
	


}
