package me.thesevenq.facebook.server.database.jedis;

import me.thesevenq.facebook.server.database.DatabaseManager;
import redis.clients.jedis.Jedis;

public class JedisPublisher {
    public static String GLOBAL = "global";

    // publishes a message to a specified channel
    public void write(final String channel, final String message) {
        Jedis jedis = null;
        try {
            jedis = DatabaseManager.getInstance().getJedisPool().getResource();
            //jedis.auth("EL3DZp3EwMzx4gywmwbEt4DedKwKa5Au");
            jedis.publish(channel.toLowerCase(), message);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
