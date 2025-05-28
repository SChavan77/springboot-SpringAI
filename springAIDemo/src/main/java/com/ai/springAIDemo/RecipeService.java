package com.ai.springAIDemo;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecipeService {

    private final ChatModel chatModel;

    public RecipeService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createRecipe(String ingredients,
                               String cuisine,
                               //String dietary,
                               String dietaryRestrictions){
        var template= """
                I want to create a recipe using the following ingredients :{ingredients}.
                The cuisine type I prefer is {cuisine}.
                Please consider the following dietary restrictions : {dietaryRestrictions}. Please provide me with a detailed Recipe including title, list of ingredients and cooking instructions
                """;

        //Use Prompt template
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> params = Map.of(
                "ingredients",ingredients,
                "cuisine",cuisine,
                "dietaryRestrictions", dietaryRestrictions
        ); //a,b : a is the one used in template {a}

        Prompt prompt= promptTemplate.create(params);
        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}
