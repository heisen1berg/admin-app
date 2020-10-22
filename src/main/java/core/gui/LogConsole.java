package core.gui;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.HashSet;

@Component
public class LogConsole extends JFrame {
    private static final String WELCOME_MESSAGE="";
    private HashSet<Integer> filters=new HashSet();

    private JTextArea textField = new JTextArea(WELCOME_MESSAGE);
    public LogConsole(){
        super.setTitle("admin-app: Log console");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        textField.setFont(new Font("Consolas", Font.PLAIN, 15));
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.GREEN);
        textField.setCaretPosition(textField.getText().length());
        textField.setLineWrap(true);
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                e.consume();
            }
        });
        final JScrollPane scrollPane = new JScrollPane(textField);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        this.getContentPane().add(mainPanel);
        this.setPreferredSize(new Dimension(700, 500));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }
    protected void showFrame(){
        this.setVisible(true);
    }
    protected void changeFilter(int messageType) {
        if(!filters.contains(messageType)){
            filters.add(messageType);
        }
        else {
            filters.remove(messageType);
        }
    }
    public void log(LogLine line){
        if(!filters.contains(line.getType())){
            textField.append("["+new Date().toString()+"]"+line.getText()+"\n");
        }
    }

    protected HashSet<Integer> getFilters() {
        return filters;
    }
}