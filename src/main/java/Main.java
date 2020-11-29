import view.MainMenu;
import view.MainView;

public class Main {
    public static void main(String[] args) {
        MainMenu.renderMainMenu();
        while (!MainView.isExit) {
            MainView.chooseAction();
            if (!MainView.isExit) MainMenu.renderMainMenu();
        }
    }
}
