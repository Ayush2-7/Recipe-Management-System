package com.recipe.model;

public class Recipe {
    private int id;
    private String title;
    private String ingredients;

    public Recipe(int id, String title, String ingredients) {
        this.id = id;
        this.title = title;
        this.ingredients = ingredients;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getIngredients() { return ingredients; }

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
}