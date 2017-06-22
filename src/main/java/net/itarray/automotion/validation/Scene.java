package net.itarray.automotion.validation;

public class Scene {

    private final Report report;
    private final String name;

    public Scene(Report report, String name) {
        this.report = report;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Report getReport() {
        return report;
    }

    public NewValidator findElement() {
        return null;
    }

    public NewChunkValidator findElements() {
        return null;
    }
}
