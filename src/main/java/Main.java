import controller.MainMenuHandler;
import view.MainMenu;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        MainMenu.renderMainMenu();
        while (!MainMenuHandler.isExit) {
            MainMenuHandler.chooseAction();
            if (!MainMenuHandler.isExit) MainMenu.renderMainMenu();
        }
    }
}
