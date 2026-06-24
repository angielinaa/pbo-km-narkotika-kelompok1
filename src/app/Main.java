package app;

import controller.KnowledgeController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.KnowledgeRepository;
import view.GUIView;

public class Main {
    public static void main(String[] args) {
        // Meluncurkan JavaFX melalui class AppFX internal
        Application.launch(AppFX.class, args);
    }

    public static class AppFX extends Application {
        public AppFX() {} // Constructor public wajib untuk JavaFX

        @Override
        public void start(Stage primaryStage) {
            KnowledgeRepository repository = new KnowledgeRepository();
            GUIView view = new GUIView(primaryStage);
            KnowledgeController controller = new KnowledgeController(repository, view);
            controller.start();
        }
    }
}