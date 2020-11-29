import view.MainView;

public class Main {
    public static void main(String[] args) {
        MainView.renderMainMenu();
        while (!MainView.isExit) {
            MainView.chooseAction();
            if (!MainView.isExit) MainView.renderMainMenu();
        }
    }
}
