package com.ai.springAIDemo;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    private final OpenAiImageModel openAiImageModel;

    public ImageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    public ImageResponse generateImage(String prompt){
            ImageResponse imageResponse= openAiImageModel.call(
                    new ImagePrompt(prompt)
            );
            return imageResponse;
    }

    public ImageResponse generateImageWithOptions(String prompt){
        ImageResponse imageResponse= openAiImageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .model("dall-e-2")
                                .N(3)
                                //.quality("hd")
                                .height(1024)
                                .width(1024).build())
        );
        return imageResponse;
    }

    public ImageResponse generateImageWithCustom(String prompt, String quality, int n, int width, int height ){
        ImageResponse imageResponse= openAiImageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .model("dall-e-2")
                                .N(n)
                               // .quality(quality)
                                .height(height)
                                .width(width).build())
        );
        return imageResponse;
    }
}

/*
By default dall-e-2, but still using it, for eg. and default N=1-10.
For dall-e-3: N=1
 */