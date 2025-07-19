package com.learning.deisgnpatterns.structural;

import java.util.*;

/**
 * FLYWEIGHT DESIGN PATTERN - JAVA IMPLEMENTATION
 *
 * Intent:
 * Reduce memory usage by sharing common (intrinsic) data between multiple objects.
 * Use when you need to create a large number of similar objects.
 *
 * Real-World Analogy:
 * In a forest, thousands of trees exist. Many of them are the same type and color.
 * Instead of creating separate TreeType objects for each tree, we share them using a factory.
 */

// Flyweight Object (intrinsic shared data)
class TreeType {
    private String type;
    private String color;

    public TreeType(String type, String color) {
        this.type = type;
        this.color = color;
    }

    /**
     * Shared draw logic. Called by Tree with position parameters.
     */
    public void draw(int x, int y) {
        System.out.println("Drawing tree at (" + x + "," + y + ") | type: " + type + " | color: " + color);
    }
}

// Flyweight Factory
class TreeFactory {

    // Key: "type_color", Value: TreeType instance
    private static Map<String, TreeType> hashTrees = new HashMap<>();

    /**
     * Reuses TreeType object if available; otherwise creates and stores one.
     */
    public static TreeType getTreeType(String type, String color) {
        String key = type + "_" + color;

        if (hashTrees.containsKey(key))
            return hashTrees.get(key);

        TreeType treeType = new TreeType(type, color);
        hashTrees.put(key, treeType);
        return treeType;
    }
}

// Context Object - Contains extrinsic data (x, y)
class Tree {
    private int x;
    private int y;
    private TreeType treeType;  // shared object

    public Tree(int x, int y, TreeType treeType) {
        this.x = x;
        this.y = y;
        this.treeType = treeType;
    }

    /**
     * Delegates drawing to shared TreeType with its unique coordinates.
     */
    public void draw() {
        treeType.draw(x, y);
    }
}

// Client - Manages large number of trees
class Forest {
    List<Tree> trees;

    public Forest() {
        trees = new ArrayList<>();
    }

    /**
     * Plants a tree using shared TreeType objects to save memory.
     */
    public void plantTree(int x, int y, String type, String color) {
        TreeType sharedType = TreeFactory.getTreeType(type, color);
        Tree tree = new Tree(x, y, sharedType);
        trees.add(tree);
    }

    /**
     * Draws all trees.
     */
    public void draw() {
        for (Tree t : trees)
            t.draw();
    }
}

// Main class
public class FlyWeight {
    public static void main(String[] args) {

        Forest amazon = new Forest();

        // Planting 1000 trees of same type and color (will reuse TreeType object)
        for (int i = 0; i < 1000; i++)
            amazon.plantTree(i, i, "Bamboo", "Green");

        amazon.draw();
    }
}
