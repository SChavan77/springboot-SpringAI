/* eslint-disable no-unused-vars */
import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ImageGenerator from './components/ImageGenerator';
import ChatComponent from './components/ChatComponent';
import RecipeGenerator from './components/RecipeGenerator';

function App() {
    const [activeTab, setActiveTab] =useState('image-generator');
    
  const handleTabChange = (tab)=> {
    //alert(tab);
    setActiveTab(tab);
  };

  return (
    <>
      <>
      <button className={activeTab=='image-generator'?'active':''} onClick={() => handleTabChange('image-generator')}>Image Generator</button>
      <button className={activeTab=='chat'?'active':''} onClick={() => handleTabChange('chat')}>Chat with AI</button>
      <button className={activeTab=='recipe-generator'?'active':''} onClick={() => handleTabChange('recipe-generator')}>Recipe Generator</button>
    </>
    <>
      {activeTab=='image-generator' && <ImageGenerator/>}
      {activeTab=='chat' && <ChatComponent/>}
      {activeTab=='recipe-generator' && <RecipeGenerator/>}
    </>
    </>
  )
}
export default App

/**
 * EARLIER CODE - 
 * 
      {activeTab=='image-generator' && <h2>Image Generator</h2>}
      {activeTab=='chat' && <h2>Chat</h2>}
      {activeTab=='recipe-generator' && <h2>Recipe Generator</h2>}
 * 
 */