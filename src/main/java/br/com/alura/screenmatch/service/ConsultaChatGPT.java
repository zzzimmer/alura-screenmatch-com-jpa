
package br.com.alura.screenmatch.service;

//import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.util.List;

public class ConsultaChatGPT {
    public static String obterTraducao(String texto) {
        OpenAiService service = new OpenAiService(System.getenv("API_CHAT_GPT"));

        ChatCompletionRequest requisicao = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(List.of(
                        new ChatMessage("system", "Você é um tradutor."),
                        new ChatMessage("user", "Traduza para o português: " + texto)
                ))
                .maxTokens(100)
                .temperature(0.7)
                .build();

//        var resposta = service.createCompletion(requisicao);
        var resposta = service.createChatCompletion(requisicao);
        return resposta.getChoices().get(0).getMessage().getContent().trim();
    }
}