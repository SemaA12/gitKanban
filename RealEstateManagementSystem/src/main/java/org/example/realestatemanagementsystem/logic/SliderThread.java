package org.example.realestatemanagementsystem.logic;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SliderThread extends Thread {
    private int i = 0;
    private final ImageView imageSlider;
    private final List<String> imagePaths;
    private volatile boolean running = true;

    public SliderThread(ImageView imageSlider, File imageDirectory) {
        this.imageSlider = imageSlider;
        this.imagePaths = loadImages(imageDirectory);
    }

    private List<String> loadImages(File directory) {
        List<String> paths = new ArrayList<>();
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png"));
        if (files != null) {
            for (File file : files) {
                paths.add(file.toURI().toString());
            }
        }
        return paths;
    }

    @Override
    public void run() {
        try {
            while (running) {
                Platform.runLater(() -> {
                    if (i >= imagePaths.size()) {
                        i = 0;
                    }
                    imageSlider.setImage(new Image(imagePaths.get(i), 158, 118, false, true));
                    i++;
                });
                Thread.sleep(4000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void stopSlider() {
        running = false;
        this.interrupt();
    }
}
