package Core.UI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import Core.MainLogic.ControlPanel;
import org.hibernate.mapping.Subclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class CommandConsole extends JFrame {
    private static final String NEW_LINE = "\n--> ";
    private static final String WELCOME_MESSAGE="Type \"list\" for commands or /help for command description.";

    @Autowired
    private ControlPanel controlPanel;

    private JTextArea textField = new JTextArea(WELCOME_MESSAGE+NEW_LINE);
    private ArrayList<String> commandHistory=new ArrayList();
    private int historyPointer=0;
    private String lastCommand="";
    public CommandConsole(){
        initializeCommands();
        super.setTitle("admin-app: Command console");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
                switch(e.getKeyCode()){
                    case(0x24): // HOME
                        e.consume();
                    case(0x26): // UP
                        textField.setText(scrollCommand(true));
                        textField.moveCaretPosition(textField.getText().length());
                        e.consume();
                        break;
                    case(0x28): // DOWN
                        textField.setText(scrollCommand(false));
                        textField.moveCaretPosition(textField.getText().length());
                        e.consume();
                        break;
                    case(0x0A): // ENTER
                        enter();
                        e.consume();
                        break;
                    case(0x08): // BACKSPACE
                        String s=textField.getText();
                        if(s.substring(s.length()-NEW_LINE.length()).equals(NEW_LINE)){e.consume();}
                        break;
                }
            }
        });
        final JScrollPane scrollPane = new JScrollPane(textField);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        this.getContentPane().add(mainPanel);
        this.setPreferredSize(new Dimension(700, 500));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    private String scrollCommand(boolean up){
        String text=textField.getText();
        String ret=text.substring(0,text.lastIndexOf("\n")+NEW_LINE.length());
        if(up){
            if(historyPointer+1>commandHistory.size())return textField.getText();
            historyPointer++;
            return ret+commandHistory.get(commandHistory.size()-historyPointer);
        }
        else{
            if(historyPointer-1<1||commandHistory.isEmpty()){historyPointer=0;return ret;}
            historyPointer--;
            return ret+commandHistory.get(commandHistory.size()-historyPointer);
        }
    }
    private void enter() {
        historyPointer=0;
        String text=textField.getText();
        lastCommand=text.substring(text.lastIndexOf("\n")+NEW_LINE.length());
        commandHistory.add(lastCommand);
        if(lastCommand.isEmpty()){
            return;
        }
        else{
            String commandExecutionResult=Command.ExecuteLine(lastCommand);
            textField.setText(text+"\n" + commandExecutionResult + NEW_LINE);
            textField.moveCaretPosition(textField.getText().length());
        }
    }
    private void initializeCommands(){
        String definition="\nclcache - Clears the cache and reinitializes it.\n";
        String argDescription="\nArguments:\n"+
                "/help - Opens this message.";
        String[] argVector=new String[]{"/help"};
        int[] subArgCount=new int[]{0};
        int[][] incompatibleArgs=new int[][]{};
        Command command_clcache=new Command("clcache",argVector,subArgCount,incompatibleArgs,definition,argDescription) {
            @Override
            protected String logic(ArgMatrix argMatrix) {
                if(!argMatrix.argument_matches[0]){
                    controlPanel.resetCache();
                    return "OK";
                }
                return "";
            }
        };

        definition="\nsub - Subscribe to post by ID.\n";
        argDescription="\nArguments:\n"+
                "/p <post_id> - Required. Post to subscribe to;\n"+
                "/help - Opens this message.";
        argVector=new String[]{"/help","/p"};
        subArgCount=new int[]{0,1};
        incompatibleArgs=new int[][]{};
        Command command_sub=new Command("sub",argVector,subArgCount,incompatibleArgs,definition,argDescription) {
            @Override
            protected String logic(ArgMatrix argMatrix) {
                if(argMatrix.noArgMatches())return null;
                if(argMatrix.argument_matches[1]){
                    controlPanel.subscribe(Long.parseLong(argMatrix.subarguments[1][0]),true);
                    return "OK";
                }
                return "";
            }
        };
        definition="\nconfig - Configure application.\n";
        argDescription="\nArguments:\n"+
                "/a <admin_id> - Give new admin ID;\n"+
                "/u <api-bot_url> - Give new api-bot URL;"+
                "/help - Opens this message.";
        argVector=new String[]{"/help","/a","/u"};
        subArgCount=new int[]{0,1,1};
        incompatibleArgs=new int[][]{};
        Command command_config=new Command("config",argVector,subArgCount,incompatibleArgs,definition,argDescription) {
            @Override
            protected String logic(ArgMatrix argMatrix) {
                if(argMatrix.noArgMatches())return null;
                if(argMatrix.argument_matches[1]){
                    ControlPanel.adminId=Long.parseLong(argMatrix.subarguments[1][0]);
                }
                if(argMatrix.argument_matches[2]){
                    ControlPanel.botApiSubscribeUrl=argMatrix.subarguments[2][0];
                }
                return "";
            }
        };
    }

}

