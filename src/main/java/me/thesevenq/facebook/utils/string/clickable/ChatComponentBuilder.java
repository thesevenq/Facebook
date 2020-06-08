package me.thesevenq.facebook.utils.string.clickable;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.HoverEvent.Action;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class ChatComponentBuilder extends ComponentBuilder {
   private static Field partsField;
   private static Field currField;

   public ChatComponentBuilder(String text) {
      super("");
      this.parse(text);
   }

   public TextComponent getCurrent() {
      try {
         return (TextComponent)currField.get(this);
      } catch (IllegalAccessException var2) {
         var2.printStackTrace();
         return null;
      }
   }

   public void setCurrent(TextComponent tc) {
      try {
         currField.set(this, tc);
      } catch (IllegalAccessException var3) {
         var3.printStackTrace();
      }

   }

   public List getParts() {
      try {
         return (List)partsField.get(this);
      } catch (IllegalAccessException var2) {
         var2.printStackTrace();
         return null;
      }
   }

   public ChatComponentBuilder setCurrentHoverEvent(HoverEvent hoverEvent) {
      this.getCurrent().setHoverEvent(hoverEvent);
      return this;
   }

   public ChatComponentBuilder setCurrentClickEvent(ClickEvent clickEvent) {
      this.getCurrent().setClickEvent(clickEvent);
      return this;
   }
// non viene utilizzato come metodo
//   public ChatComponentBuilder attachToEachPart(HoverEvent hoverEvent) {
//      this.getParts().forEach((part) -> {
//         if ((HoverEvent) part.getHoverEvent() == null) {
//            (HoverEvent) part.setHoverEvent(hoverEvent);
//         }
//
//      });
//      this.getCurrent().setHoverEvent(hoverEvent);
//      return this;
//   }
/*ezez
   public ChatComponentBuilder attachToEachPart(ClickEvent clickEvent) {
      this.getParts().forEach((part) -> {
         if (part.getClickEvent() == null) {
            part.setClickEvent(clickEvent);
         }

      });
      this.getCurrent().setClickEvent(clickEvent);
      return this;
   }
*/
   public ChatComponentBuilder parse(String text) {
      String regex = "[&§]{1}([a-fA-Fl-oL-O0-9-r]){1}";
      text = text.replaceAll(regex, "§$1");
      if (!Pattern.compile(regex).matcher(text).find()) {
         this.append(text);
         return this;
      } else {
         String[] words = text.split(regex);
         int index = words[0].length();
         String[] var5 = words;
         int var6 = words.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            String word = var5[var7];

            try {
               if (index != words[0].length()) {
                  this.append(word);
                  ChatColor color = ChatColor.getByChar(text.charAt(index - 1));
                  if (color == ChatColor.BOLD) {
                     this.bold(true);
                  } else if (color == ChatColor.STRIKETHROUGH) {
                     this.strikethrough(true);
                  } else if (color == ChatColor.MAGIC) {
                     this.obfuscated(true);
                  } else if (color == ChatColor.UNDERLINE) {
                     this.underlined(true);
                  } else if (color == ChatColor.RESET) {
                     this.bold(false);
                     this.strikethrough(false);
                     this.obfuscated(false);
                     this.underlined(false);
                  } else {
                     this.color(color);
                  }
               }
            } catch (Exception var10) {
               var10.printStackTrace();
            }

            index += word.length() + 2;
         }

         return this;
      }
   }

   public ChatComponentBuilder append(String text, BaseComponent[] hover) {
      this.append(text);
      if (hover != null) {
         this.getCurrent().setHoverEvent(new HoverEvent(Action.SHOW_TEXT, hover));
      }

      return this;
   }

   public ChatComponentBuilder append(TextComponent textComponent) {
      if (textComponent == null) {
         return this;
      } else {
         String text = textComponent.getText();
         ChatColor color = textComponent.getColor();
         boolean underline = textComponent.isUnderlined();
         boolean italic = textComponent.isUnderlined();
         boolean strike = textComponent.isStrikethrough();
         HoverEvent he = textComponent.getHoverEvent();
         ClickEvent ce = textComponent.getClickEvent();
         this.append(text);
         this.color(color);
         this.underlined(underline);
         this.italic(italic);
         this.strikethrough(strike);
         this.event(he);
         this.event(ce);
         if (textComponent.getExtra() != null) {
            Iterator var9 = textComponent.getExtra().iterator();

            while(var9.hasNext()) {
               BaseComponent bc = (BaseComponent)var9.next();
               if (bc instanceof TextComponent) {
                  this.append((TextComponent)bc);
               }
            }
         }

         return this;
      }
   }

   static {
      try {
         currField = ComponentBuilder.class.getDeclaredField("current");
         partsField = ComponentBuilder.class.getDeclaredField("parts");
         currField.setAccessible(true);
         partsField.setAccessible(true);
      } catch (NoSuchFieldException var1) {
         var1.printStackTrace();
      }

   }
}
