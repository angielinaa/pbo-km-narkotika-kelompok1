package app;

import controller.KnowledgeController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        DatasetManager model = new DatasetManager();
        MainView view = new MainView();
        new KnowledgeController(model, view);

        Scene scene = new Scene(view, 1100, 700);

        primaryStage.setTitle("Knowledge Management System (KMS) Putusan Pengadilan Narkotika v1.0");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}