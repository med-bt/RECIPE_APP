package com.example.recipe;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class RecipeModel {
    private String name;
    private String ingredients;
    private String imagePath;
    private static Database db;
    private static boolean dbInitialized = false;
    public RecipeModel(String name, String ingredients, String imagePath) {
        this.name = name;
        this.ingredients = ingredients;
        this.imagePath = imagePath;
    }
    @Override
    public String toString() {
        return name;
    }
    public String getName() { return name; }
    public String getIngredients() { return ingredients; }
    public String getImagePath() { return imagePath; }
    // database creation
    public static void initDB() {
        if (dbInitialized) return;
        try {
            db = Database.openOrCreate("RecipeDB.db");
            db.execute("CREATE TABLE IF NOT EXISTS recipes (id INTEGER PRIMARY KEY, name TEXT, ingredients TEXT, imagePath TEXT);");
            dbInitialized = true;
        } catch (IOException e) {
            Log.e(e);
        }
    }

    // inseting data into the database
    public static void insertExampleRecipes() {
        if (!dbInitialized) initDB();
        try {
            db.execute("DELETE FROM recipes;");
            String[][] examples = {
                    {"Spaghetti Carbonara", "Pasta, Eggs, meet", "spagetti.png"},
                    {"Margherita Pizza", "Dough, Tomatoes, Mozzarella, Basil", "pizza.png"},
                    {"Chicken Alfredo", "Fettuccine, Chicken, Cream, Parmesan Cheese", "chicken_alfredo.png"},
                    {"Caesar Salad", "Romaine Lettuce, Croutons, Parmesan Cheese, Caesar Dressing", "caesar_salad.png"},
                    {"Beef Burger", "Beef Patty, Lettuce, Tomato, Onion, Pickles, Cheese, Burger Bun", "beef_burger.png"},
                    {"Chocolate Cake", "Flour, Sugar, Cocoa Powder, Eggs, Butter, Milk, Vanilla Extract", "chocolate_cake.png"},
                    {"Fish and Chips", "Fish Fillets, Potatoes, Flour, Baking Powder", "fish_and_chips.png"}
            };
            for (String[] recipe : examples) {
                db.execute("INSERT INTO recipes (name, ingredients, imagePath) VALUES (?, ?, ?);", new Object[]{recipe[0], recipe[1], recipe[2]});
            }
        } catch (IOException e) {
            Log.e(e);
        }
    }

    // Read data from the database
    public static List<RecipeModel> readRecipes() {
        List<RecipeModel> recipes = new ArrayList<>();
        if (!dbInitialized) initDB();
        try {
            Cursor cur = db.executeQuery("SELECT * FROM recipes;");
            while (cur.next()) {
                Row row = cur.getRow();
                String name = row.getString(1);
                String ingredients = row.getString(2);
                String imagePath = row.getString(3);
                recipes.add(new RecipeModel(name, ingredients, imagePath));
            }
        } catch (IOException e) {
            Log.e(e);
        }
        return recipes;
    }

}

