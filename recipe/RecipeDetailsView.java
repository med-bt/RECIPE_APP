package com.example.recipe;

import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class RecipeDetailsView {
    private RecipeController controller;
    private RecipeModel recipe;
    public RecipeDetailsView(RecipeController controller, RecipeModel recipe) {
        this.controller = controller;
        this.recipe = recipe;
    }
    public Form createForm() {
        Form recipeDetailsForm = new Form(recipe.getName(), BoxLayout.y());
        try {
            Resources res = Resources.openLayered("/NativeTheme");
            String imageName = recipe.getImagePath();
            Image recipeImage = res.getImage(imageName);
            if (recipeImage == null) {
                System.out.println("Image is null. Check the image name and path.");
            } else {
                Image scaledImage = recipeImage.scaled(1000, 1000);
                ImageViewer imageView = new ImageViewer(scaledImage);
                recipeDetailsForm.add(imageView);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] ingredients = splitIngredients(recipe.getIngredients());
        Label ing = new Label("Ingredients:");
        ing.setUIID("subtitle");
        ing.getAllStyles().setAlignment(Component.CENTER);
        recipeDetailsForm.add(ing);
        for (String ingredient : ingredients) {
            recipeDetailsForm.add(new Label(ingredient));
        }
        Button backButton = new Button("Back");
        backButton.addActionListener(e -> controller.showRecipesList());
        recipeDetailsForm.add(backButton);
        return recipeDetailsForm;
    }
    public static String[] splitIngredients(String ingredients) {
        List<String> result = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < ingredients.length(); i++) {
            if (ingredients.charAt(i) == ',') {
                result.add(ingredients.substring(start, i).trim());
                start = i + 1;
            }
        }
        result.add(ingredients.substring(start).trim());
        return result.toArray(new String[0]);
    }
}
