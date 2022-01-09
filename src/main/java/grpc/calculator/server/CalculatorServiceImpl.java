package grpc.calculator.server;

import com.proto.calculator.Calculator;
import com.proto.calculator.CalculatorRequest;
import com.proto.calculator.CalculatorResponse;
import com.proto.calculator.CalculatorServiceGrpc;
import io.grpc.stub.StreamObserver;

public class CalculatorServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {
    @Override
    public void calculator(CalculatorRequest request, StreamObserver<CalculatorResponse> responseObserver) {

        //extract required info
        Calculator calc = request.getCalc();
        int num1 = calc.getFirstNumber();
        int num2 = calc.getSecondNumber();

        //create the response
        int result = num1+num2;
        CalculatorResponse response = CalculatorResponse.newBuilder()
                .setResult(result)
                .build();

        //send the response
        responseObserver.onNext(response);

        //RPC call
        responseObserver.onCompleted();
    }
}
