/* eslint-disable no-unused-vars */
import { useState } from "react";

const RecipeGenerator =()=>{
 // 3 Inputs
 const [ingredients, setIngredients] =useState('');
 const [cuisine, setCuisine] =useState('');
 const [dietaryRestrictions, setDietaryRestrictions]=useState('');
 //1 Output
 const [recipe, setRecipe] =useState('');

 const createRecipe =async()=>{
      try {
         const response =await fetch(`http://localhost:8081/gen-recipe?ingredients=${ingredients}&cuisine=${cuisine}&dietaryRestrictions=${dietaryRestrictions}`);
            const data = await response.text();
            setRecipe(data);
        
      } catch (error) {
        console.log("Error in Recipe Generation",error);
        
      }
 }
     return (
     <>
       <div>
          <h2> Create a Recipe</h2>
          <input
              type="text"
              value={ingredients}
              onChange={(e)=>setIngredients(e.target.value)}
              placeholder="Enter the Ingrediants (Comma Separated)"
          />
          <input
              type="text"
              value={cuisine}
              onChange={(e)=>setCuisine(e.target.value)}
              placeholder="Enter Cuisine type"
          />
          <input
              type="text"
              value={dietaryRestrictions}
              onChange={(e)=>setDietaryRestrictions(e.target.value)}
              placeholder="Enter Dietary Restrictions"
          />
          <button onClick={createRecipe}>Create Recipe</button>
       </div>
       <div className="output">
          <pre className="recipe-text">{recipe}</pre>
       </div>
     </>
     );
}

export default RecipeGenerator;