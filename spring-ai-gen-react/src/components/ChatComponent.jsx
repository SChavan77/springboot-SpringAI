/* eslint-disable no-unused-vars */
import { useState } from "react";

const ChatComponent =()=>{
  const [prompt, setPrompt]=useState('');
    const [chatResponse, setChatResponse]=useState('');
    
    const handleChatPrompt =async() =>{
      try {
        const response =await fetch(`http://localhost:8081/ask-ai?prompt=${prompt}`);
        const data = await response.text();
        setChatResponse(data);
      } catch (error) {
          console.log("Error in AI Chat", error);
      }
    }
     return (
     <>
       <div>
        <input
            type="text"
            onChange={(e)=> setPrompt(e.target.value)}
            placeholder="Start a chat with AI"
        />
        <button onClick={handleChatPrompt}>Submit</button>
       </div>
       <div className="output">
          <p>{chatResponse}</p>
       </div>
     </>
     );
}

export default ChatComponent;