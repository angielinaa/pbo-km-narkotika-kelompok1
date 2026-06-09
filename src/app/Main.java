package app;

import model.*;

public class Main {

    public static void main(String[] args) {

        KnowledgeRepository repository =
                new KnowledgeRepository();

        DataDummy.loadData(repository);

        System.out.println(
                "Jumlah data = " +
                        repository.getTotalData()
        );

        StatistikPutusan statistik =
                new StatistikPutusan(
                        repository.getSemuaData()
                );

        System.out.println(statistik);
    }
}