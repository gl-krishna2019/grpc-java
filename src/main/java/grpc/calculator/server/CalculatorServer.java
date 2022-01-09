package grpc.calculator.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class CalculatorServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Calculator server");

        Server server = ServerBuilder.forPort(50050)
                .addService(new CalculatorServiceImpl())
                .build();

        server.start();

        Runtime.getRuntime().addShutdownHook( new Thread( () -> {
            System.out.println("Received Shutdown request");
            server.shutdown();
            System.out.println("Server successfully stopped");
        }));

        server.awaitTermination();
    }
}
