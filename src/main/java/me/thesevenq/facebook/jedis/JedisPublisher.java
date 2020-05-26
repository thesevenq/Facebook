package me.thesevenq.facebook.jedis;

import me.thesevenq.facebook.database.DatabaseManager;
import redis.clients.jedis.Jedis;

public class JedisPublisher {
    public static String GLOBAL = "global";

    // publishes a message to a specified channel
    public void write(final String channel, final String message) {
        Jedis jedis = null;
        try {
            jedis = DatabaseManager.getInstance().getJedisPool().getResource();

            jedis.publish(channel.toLowerCase(), message);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
