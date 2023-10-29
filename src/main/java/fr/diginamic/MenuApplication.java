package fr.diginamic;

import fr.diginamic.menu.Menu;

public class MenuApplication {
    public static void main(String[] args) {
        try {
            Menu.displayOptions();
        } catch (Exception exception) {
            //noinspection CallToPrintStackTrace
            exception.printStackTrace();
        }
    }
}
