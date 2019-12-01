package rpc.grpc.helloworld.my;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import rpc.grpc.helloworld.GreeterGrpc;
import rpc.grpc.helloworld.HelloReply;
import rpc.grpc.helloworld.HelloRequest;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author shipengfei
 * @data 19-10-6
 */
public class HelloServer {

    private static final Logger logger = Logger.getLogger(HelloServer.class.getName());

    private Server server;

    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 55555;
        server = ServerBuilder.forPort(port)
                .addService(new HelloServer.GreeterImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                HelloServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    static class GreeterImpl extends GreeterGrpc.GreeterImplBase {

        @Override
        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            logger.info("User:"+req.getName()+" request messages");
            HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        final HelloServer server=new HelloServer();
        server.start();
        server.blockUntilShutdown();

    }


}
