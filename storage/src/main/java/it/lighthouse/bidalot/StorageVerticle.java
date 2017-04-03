package it.lighthouse.bidalot;

import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpServerRequest;

public class StorageVerticle extends BaseHttpVerticle {
	
	
	private HttpClient client;
	private BaseRecordConfig config;
	
	public StorageVerticle() {
		client = vertx.createHttpClient(new HttpClientOptions());
		config = new BaseRecordConfig("Embedded Data Storage", "localhost", 8543, "/api/data");
	}
	
	
	
	
	@Override
	protected void handleRequest(HttpServerRequest req) {
		System.out.println("Proxying request: " + req.uri());
		HttpClientRequest c_req = client.request(req.method(), 8282, "localhost", req.uri(), c_res -> {
			System.out.println("Proxying response: " + c_res.statusCode());
			req.response().setChunked(true);
			req.response().setStatusCode(c_res.statusCode());
			req.response().headers().setAll(c_res.headers());
			c_res.handler(data -> {
				System.out.println("Proxying response body: " + data.toString("ISO-8859-1"));
				req.response().write(data);
			});
			c_res.endHandler((v) -> req.response().end());
		});
		c_req.setChunked(true);
		c_req.headers().setAll(req.headers());
		req.handler(data -> {
			System.out.println("Proxying request body " + data.toString("ISO-8859-1"));
			c_req.write(data);
		});
		req.endHandler((v) -> c_req.end());
	}




	@Override
	protected BaseRecordConfig getRecordConfig() {
		return config;
	}
}
