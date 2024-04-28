package com.example.recipe;


import com.codename1.system.Lifecycle;
import com.codename1.ui.util.Resources;
import com.codename1.ui.plaf.UIManager;

public class RecipeApp extends Lifecycle {
    @Override
    public void runApp() {
        Resources theme = UIManager.initFirstTheme("/theme");
        RecipeController controller = new RecipeController();
        controller.showWelcomeScreen();
    }
}

