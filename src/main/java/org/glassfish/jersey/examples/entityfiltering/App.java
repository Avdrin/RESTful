package org.glassfish.jersey.examples.entityfiltering;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import org.glassfish.grizzly.http.server.HttpServer;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class App {

    private static final URI BASE_URI = URI.create("http://localhost:8080/");

    public static void main(String[] args) {
        try {
            final HttpServer server = GrizzlyHttpServerFactory
                    .createHttpServer(BASE_URI, new EntityFilteringApplication(), false);
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    server.shutdownNow();
                }
            }));
            server.start();

            for (final String path : new String[]{"priceList?productName=per12&date=2017-04-21",
                    "priceList?productName=per1&date=2017-05-12",
                    "priceList?productName=per1&date=2017-04-21",
                    "priceList/listPrices?productName=per4",
                    }) {
                System.out.println(BASE_URI + path);
            }
            Thread.currentThread().join();

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(App.class.getName())
                    .log(Level.SEVERE, "I/O error occurred during reading from an system input stream.", ex);
        }
    }
}
