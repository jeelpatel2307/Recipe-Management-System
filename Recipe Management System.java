recipe_management/
│   ├── main.py
│   └── recipes.json
└── README.md




import json

# Function to load recipes from JSON file
def load_recipes():
    try:
        with open('recipes.json', 'r') as file:
            recipes = json.load(file)
    except FileNotFoundError:
        recipes = []
    return recipes

# Function to save recipes to JSON file
def save_recipes(recipes):
    with open('recipes.json', 'w') as file:
        json.dump(recipes, file, indent=4)

# Function to add a new recipe
def add_recipe():
    recipes = load_recipes()
    title = input("Enter recipe title: ")
    ingredients = input("Enter ingredients (comma-separated): ").split(',')
    instructions = input("Enter instructions: ")
    cuisine = input("Enter cuisine type: ")
    meal_type = input("Enter meal type: ")
    tags = input("Enter tags (comma-separated): ").split(',')
    recipe = {
        'title': title,
        'ingredients': ingredients,
        'instructions': instructions,
        'cuisine': cuisine,
        'meal_type': meal_type,
        'tags': tags
    }
    recipes.append(recipe)
    save_recipes(recipes)
    print("Recipe added successfully!")

# Function to view all recipes
def view_recipes():
    recipes = load_recipes()
    if not recipes:
        print("No recipes found.")
    else:
        for i, recipe in enumerate(recipes, start=1):
            print(f"Recipe {i}: {recipe['title']}")

# Function to edit a recipe
def edit_recipe():
    recipes = load_recipes()
    view_recipes()
    choice = int(input("Enter recipe number to edit: "))
    if 1 <= choice <= len(recipes):
        recipe = recipes[choice - 1]
        print("Editing Recipe:")
        print(f"1. Title: {recipe['title']}")
        print(f"2. Ingredients: {', '.join(recipe['ingredients'])}")
        print(f"3. Instructions: {recipe['instructions']}")
        print(f"4. Cuisine: {recipe['cuisine']}")
        print(f"5. Meal Type: {recipe['meal_type']}")
        print(f"6. Tags: {', '.join(recipe['tags'])}")
        field = int(input("Enter field number to edit: "))
        if field == 1:
            recipe['title'] = input("Enter new title: ")
        elif field == 2:
            recipe['ingredients'] = input("Enter new ingredients (comma-separated): ").split(',')
        elif field == 3:
            recipe['instructions'] = input("Enter new instructions: ")
        elif field == 4:
            recipe['cuisine'] = input("Enter new cuisine type: ")
        elif field == 5:
            recipe['meal_type'] = input("Enter new meal type: ")
        elif field == 6:
            recipe['tags'] = input("Enter new tags (comma-separated): ").split(',')
        save_recipes(recipes)
        print("Recipe edited successfully!")
    else:
        print("Invalid choice.")

# Function to delete a recipe
def delete_recipe():
    recipes = load_recipes()
    view_recipes()
    choice = int(input("Enter recipe number to delete: "))
    if 1 <= choice <= len(recipes):
        del recipes[choice - 1]
        save_recipes(recipes)
        print("Recipe deleted successfully!")
    else:
        print("Invalid choice.")

# Main function
def main():
    while True:
        print("\nRecipe Management System")
        print("1. Add Recipe")
        print("2. View Recipes")
        print("3. Edit Recipe")
        print("4. Delete Recipe")
        print("5. Exit")
        choice = input("Enter your choice: ")
        if choice == '1':
            add_recipe()
        elif choice == '2':
            view_recipes()
        elif choice == '3':
            edit_recipe()
        elif choice == '4':
            delete_recipe()
        elif choice == '5':
            print("Exiting program...")
            break
        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    main()
