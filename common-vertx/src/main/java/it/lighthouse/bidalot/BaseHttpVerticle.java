package it.lighthouse.bidalot;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.types.HttpEndpoint;

public abstract class BaseHttpVerticle extends AbstractVerticle{
	
	//Vertx vertx = Vertx.vertx();
	Record record;
	protected abstract void handleRequest(HttpServerRequest request);
	protected abstract BaseRecordConfig getRecordConfig();
	
	@Override
	  public void start(Future<Void> fut) throws Exception {
		vertx
        .createHttpServer()
        /*.requestHandler(r -> {
          r.response().end("<h1>Hello from my first " +
              "Vert.x 3 application</h1>");
        })*/
        .requestHandler(this::handleRequest)
        .listen(getRecordConfig().getPort(), result -> {
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
		record = HttpEndpoint.createRecord(getRecordConfig().getName(), getRecordConfig().getHost(),getRecordConfig().getPort(), getRecordConfig().getRoot());
		discovery.publish(record, ar -> {
			discovery.close();
		  if (ar.succeeded()) {
		    // publication succeeded
		    Record publishedRecord = ar.result();
		    
		  } else {
		    // publication failed
		  }
		});
		
	}
	
	


}
