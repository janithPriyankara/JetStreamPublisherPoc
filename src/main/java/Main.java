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
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

  public static void main(String[] args)
      throws IOException, InterruptedException, JetStreamApiException, ExecutionException {

    String userName = "local";
    String password = "vvNGFu3HRk3PMjZHsztlsCZ9MoUwTGzR";
    String host = "0.0.0.0";
    String port = "37733";
    String streamName = "test_stream_1";
    String subjectName = "nextgen_2";


    Connection nc = Nats.connect("nats://"+userName+":"+password+ "@"+host+":"+port);
    JetStream js = nc.jetStream();

    PublishOptions po = PublishOptions.builder()
        .stream(streamName)
        .build();

    for (int i = 1; i <2;i++){

      LocalTime currentTime = LocalTime.now();
      String timeStamp = currentTime.toString();


      String message = ( "{\"GATEWAY_ID\" : \"gateway_"+i+"\", \"eventType\" : \"ADD_GATEWAY\", \"GATEWAY_HOST\":\"localhost\", \"GATEWAY_PORT\" : \""+5000+"\"}");
      byte[] messsageBytes = message.getBytes(StandardCharsets.UTF_8);
      PublishAck pa = js.publish(subjectName, messsageBytes, po);
      System.out.println(message);
      System.out.println(pa);
    }


    nc.close();
  }
}
