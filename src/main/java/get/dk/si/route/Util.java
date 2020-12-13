package get.dk.si.route;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

public class Util implements Serializable {

    protected Logger logger = LoggerFactory.getLogger(Util.class.getName());
    private ObjectMapper objectMapper = new ObjectMapper();

    public String rootToJson(Root root) throws JsonProcessingException {
        logger.info("Mapping the root object to json");
        return objectMapper.writeValueAsString(root);
    }

    public Root rootFromJson(String jsonString) throws JsonProcessingException {
        logger.info("Mapping the jsonString to object");
        Root root = objectMapper.readValue(jsonString, Root.class);
        return root;
    }

    public void sendToRoute(Route route, String root) throws IOException, TimeoutException {
        logger.info("Starting connection to rabbitQue");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("188.166.16.16");
        factory.setUsername("mmmrj1");
        factory.setPassword("mmmrj1");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(route.getQueue(), false, false, false, null);
            channel.basicPublish("", route.getQueue(), null, root.getBytes("UTF-8"));
        }
        logger.info("Sending to topic: "+ route.getQueue());
    }
}
