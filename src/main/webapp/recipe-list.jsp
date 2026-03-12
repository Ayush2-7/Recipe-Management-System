<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.recipe.model.Recipe" %>
<!DOCTYPE html>
<html>
<head>
    <title>Recipe Sharing App</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow">
            <div class="card-header bg-primary text-white">
                <h3 class="mb-0">Recipe Management System</h3>
            </div>
            <div class="card-body">
                <form action="insert" method="post" class="row g-3 mb-4">
                    <div class="col-md-5">
                        <input type="text" name="title" class="form-control" placeholder="Recipe Name" required>
                    </div>
                    <div class="col-md-5">
                        <input type="text" name="ingredients" class="form-control" placeholder="Ingredients" required>
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-success w-100">Add Recipe</button>
                    </div>
                </form>

                <table class="table table-hover table-bordered">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Recipe Name</th>
                            <th>Ingredients</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    
                    <tbody>
                    <% 
                        // 1. Get the list using the EXACT name "listRecipe"
                        List<Recipe> recipes = (List<Recipe>) request.getAttribute("listRecipe"); 
                        
                        // 2. Check if the list has data
                        if (recipes != null && !recipes.isEmpty()) {
                            for (Recipe r : recipes) { 
                    %>
                        <tr>
                            <td><%= r.getId() %></td>
                            <td><strong><%= r.getTitle() %></strong></td>
                            <td><%= r.getIngredients() %></td>
                            <td>
                                <a href="delete?id=<%= r.getId() %>" 
   class="btn btn-danger btn-sm" 
   onclick="return confirm('Are you sure you want to delete this recipe?')">
   Delete
</a>
                            </td>
                        </tr>
                    <% 
                            }
                        } else { 
                    %>
                        <tr>
                            <td colspan="4" class="text-center text-muted">
                                No recipes found in the list. (Check Eclipse Console for Debug)
                            </td>
                        </tr>
                    <% } %>
                </tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>