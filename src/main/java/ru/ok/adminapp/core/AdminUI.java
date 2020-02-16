package ru.ok.adminapp.core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminUI {

    public static void main(String[] args){
        // Create frame with title Registration Demo
        JFrame frame= new JFrame();
        frame.setTitle("Admin UI");

        // Panel to define the layout. We are using GridBagLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel headingPanel = new JPanel();
        JLabel headingLabel = new JLabel("");
        headingPanel.add(headingLabel);


        // Panel to define the layout. We are using GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        // Constraints for the layout
        GridBagConstraints constr = new GridBagConstraints();
        constr.insets = new Insets(5, 5, 5, 5);
        constr.anchor = GridBagConstraints.WEST;

        // Set the initial grid values to 0,0
        constr.gridx=0;
        constr.gridy=0;


        // Declare Text fields
        JButton requestNewButton = new JButton("Request new case");
        JButton banButton = new JButton("Ban");
        JButton subscribeButton = new JButton("Subscribe");
        constr.anchor = GridBagConstraints.CENTER;

        constr.gridx=1;
        panel.add(requestNewButton, constr);
        constr.gridy=1;
        panel.add(banButton, constr);
        constr.gridy=2;
        panel.add(subscribeButton, constr);
        constr.gridx = 2;
        JTextField postIdTextField = new JTextField("postId");
        panel.add(postIdTextField, constr);
        constr.gridy = 3;
        constr.gridwidth = 2;

        mainPanel.add(headingPanel);
        mainPanel.add(panel);

        // Add panel to frame
        frame.add(mainPanel);
        frame.pack();
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}