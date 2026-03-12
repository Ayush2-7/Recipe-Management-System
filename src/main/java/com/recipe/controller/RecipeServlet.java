package com.recipe.controller;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.recipe.model.Recipe;

@WebServlet(urlPatterns = {"/", "/insert", "/delete"})
public class RecipeServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dbUrl = "jdbc:mysql://localhost:3306/recipe_db";
    private String dbUser = "root";
    private String dbPass = "your_password"; // CHANGE THIS TO YOUR PASSWORD
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String ingredients = request.getParameter("ingredients");

        Connection con = null;
        try {
            // 1. Force load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 2. Establish connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipe_db", "root", "root");
            
            // 3. Prepare Statement
            String sql = "INSERT INTO recipes (title, ingredients) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, ingredients);
            
            int result = ps.executeUpdate();
            System.out.println("DEBUG: Rows affected: " + result);

        } catch (Exception e) {
            System.out.println("DEBUG ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try { if(con != null) con.close(); } catch (Exception e) {}
        }
        
        // Redirect back to home
        response.sendRedirect(request.getContextPath() + "/");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Identify what the user wants to do based on the URL
        String action = request.getServletPath();
        
        // Database credentials (Ensure these match your MySQL setup)
        String dbUrl = "jdbc:mysql://localhost:3306/recipe_db";
        String dbUser = "root";
        String dbPass = "your_password"; 

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            if (action.equals("/delete")) {
                // --- DELETE LOGIC ---
                int id = Integer.parseInt(request.getParameter("id"));
                
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipe_db", "root", "root")) {
                    String sql = "DELETE FROM recipes WHERE id = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, id);
                    int result = ps.executeUpdate();
                    System.out.println("Delete Action - Rows affected: " + result);
                }
                // After deleting, send them back to the clean home URL
                response.sendRedirect(request.getContextPath() + "/");
                
            } else {
                // --- READ/LIST LOGIC (Default Action) ---
                List<Recipe> list = new ArrayList<>();
                
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipe_db", "root", "root")) {
                    String sql = "SELECT * FROM recipes";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    
                    while (rs.next()) {
                        // Make sure these column names match your MySQL table exactly
                        int id = rs.getInt("id");
                        String title = rs.getString("title");
                        String ingredients = rs.getString("ingredients");
                        list.add(new Recipe(id, title, ingredients));
                    }
                }
                
                // Pass the data to the JSP
                request.setAttribute("listRecipe", list);
                request.getRequestDispatcher("recipe-list.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            System.out.println("Servlet Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
	@SuppressWarnings("unused")
	private void listRecipes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Recipe> list = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recipe_db", "root", "root")) {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM recipes");
            while (rs.next()) {
                list.add(new Recipe(rs.getInt("id"), rs.getString("title"), rs.getString("ingredients")));
            }
        }
        // CRITICAL: This attribute name must match the one in your JSP loop
        request.setAttribute("listRecipe", list); 
        request.getRequestDispatcher("recipe-list.jsp").forward(request, response);
    }
}