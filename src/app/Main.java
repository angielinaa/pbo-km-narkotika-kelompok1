package app;

import model.KnowledgeRepository;
import view.ConsoleView;
import controller.KnowledgeController;

public class Main {

    public static void main(String[] args) {
        KnowledgeRepository repository = new KnowledgeRepository();
        ConsoleView view = new ConsoleView();
        KnowledgeController controller = new KnowledgeController(repository, view);
        controller.start();
    }
}