package me.thesevenq.facebook.database;

import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import lombok.Getter;
import me.thesevenq.facebook.database.jedis.JedisPublisher;
import me.thesevenq.facebook.database.jedis.JedisSubscriber;
import redis.clients.jedis.JedisPool;

@Getter
public class DatabaseManager {

    @Getter
    public static DatabaseManager instance;

    MongoClient client;
    MongoDatabase database;
    MongoCollection profiles, uhcProfiles;

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
        client = new MongoClient(new ServerAddress("127.0.0.1", 27017));
        database = client.getDatabase("FacebookAorus");
    }

    private void connectCollections() {
        profiles = database.getCollection("profiles");
        uhcProfiles = database.getCollection("uhcProfiles");
    }

    private void setupRedis() {
        this.jedisPool = new JedisPool("127.0.0.1", 6379);
        this.publisher = new JedisPublisher();
        this.subscriber = new JedisSubscriber();
    }
}