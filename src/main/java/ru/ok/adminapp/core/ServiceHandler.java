package ru.ok.adminapp.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ServiceHandler {
    final private Logger logger =
            LogManager.getLogger(ServiceHandler.class);
    final public static String WORD_DICT = "badWords.txt";
    final public static String LINE_SEP = System.lineSeparator();

    private void writeWord(String word, Writer wr) throws IOException {
        wr.write(LINE_SEP + word + LINE_SEP);
    }

    private void logWriteError(IOException e) {
        logger.debug("Failed to write word to dict", e);
    }

    public void addWord(String word) {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(WORD_DICT, true))) {
            writeWord(word, wr);
        } catch (IOException e) {
            logWriteError(e);
        }
    }

    void deleteWord(String word) {
        try (BufferedReader br = new BufferedReader(
                new FileReader(WORD_DICT));
             BufferedWriter wr = new BufferedWriter(new FileWriter(WORD_DICT))) {
                br.lines().filter(s -> !s.equals(word)).forEach(w -> {
                    try {
                        writeWord(w, wr);
                    } catch (IOException e) {
                        logWriteError(e);
                    }
                });
        } catch (IOException e) {
            logger.debug("Failed while processing dict", e);
        }
    }

    public void process(Comment comment) {
        if (!isBad(comment.getText())) {
            return;
        }

        Ban ban = new Ban(comment.getPostId(), comment.getCommentId());
        //  ban using RMI
    }

    private boolean isBad(String text) {
        try (BufferedReader br = new BufferedReader(
                new FileReader(WORD_DICT))) {
            return br.lines().anyMatch(text::contains);
        } catch (IOException e) {
            logger.debug("Failed to read dict", e);
            return false;
        }
    }
}
