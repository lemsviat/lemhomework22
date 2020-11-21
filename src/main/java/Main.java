import view.MainMenu;
import view.MainMenuHandler;

public class Main {
    public static void main(String[] args) {
        MainMenu.renderMainMenu();
        while (!MainMenuHandler.isExit) {
            MainMenuHandler.chooseAction();
            if (!MainMenuHandler.isExit) MainMenu.renderMainMenu();
        }
    }
}
