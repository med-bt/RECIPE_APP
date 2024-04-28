package com.example.recipe;

import com.codename1.ui.Form;

public class RecipeController {
    private Form currentForm;
    public RecipeController() {
        RecipeModel.initDB();
        RecipeModel.insertExampleRecipes();
    }
    public void showWelcomeScreen() {
        Form welcomeForm = new WelcomeView(this).createForm();
        switchForm(welcomeForm);
    }
    public void showRecipesList() {
        java.util.List<RecipeModel> recipes = RecipeModel.readRecipes();
        Form recipesListForm = new RecipesListView(this, recipes).createForm();
        switchForm(recipesListForm);
    }
    public void showRecipeDetails(RecipeModel recipe) {
        Form recipeDetailsForm = new RecipeDetailsView(this, recipe).createForm();
        switchForm(recipeDetailsForm);
    }
    private void switchForm(Form newForm) {
        if (currentForm != null) {
            currentForm.showBack();
        }
        currentForm = newForm;
        currentForm.show();
    }

}