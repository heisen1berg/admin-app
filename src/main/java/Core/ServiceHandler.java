package Core;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class ServiceHandler {
    final private String WORD_DICT = "badWords.txt";

    void addWord(String word) {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(WORD_DICT, true))) {
            wr.write(word + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void deleteWord(String word) {
        List<String> lines;
        try (BufferedReader br = new BufferedReader(
                new FileReader(WORD_DICT))) {
            lines = br.lines().filter(s -> !s.equals(word)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(WORD_DICT))) {
            for (String line : lines) {
                wr.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void process(Event event, String botApiURI) {
        if (!isBad(event.getText())) {
            return;
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Ban> request = new HttpEntity<>(new Ban(
                event.getPostId(), event.getCommentId()
        ));
        restTemplate.postForLocation(botApiURI, request, Ban.class);
    }

    private boolean isBad(String text) {
        try (BufferedReader br = new BufferedReader(
                new FileReader(WORD_DICT))) {
            return br.lines().anyMatch(text::contains);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
