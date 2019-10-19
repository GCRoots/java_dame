package rpc.grpc.examples.helloworld.my;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import rpc.grpc.examples.helloworld.GreeterGrpc;
import rpc.grpc.examples.helloworld.HelloReply;
import rpc.grpc.examples.helloworld.HelloRequest;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author shipengfei
 * @data 19-10-6
 */
public class HelloClient {
    private static final Logger logger = Logger.getLogger(HelloClient.class.getName());

    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    /** Construct client connecting to Hello server at {@code host:port}. */
    public HelloClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                .build());
    }

    /** Construct client for accessing Hello server using the existing channel. */
    HelloClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /** Say hello to server. */
    public void greet(String name) {
        logger.info("User:"+ name +" waiting SERVER response ...");
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response;
        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.info("Greeting: " + response.getMessage());
    }

    public static void main(String[] args) throws InterruptedException {
        HelloClient helloClient=new HelloClient("localhost",55555);
        try {
            /* Access a service running on the local machine on port 50051 */
            String user = "WORLD";
            if (args.length > 0) {
                user = args[0]; /* Use the arg as the name to greet if provided */
            }
            helloClient.greet(user);
        } finally {
            helloClient.shutdown();
        }
    }

}
