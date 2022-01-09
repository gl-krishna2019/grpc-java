package grpc.calculator.client;

import com.proto.calculator.Calculator;
import com.proto.calculator.CalculatorRequest;
import com.proto.calculator.CalculatorResponse;
import com.proto.calculator.CalculatorServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {
    public static void main(String[] args) {
        System.out.println("Calculator inputs");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",50050)
                .usePlaintext()
                .build();

        System.out.println("Creating stub");

        //calc service call
        CalculatorServiceGrpc.CalculatorServiceBlockingStub calcClient = CalculatorServiceGrpc.newBlockingStub(channel);

        //protocol buffer message
        Calculator calculator = Calculator.newBuilder()
                .setFirstNumber(15)
                .setSecondNumber(3)
                .build();

        //protocol buffer request
        CalculatorRequest calculatorRequest = CalculatorRequest.newBuilder()
                .setCalc(calculator)
                .build();


        //RPC call and get a result
        CalculatorResponse calculatorResponse = calcClient.calculator(calculatorRequest);

        System.out.println(calculatorResponse.getResult());

        System.out.println("Shutting down channel");

        channel.shutdown();


    }
}
