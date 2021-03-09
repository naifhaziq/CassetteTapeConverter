package cassetteTapeConverter;

import java.awt.Dimension;

import javax.swing.*;

public class Help extends JFrame{

    public Help() {
        setResizable(false);
        setBounds(100, 100, 450, 450);
        setPreferredSize(new Dimension(450, 450));
        setTitle("Help");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblWelcome = new JLabel("Welcome!");
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setBounds(162, 11, 95, 14);
        getContentPane().add(lblWelcome);

        JLabel lblText1 = new JLabel("<HTML>To get started, connect your cassette player to your computer microphone port using an aux cable. Then click in the box with the arrow pointing downwards and choose a data line (Usually the final option). Press start and play the cassette tape and wait for it to finish recording. The text at the bottom wil display \"Completed\" when it is done.\r\n</HTML>");
        lblText1.setVerticalAlignment(SwingConstants.TOP);
        lblText1.setBounds(10, 39, 424, 65);
        getContentPane().add(lblText1);

        JLabel lblText2 = new JLabel("<HTML>You can change the threshold value at settings and use the \"Reprocess\" function under the \"Tracks\" menu if you get less tracks than it was supposed to have.\r\n</HTML>");
        lblText2.setVerticalAlignment(SwingConstants.TOP);
        lblText2.setBounds(10, 115, 424, 45);
        getContentPane().add(lblText2);

        JLabel lblText3 = new JLabel("<HTML>You can put in information for the tracks in the \"Naming the tracks\" under \"Tracks\" menu.\r\n</HTML>");
        lblText3.setVerticalAlignment(SwingConstants.TOP);
        lblText3.setBounds(10, 171, 424, 45);
        getContentPane().add(lblText3);

        JLabel lblText4 = new JLabel("<HTML>Tracks can also be merged in the \"Naming the Tracks\". Check which tracks that should combine and click on \"Merge\". Wait for it to merge and a message will pop up to tell whether it merged or not.\r\n</HTML>");
        lblText4.setVerticalAlignment(SwingConstants.TOP);
        lblText4.setBounds(10, 227, 424, 65);
        getContentPane().add(lblText4);
    }
}
