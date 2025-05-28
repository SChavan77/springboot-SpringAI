/* eslint-disable no-unused-vars */
import { useState } from "react";

const ImageGenerator =()=>{

    const [prompt, setPrompt]=useState(''); //init with empty string
    const [imageUrls, setImageUrls]=useState([]); //init with Empty array

    const generateImage = async() =>{
        try {
            const response =await fetch(`http://localhost:8081/gen-image-options?prompt=${prompt}`);
            const urls = await response.json();
            console.log(urls);
            
            setImageUrls(urls);
        } catch (error) {
            console.log("Error generating images :", error);
            
        }
    }


     return (
     <>
       <div className="tab-content">
            <input type="text" 
                   value={prompt} 
                   onChange={(e)=>setPrompt(e.target.value)}
                    placeholder="Enter prompt for Image generation"
                   ></input>

            <button onClick={generateImage}>Generate Images</button>
       </div>
       <div className="image-grid">
            {   imageUrls.map((url,index)=>(
                <img key={index} src={url} atl={`Generated ${index}`}/>
            ))}
            {[...Array(3-imageUrls.length)].map((_,index)=>(
                <div key={index+imageUrls.length} className="empty-image-slot"> </div>
            ))} 
       </div> 
     </>
     );
}

export default ImageGenerator;


/***
 * 
 * We need states- 
 * prompt,imageURL : The request eneterd by user (as a prompt)- managed by a state variable 'prompt'
 * imageURL : Get the output from the back-end - managed by another state.
 * 
 * //if there are 3 imageUrls, need to display 1 slot., that's why we created the array
 * 
 */