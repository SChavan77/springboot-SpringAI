package com.ai.springAIDemo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GenAIController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/ask-ai")
    public String getResponse (@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("/ask-ai-options")
    public String getResponseOptions (@RequestParam String prompt){
        return chatService.getResponseOptions(prompt);
    }

    @GetMapping("/gen-image")
    public void generateImage(HttpServletResponse response, @RequestParam String prompt) throws IOException {
        ImageResponse imageResponse= imageService.generateImage(prompt);
        String imageUrl=imageResponse.getResult().getOutput().getUrl();
        //to redirect the user to that URL, for that we need HTTPServlet
        response.sendRedirect(imageUrl);
    }

    @GetMapping("/gen-images")
    public List<String> generateImageMultiple(HttpServletResponse response, @RequestParam String prompt) throws IOException {
        ImageResponse imageResponse= imageService.generateImageWithOptions(prompt);
                List<String> imageUrls=imageResponse.getResults().stream()
                        .map(result-> result.getOutput().getUrl()).collect(Collectors.toList());
        imageUrls.forEach(System.out::println);
        return imageUrls;
    }

    @GetMapping("/gen-image-options")
    public List<String> generateImageCustom(HttpServletResponse response,
                                            @RequestParam String prompt,
                                            @RequestParam (value="quality", defaultValue="hd") String quality,
                                            @RequestParam (defaultValue="1") int N,
                                            @RequestParam (defaultValue="1024") int width,
                                            @RequestParam (defaultValue="1024") int height) throws IOException {
        ImageResponse imageResponse= imageService.generateImageWithCustom(prompt, quality, N, width, height);
        List<String> imageUrls=imageResponse.getResults().stream()
                .map(result-> result.getOutput().getUrl()).collect(Collectors.toList());
        imageUrls.forEach(System.out::println);
        return imageUrls;
    }

    @GetMapping("/gen-recipe")
    public String recipeBuilder(@RequestParam String ingredients,
                                @RequestParam(defaultValue = "any") String cuisine,
                                //@RequestParam String dietary,
                                @RequestParam(defaultValue = "") String dietaryRestrictions){
        return recipeService.createRecipe(ingredients,cuisine,dietaryRestrictions);
    }
}

