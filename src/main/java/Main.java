import io.nats.client.Connection;
import io.nats.client.JetStream;
import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamManagement;
import io.nats.client.JetStreamSubscription;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.PublishOptions;
import io.nats.client.PullSubscribeOptions;
import io.nats.client.api.PublishAck;
import io.nats.client.api.StorageType;
import io.nats.client.api.StreamConfiguration;
import io.nats.client.api.StreamInfo;
import io.nats.client.impl.NatsMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

  public static void main(String[] args)
      throws IOException, InterruptedException, JetStreamApiException, ExecutionException {

    Connection nc = Nats.connect("nats://local:vvNGFu3HRk3PMjZHsztlsCZ9MoUwTGzR@0.0.0.0:37733");

    JetStream js = nc.jetStream();
    byte[] messsageBytes = "go fuck yourself".getBytes(StandardCharsets.UTF_8);
    PublishOptions po = PublishOptions.builder()
        .stream("test_stream")
        .build();

    PublishAck pa = js.publish("test_subject_1", messsageBytes, po);
    nc.close();
  }
}
