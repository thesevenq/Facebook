package me.thesevenq.facebook.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import me.thesevenq.facebook.Facebook;

import java.util.concurrent.ThreadFactory;

public class Tasks {
   public static ThreadFactory newThreadFactory(String name) {
      return (new ThreadFactoryBuilder()).setNameFormat(name).build();
   }

   public static void run(Tasks.Callable callable) {
      Facebook.getInstance().getServer().getScheduler().runTask(Facebook.getInstance(), callable::call);
   }

   public static void runAsync(Tasks.Callable callable) {
      Facebook.getInstance().getServer().getScheduler().runTaskAsynchronously(Facebook.getInstance(), callable::call);
   }

   public static void runLater(Tasks.Callable callable, long delay) {
      Facebook.getInstance().getServer().getScheduler().runTaskLater(Facebook.getInstance(), callable::call, delay);
   }

   public static void runAsyncLater(Tasks.Callable callable, long delay) {
      Facebook.getInstance().getServer().getScheduler().runTaskLaterAsynchronously(Facebook.getInstance(), callable::call, delay);
   }

   public static void runTimer(Tasks.Callable callable, long delay, long interval) {
      Facebook.getInstance().getServer().getScheduler().runTaskTimer(Facebook.getInstance(), callable::call, delay, interval);
   }

   public static void runAsyncTimer(Tasks.Callable callable, long delay, long interval) {
      Facebook.getInstance().getServer().getScheduler().runTaskTimerAsynchronously(Facebook.getInstance(), callable::call, delay, interval);
   }

   public interface Callable {
      void call();
   }
}
