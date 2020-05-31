package me.thesevenq.facebook.database;

import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import lombok.Getter;
import me.thesevenq.facebook.jedis.JedisPublisher;
import me.thesevenq.facebook.jedis.JedisSubscriber;
import redis.clients.jedis.JedisPool;

@Getter
public class DatabaseManager {

    @Getter
    public static DatabaseManager instance;

    MongoClient client;
    MongoDatabase database;
    MongoCollection profiles;

    private JedisPool jedisPool;
    private JedisPublisher publisher;
    private JedisSubscriber subscriber;

    public DatabaseManager() {
        instance = this;

        connect();
        connectCollections();
        setupRedis();
    }

    private void connect() {
        client = new MongoClient(new ServerAddress("localhost", 27017));
        database = client.getDatabase("facebook");
    }

    private void connectCollections() {
        profiles = database.getCollection("profiles");
    }

    private void setupRedis() {
        this.jedisPool = new JedisPool("127.0.0.1", 6379);
        this.publisher = new JedisPublisher();
        this.subscriber = new JedisSubscriber();
    }
}