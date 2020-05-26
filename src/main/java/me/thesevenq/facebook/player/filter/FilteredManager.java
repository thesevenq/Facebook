package me.thesevenq.facebook.player.filter;

import lombok.Getter;
import lombok.Setter;
import me.thesevenq.facebook.utils.CC;
import me.thesevenq.facebook.utils.MessageUtils;

@Getter @Setter
public class FilteredManager {

    @Getter public static FilteredManager instance;

    public FilteredManager() {
        instance = this;
    }

    public static String FORMAT = CC.GRAY + "[" + CC.BD_RED + MessageUtils.WARNING + CC.GRAY + "] ";

    @Getter
    public String[] blocked = {
            "kurac", "picka", "dildo",
            "debil", "jebem", "jebo",
            "rekt", "noob", "nigger",
            "niger", "dick", "pussy",
            "vape.gg", "bitch", "kurva",
            "peder", "kreten", "idiot",
            "demon.gg", "drek.io", "pornhub",
            "brazzers", "xnxx", "xvideos",
            "redtube", "picke", "supak", "cetnik", "ustasa", "siptar",
            "kys", "killyourself"
    };

}
