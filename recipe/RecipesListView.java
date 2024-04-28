package com.example.recipe;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.List;
import com.codename1.ui.list.DefaultListModel;

public class RecipesListView {
    private RecipeController controller;
    private java.util.List<RecipeModel> recipes;

    public RecipesListView(RecipeController controller, java.util.List<RecipeModel> recipes) {
        this.controller = controller;
        this.recipes = recipes;
    }

    public Form createForm() {
        Form recipesListForm = new Form("Recipes", BoxLayout.y());
        DefaultListModel<RecipeModel> model = new DefaultListModel<>();
        for (RecipeModel recipe : recipes) {
            model.addItem(recipe);
        }

        List<RecipeModel> recipesList = new List<>(model);
        recipesList.addActionListener(e -> {
            RecipeModel selectedRecipe = recipesList.getSelectedItem();
            recipesList.setUIID("subtitle");
            controller.showRecipeDetails(selectedRecipe);
        });

        recipesListForm.add(recipesList);
        Button backButton = new Button("Back");
        backButton.addActionListener(e -> controller.showWelcomeScreen());
        recipesListForm.add(backButton);
        return recipesListForm;
    }
}
