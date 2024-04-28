package com.example.recipe;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

import java.io.IOException;

public class WelcomeView {
    private RecipeController controller;

    public WelcomeView(RecipeController controller) {
        this.controller = controller;
    }

    public Form createForm() {
        Form hi = new Form("Recipe Master", BoxLayout.y());
        SpanLabel welcomeLabel = new SpanLabel("Welcome to Recipe Master, " +
                "your go-to app for delicious culinary adventures! Whether you're a seasoned chef or a cooking novice," +
                " Recipe Master is here to inspire your kitchen creativity.");
        welcomeLabel.setTextUIID("MultiLineLabel");
        welcomeLabel.setUIID("WelcomeLabel");
        welcomeLabel.getAllStyles().setAlignment(Component.CENTER);
        welcomeLabel.setWidth(Display.getInstance().getDisplayWidth() - 100);
        welcomeLabel.setHeight(300);

        try {
            Resources res = Resources.openLayered("/NativeTheme");
            String imageName = "logopng.png";
            Image logo = res.getImage(imageName);
            logo.scaled(1500,1500);
            ImageViewer imageView = new ImageViewer(logo);
            hi.add(imageView);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button startButton = new Button("Start");
        startButton.addActionListener(e -> controller.showRecipesList());
        hi.add(startButton);
        return hi;
    }
}
