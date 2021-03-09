package cassetteTapeConverter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.SequenceInputStream;
import java.nio.file.Paths;

import javax.swing.*;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v23Tag;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;

public class ID3tags extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField textTrackNumber;
    private JTextField textArtist;
    private JTextField textTitle;
    private JTextField textAlbum;
    private JTextField textYear;
    private JTextField textComment;
    private JTextField textComposer;
    private JTextField textPublisher;
    private JTextField textOriginalArtist;
    private JTextField textAlbumArtist;
    private JButton btnBack;
    private JButton btnNextTrack;
    private JButton btnPreviousTrack;
    private JButton btnSaveID3tag;
    private JButton btnLoadID3tag;
    private JPanel panelID3;

    private int trackPosition = 1;
    private int trackQuantity = AudioSplitter.getTrackCount()-1;

    private String the_trackNo;
    private String the_artist;
    private String the_album;
    private String the_year;
    private String the_composer;
    private String the_publisher;
    private String the_originalArtist;
    private String the_albumArtist;
    private String filepath = "ID3tag.txt";

    private int the_trackNumber = 0;

    private Boolean firstTag = true;
    private JTextField textTrackNumber_2;
    private JTextField textArtist_2;
    private JTextField textTitle_2;
    private JTextField textAlbum_2;
    private JTextField textYear_2;
    private JTextField textComment_2;
    private JTextField textComposer_2;
    private JTextField textPublisher_2;
    private JTextField textOriginalArtist_2;
    private JTextField textAlbumArtist_2;
    private JTextField textTrackNumber_3;
    private JTextField textArtist_3;
    private JTextField textTitle_3;
    private JTextField textAlbum_3;
    private JTextField textYear_3;
    private JTextField textComment_3;
    private JTextField textComposer_3;
    private JTextField textPublisher_3;
    private JTextField textOriginalArtist_3;
    private JTextField textAlbumArtist_3;
    private JTextField textTrackNumber_4;
    private JTextField textArtist_4;
    private JTextField textTitle_4;
    private JTextField textAlbum_4;
    private JTextField textYear_4;
    private JTextField textComment_4;
    private JTextField textComposer_4;
    private JTextField textPublisher_4;
    private JTextField textOriginalArtist_4;
    private JTextField textAlbumArtist_4;
    private JTextField textTrackNumber_5;
    private JTextField textArtist_5;
    private JTextField textTitle_5;
    private JTextField textAlbum_5;
    private JTextField textYear_5;
    private JTextField textComment_5;
    private JTextField textComposer_5;
    private JTextField textPublisher_5;
    private JTextField textOriginalArtist_5;
    private JTextField textAlbumArtist_5;
    private JTextField textTrackNumber_6;
    private JTextField textArtist_6;
    private JTextField textTitle_6;
    private JTextField textAlbum_6;
    private JTextField textYear_6;
    private JTextField textComment_6;
    private JTextField textComposer_6;
    private JTextField textPublisher_6;
    private JTextField textOriginalArtist_6;
    private JTextField textAlbumArtist_6;
    private JTextField textTrackNumber_7;
    private JTextField textArtist_7;
    private JTextField textTitle_7;
    private JTextField textAlbum_7;
    private JTextField textYear_7;
    private JTextField textComment_7;
    private JTextField textComposer_7;
    private JTextField textPublisher_7;
    private JTextField textOriginalArtist_7;
    private JTextField textAlbumArtist_7;
    private JTextField textTrackNumber_8;
    private JTextField textArtist_8;
    private JTextField textTitle_8;
    private JTextField textAlbum_8;
    private JTextField textYear_8;
    private JTextField textComment_8;
    private JTextField textComposer_8;
    private JTextField textPublisher_8;
    private JTextField textOriginalArtist_8;
    private JTextField textAlbumArtist_8;
    private JTextField textTrackNumber_9;
    private JTextField textArtist_9;
    private JTextField textTitle_9;
    private JTextField textAlbum_9;
    private JTextField textYear_9;
    private JTextField textComment_9;
    private JTextField textComposer_9;
    private JTextField textPublisher_9;
    private JTextField textOriginalArtist_9;
    private JTextField textAlbumArtist_9;
    private JTextField textTrackNumber_10;
    private JTextField textArtist_10;
    private JTextField textTitle_10;
    private JTextField textAlbum_10;
    private JTextField textYear_10;
    private JTextField textComment_10;
    private JTextField textComposer_10;
    private JTextField textPublisher_10;
    private JTextField textOriginalArtist_10;
    private JTextField textAlbumArtist_10;
    private JButton playTrack_1;
    private JButton playTrack_2;
    private JButton playTrack_3;
    private JButton playTrack_4;
    private JButton playTrack_5;
    private JButton playTrack_6;
    private JButton playTrack_7;
    private JButton playTrack_8;
    private JButton playTrack_9;
    private JButton playTrack_10;
    private JButton stopTrack_1;
    private JButton stopTrack_2;
    private JButton stopTrack_3;
    private JButton stopTrack_4;
    private JButton stopTrack_5;
    private JButton stopTrack_6;
    private JButton stopTrack_7;
    private JButton stopTrack_8;
    private JButton stopTrack_9;
    private JButton stopTrack_10;
    private JLabel tracksRecorded;
    JCheckBox track1chk;
    JCheckBox track2chk;
    JCheckBox track3chk;
    JCheckBox track4chk;
    JCheckBox track5chk;
    JCheckBox track6chk;
    JCheckBox track7chk;
    JCheckBox track8chk;
    JCheckBox track9chk;
    JCheckBox track10chk;
    private int tracksSelected = 0;
    private int trackChosen;
    private int firstTrack = 0;

    //private static MediaPlayer mediaPlayer;
    private static Boolean playingTrack = false;

    public ID3tags() {
        setBounds(100, 100, 990, 560);
        setPreferredSize(new Dimension(990, 560));
        setTitle("ID3 Tag");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panelID3 = new JPanel();
        setContentPane(panelID3);

        btnBack = new JButton("Back to Audio Processor");
        btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
        btnBack.setEnabled(true);
        btnBack.addActionListener(new ActionListener() {
                                      public void actionPerformed(ActionEvent event) {
                                          dispose();
                                          AudioSplitter frame = new AudioSplitter();
                                          frame.pack();
                                          frame.setVisible(true);
                                          frame.enableReprocess();
                                      }
                                  }
        );

        final JFXPanel fxPanel = new JFXPanel();

        JLabel lblOutputTrackNumber = new JLabel("This is Track_" + trackPosition);
        lblOutputTrackNumber.setFont(new Font("Arial", Font.PLAIN, 20));
        panelID3.setLayout(null);
        //panelID3.add(lblOutputTrackNumber);

        JLabel lblTrackNumber = new JLabel("<HTML>Track<br>Number :</HTML>");
        lblTrackNumber.setHorizontalAlignment(SwingConstants.CENTER);
        lblTrackNumber.setBounds(44, 0, 84, 55);
        lblTrackNumber.setFont(new Font("Arial", Font.PLAIN, 15));
        panelID3.add(lblTrackNumber);

        JLabel lblArtist_1 = new JLabel("Artist :");
        lblArtist_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblArtist_1.setBounds(128, 0, 84, 55);
        lblArtist_1.setFont(new Font("Arial", Font.PLAIN, 15));
        panelID3.add(lblArtist_1);

        JLabel lblTitle_1 = new JLabel("Title :");
        lblTitle_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle_1.setBounds(212, 0, 84, 55);
        lblTitle_1.setFont(new Font("Arial", Font.PLAIN, 15));
        panelID3.add(lblTitle_1);

        JLabel lblAlbum_1 = new JLabel("Album :");
        lblAlbum_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblAlbum_1.setBounds(296, 0, 84, 55);
        lblAlbum_1.setFont(new Font("Arial", Font.PLAIN, 15));
        panelID3.add(lblAlbum_1);

        JLabel lblYear_1 = new JLabel("Year :");
        lblYear_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblYear_1.setBounds(380, 0, 84, 55);
        lblYear_1.setFont(new Font("Arial", Font.PLAIN, 15));
        panelID3.add(lblYear_1);

        JLabel lblComment_1 = new JLabel("Comment :");
        lblComment_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblComment_1.setBounds(464, 0, 84, 55);
        lblComment_1.setFont(new Font("Arial", Font.PLAIN, 15));
        panelID3.add(lblComment_1);

        JLabel lblComposer_1 = new JLabel("Composer :");
        lblComposer_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblComposer_1.setBounds(548, 0, 84, 55);
        lblComposer_1.setFont(new Font("Arial", Font.PLAIN, 15));
        panelID3.add(lblComposer_1);

        JLabel lblPublisher_1 = new JLabel("Publisher :");
        lblPublisher_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblPublisher_1.setBounds(632, 0, 84, 55);
        lblPublisher_1.setFont(new Font("Arial", Font.PLAIN, 15));
        panelID3.add(lblPublisher_1);

        JLabel lblOriginalArtist_1 = new JLabel("<HTML>Original<br>Artist :</HTML>");
        lblOriginalArtist_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblOriginalArtist_1.setBounds(716, 0, 84, 55);
        lblOriginalArtist_1.setFont(new Font("Arial", Font.PLAIN, 15));
        panelID3.add(lblOriginalArtist_1);

        JLabel lblAlbumArtist_1 = new JLabel("<HTML>Album<br>Artist :</HTML>");
        lblAlbumArtist_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblAlbumArtist_1.setBounds(800, 0, 84, 55);
        lblAlbumArtist_1.setFont(new Font("Arial", Font.PLAIN, 15));
        panelID3.add(lblAlbumArtist_1);

        AudioSplitter v = new AudioSplitter();

        textTrackNumber = new JTextField();
        textTrackNumber.setHorizontalAlignment(SwingConstants.CENTER);
        textTrackNumber.setBounds(44, 66, 84, 30);
        textTrackNumber.setText("1");
        textTrackNumber.setFont(new Font("Arial", Font.PLAIN, 11));
        panelID3.add(textTrackNumber);
        textTrackNumber.setColumns(20);

        textArtist = new JTextField();
        textArtist.setBounds(128, 66, 84, 30);
        textArtist.setText("");
        textArtist.setFont(new Font("Arial", Font.PLAIN, 11));
        panelID3.add(textArtist);
        textArtist.setColumns(20);

        textTitle = new JTextField();
        textTitle.setBounds(212, 66, 84, 30);
        textTitle.setText("TRACK_1");
        textTitle.setFont(new Font("Arial", Font.PLAIN, 11));
        panelID3.add(textTitle);
        textTitle.setColumns(20);

        textAlbum = new JTextField();
        textAlbum.setBounds(296, 66, 84, 30);
        textAlbum.setText(v.getAlbumName());
        textAlbum.setFont(new Font("Arial", Font.PLAIN, 11));
        panelID3.add(textAlbum);
        textAlbum.setColumns(20);

        textYear = new JTextField();
        textYear.setBounds(380, 66, 84, 30);
        textYear.setText("");
        textYear.setFont(new Font("Arial", Font.PLAIN, 11));
        panelID3.add(textYear);
        textYear.setColumns(20);

        textComment = new JTextField();
        textComment.setBounds(464, 66, 84, 30);
        textComment.setText("");
        textComment.setFont(new Font("Arial", Font.PLAIN, 11));
        panelID3.add(textComment);
        textComment.setColumns(20);

        textComposer = new JTextField();
        textComposer.setBounds(548, 66, 84, 30);
        textComposer.setText("");
        textComposer.setFont(new Font("Arial", Font.PLAIN, 11));
        panelID3.add(textComposer);
        textComposer.setColumns(20);

        textPublisher = new JTextField();
        textPublisher.setBounds(632, 66, 84, 30);
        textPublisher.setText("");
        textPublisher.setFont(new Font("Arial", Font.PLAIN, 11));
        panelID3.add(textPublisher);
        textPublisher.setColumns(20);

        textOriginalArtist = new JTextField();
        textOriginalArtist.setBounds(716, 66, 84, 30);
        textOriginalArtist.setText("");
        textOriginalArtist.setFont(new Font("Arial", Font.PLAIN, 11));
        panelID3.add(textOriginalArtist);
        textOriginalArtist.setColumns(20);

        textAlbumArtist = new JTextField();
        textAlbumArtist.setBounds(800, 66, 84, 30);
        textAlbumArtist.setText("");
        textAlbumArtist.setFont(new Font("Arial", Font.PLAIN, 11));
        panelID3.add(textAlbumArtist);
        textAlbumArtist.setColumns(20);

        textTrackNumber_2 = new JTextField();
        textTrackNumber_2.setFont(new Font("Arial", Font.PLAIN, 11));
        textTrackNumber_2.setText("2");
        textTrackNumber_2.setHorizontalAlignment(SwingConstants.CENTER);
        textTrackNumber_2.setBounds(44, 107, 84, 30);
        panelID3.add(textTrackNumber_2);
        textTrackNumber_2.setColumns(10);

        textArtist_2 = new JTextField();
        textArtist_2.setFont(new Font("Arial", Font.PLAIN, 11));
        textArtist_2.setColumns(10);
        textArtist_2.setBounds(128, 107, 84, 30);
        panelID3.add(textArtist_2);

        textTitle_2 = new JTextField();
        textTitle_2.setFont(new Font("Arial", Font.PLAIN, 11));
        textTitle_2.setColumns(10);
        textTitle_2.setBounds(212, 107, 84, 30);
        textTitle_2.setText("TRACK_2");
        panelID3.add(textTitle_2);

        textAlbum_2 = new JTextField();
        textAlbum_2.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbum_2.setColumns(10);
        textAlbum_2.setBounds(296, 107, 84, 30);
        textAlbum_2.setText(v.getAlbumName());
        panelID3.add(textAlbum_2);

        textYear_2 = new JTextField();
        textYear_2.setFont(new Font("Arial", Font.PLAIN, 11));
        textYear_2.setColumns(10);
        textYear_2.setBounds(380, 107, 84, 30);
        panelID3.add(textYear_2);

        textComment_2 = new JTextField();
        textComment_2.setFont(new Font("Arial", Font.PLAIN, 11));
        textComment_2.setColumns(10);
        textComment_2.setBounds(464, 107, 84, 30);
        panelID3.add(textComment_2);

        textComposer_2 = new JTextField();
        textComposer_2.setFont(new Font("Arial", Font.PLAIN, 11));
        textComposer_2.setColumns(10);
        textComposer_2.setBounds(548, 107, 84, 30);
        panelID3.add(textComposer_2);

        textPublisher_2 = new JTextField();
        textPublisher_2.setFont(new Font("Arial", Font.PLAIN, 11));
        textPublisher_2.setColumns(10);
        textPublisher_2.setBounds(632, 107, 84, 30);
        panelID3.add(textPublisher_2);

        textOriginalArtist_2 = new JTextField();
        textOriginalArtist_2.setFont(new Font("Arial", Font.PLAIN, 11));
        textOriginalArtist_2.setColumns(10);
        textOriginalArtist_2.setBounds(716, 107, 84, 30);
        panelID3.add(textOriginalArtist_2);

        textAlbumArtist_2 = new JTextField();
        textAlbumArtist_2.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbumArtist_2.setColumns(10);
        textAlbumArtist_2.setBounds(800, 107, 84, 30);
        panelID3.add(textAlbumArtist_2);

        textTrackNumber_3 = new JTextField();
        textTrackNumber_3.setFont(new Font("Arial", Font.PLAIN, 11));
        textTrackNumber_3.setText("3");
        textTrackNumber_3.setHorizontalAlignment(SwingConstants.CENTER);
        textTrackNumber_3.setColumns(10);
        textTrackNumber_3.setBounds(44, 148, 84, 30);
        panelID3.add(textTrackNumber_3);

        textArtist_3 = new JTextField();
        textArtist_3.setFont(new Font("Arial", Font.PLAIN, 11));
        textArtist_3.setColumns(10);
        textArtist_3.setBounds(128, 148, 84, 30);
        panelID3.add(textArtist_3);

        textTitle_3 = new JTextField();
        textTitle_3.setFont(new Font("Arial", Font.PLAIN, 11));
        textTitle_3.setColumns(10);
        textTitle_3.setBounds(212, 148, 84, 30);
        textTitle_3.setText("TRACK_3");
        panelID3.add(textTitle_3);

        textAlbum_3 = new JTextField();
        textAlbum_3.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbum_3.setColumns(10);
        textAlbum_3.setBounds(296, 148, 84, 30);
        textAlbum_3.setText(v.getAlbumName());
        panelID3.add(textAlbum_3);

        textYear_3 = new JTextField();
        textYear_3.setFont(new Font("Arial", Font.PLAIN, 11));
        textYear_3.setColumns(10);
        textYear_3.setBounds(380, 148, 84, 30);
        panelID3.add(textYear_3);

        textComment_3 = new JTextField();
        textComment_3.setFont(new Font("Arial", Font.PLAIN, 11));
        textComment_3.setColumns(10);
        textComment_3.setBounds(464, 148, 84, 30);
        panelID3.add(textComment_3);

        textComposer_3 = new JTextField();
        textComposer_3.setFont(new Font("Arial", Font.PLAIN, 11));
        textComposer_3.setColumns(10);
        textComposer_3.setBounds(548, 148, 84, 30);
        panelID3.add(textComposer_3);

        textPublisher_3 = new JTextField();
        textPublisher_3.setFont(new Font("Arial", Font.PLAIN, 11));
        textPublisher_3.setColumns(10);
        textPublisher_3.setBounds(632, 148, 84, 30);
        panelID3.add(textPublisher_3);

        textOriginalArtist_3 = new JTextField();
        textOriginalArtist_3.setFont(new Font("Arial", Font.PLAIN, 11));
        textOriginalArtist_3.setColumns(10);
        textOriginalArtist_3.setBounds(716, 148, 84, 30);
        panelID3.add(textOriginalArtist_3);

        textAlbumArtist_3 = new JTextField();
        textAlbumArtist_3.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbumArtist_3.setColumns(10);
        textAlbumArtist_3.setBounds(800, 148, 84, 30);
        panelID3.add(textAlbumArtist_3);

        textTrackNumber_4 = new JTextField();
        textTrackNumber_4.setFont(new Font("Arial", Font.PLAIN, 11));
        textTrackNumber_4.setText("4");
        textTrackNumber_4.setHorizontalAlignment(SwingConstants.CENTER);
        textTrackNumber_4.setColumns(10);
        textTrackNumber_4.setBounds(44, 189, 84, 30);
        panelID3.add(textTrackNumber_4);

        textArtist_4 = new JTextField();
        textArtist_4.setFont(new Font("Arial", Font.PLAIN, 11));
        textArtist_4.setColumns(10);
        textArtist_4.setBounds(128, 189, 84, 30);
        panelID3.add(textArtist_4);

        textTitle_4 = new JTextField();
        textTitle_4.setFont(new Font("Arial", Font.PLAIN, 11));
        textTitle_4.setColumns(10);
        textTitle_4.setBounds(212, 189, 84, 30);
        textTitle_4.setText("TRACK_4");
        panelID3.add(textTitle_4);

        textAlbum_4 = new JTextField();
        textAlbum_4.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbum_4.setColumns(10);
        textAlbum_4.setBounds(296, 189, 84, 30);
        textAlbum_4.setText(v.getAlbumName());
        panelID3.add(textAlbum_4);

        textYear_4 = new JTextField();
        textYear_4.setFont(new Font("Arial", Font.PLAIN, 11));
        textYear_4.setColumns(10);
        textYear_4.setBounds(380, 189, 84, 30);
        panelID3.add(textYear_4);

        textComment_4 = new JTextField();
        textComment_4.setFont(new Font("Arial", Font.PLAIN, 11));
        textComment_4.setColumns(10);
        textComment_4.setBounds(464, 189, 84, 30);
        panelID3.add(textComment_4);

        textComposer_4 = new JTextField();
        textComposer_4.setFont(new Font("Arial", Font.PLAIN, 11));
        textComposer_4.setColumns(10);
        textComposer_4.setBounds(548, 189, 84, 30);
        panelID3.add(textComposer_4);

        textPublisher_4 = new JTextField();
        textPublisher_4.setFont(new Font("Arial", Font.PLAIN, 11));
        textPublisher_4.setColumns(10);
        textPublisher_4.setBounds(632, 189, 84, 30);
        panelID3.add(textPublisher_4);

        textOriginalArtist_4 = new JTextField();
        textOriginalArtist_4.setFont(new Font("Arial", Font.PLAIN, 11));
        textOriginalArtist_4.setColumns(10);
        textOriginalArtist_4.setBounds(716, 189, 84, 30);
        panelID3.add(textOriginalArtist_4);

        textAlbumArtist_4 = new JTextField();
        textAlbumArtist_4.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbumArtist_4.setColumns(10);
        textAlbumArtist_4.setBounds(800, 189, 84, 30);
        panelID3.add(textAlbumArtist_4);

        textTrackNumber_5 = new JTextField();
        textTrackNumber_5.setFont(new Font("Arial", Font.PLAIN, 11));
        textTrackNumber_5.setText("5");
        textTrackNumber_5.setHorizontalAlignment(SwingConstants.CENTER);
        textTrackNumber_5.setColumns(10);
        textTrackNumber_5.setBounds(44, 230, 84, 30);
        panelID3.add(textTrackNumber_5);

        textArtist_5 = new JTextField();
        textArtist_5.setFont(new Font("Arial", Font.PLAIN, 11));
        textArtist_5.setColumns(10);
        textArtist_5.setBounds(128, 230, 84, 30);
        panelID3.add(textArtist_5);

        textTitle_5 = new JTextField();
        textTitle_5.setFont(new Font("Arial", Font.PLAIN, 11));
        textTitle_5.setColumns(10);
        textTitle_5.setBounds(212, 230, 84, 30);
        textTitle_5.setText("TRACK_5");
        panelID3.add(textTitle_5);

        textAlbum_5 = new JTextField();
        textAlbum_5.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbum_5.setColumns(10);
        textAlbum_5.setBounds(296, 230, 84, 30);
        textAlbum_5.setText(v.getAlbumName());
        panelID3.add(textAlbum_5);

        textYear_5 = new JTextField();
        textYear_5.setFont(new Font("Arial", Font.PLAIN, 11));
        textYear_5.setColumns(10);
        textYear_5.setBounds(380, 230, 84, 30);
        panelID3.add(textYear_5);

        textComment_5 = new JTextField();
        textComment_5.setFont(new Font("Arial", Font.PLAIN, 11));
        textComment_5.setColumns(10);
        textComment_5.setBounds(464, 230, 84, 30);
        panelID3.add(textComment_5);

        textComposer_5 = new JTextField();
        textComposer_5.setFont(new Font("Arial", Font.PLAIN, 11));
        textComposer_5.setColumns(10);
        textComposer_5.setBounds(548, 230, 84, 30);
        panelID3.add(textComposer_5);

        textPublisher_5 = new JTextField();
        textPublisher_5.setFont(new Font("Arial", Font.PLAIN, 11));
        textPublisher_5.setColumns(10);
        textPublisher_5.setBounds(632, 230, 84, 30);
        panelID3.add(textPublisher_5);

        textOriginalArtist_5 = new JTextField();
        textOriginalArtist_5.setFont(new Font("Arial", Font.PLAIN, 11));
        textOriginalArtist_5.setColumns(10);
        textOriginalArtist_5.setBounds(716, 230, 84, 30);
        panelID3.add(textOriginalArtist_5);

        textAlbumArtist_5 = new JTextField();
        textAlbumArtist_5.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbumArtist_5.setColumns(10);
        textAlbumArtist_5.setBounds(800, 230, 84, 30);
        panelID3.add(textAlbumArtist_5);

        textTrackNumber_6 = new JTextField();
        textTrackNumber_6.setFont(new Font("Arial", Font.PLAIN, 11));
        textTrackNumber_6.setText("6");
        textTrackNumber_6.setHorizontalAlignment(SwingConstants.CENTER);
        textTrackNumber_6.setColumns(10);
        textTrackNumber_6.setBounds(44, 271, 84, 30);
        panelID3.add(textTrackNumber_6);

        textArtist_6 = new JTextField();
        textArtist_6.setFont(new Font("Arial", Font.PLAIN, 11));
        textArtist_6.setColumns(10);
        textArtist_6.setBounds(128, 271, 84, 30);
        panelID3.add(textArtist_6);

        textTitle_6 = new JTextField();
        textTitle_6.setFont(new Font("Arial", Font.PLAIN, 11));
        textTitle_6.setColumns(10);
        textTitle_6.setBounds(212, 271, 84, 30);
        textTitle_6.setText("TRACK_6");
        panelID3.add(textTitle_6);

        textAlbum_6 = new JTextField();
        textAlbum_6.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbum_6.setColumns(10);
        textAlbum_6.setBounds(296, 271, 84, 30);
        textAlbum_6.setText(v.getAlbumName());
        panelID3.add(textAlbum_6);

        textYear_6 = new JTextField();
        textYear_6.setFont(new Font("Arial", Font.PLAIN, 11));
        textYear_6.setColumns(10);
        textYear_6.setBounds(380, 271, 84, 30);
        panelID3.add(textYear_6);

        textComment_6 = new JTextField();
        textComment_6.setFont(new Font("Arial", Font.PLAIN, 11));
        textComment_6.setColumns(10);
        textComment_6.setBounds(464, 271, 84, 30);
        panelID3.add(textComment_6);

        textComposer_6 = new JTextField();
        textComposer_6.setFont(new Font("Arial", Font.PLAIN, 11));
        textComposer_6.setColumns(10);
        textComposer_6.setBounds(548, 271, 84, 30);
        panelID3.add(textComposer_6);

        textPublisher_6 = new JTextField();
        textPublisher_6.setFont(new Font("Arial", Font.PLAIN, 11));
        textPublisher_6.setColumns(10);
        textPublisher_6.setBounds(632, 271, 84, 30);
        panelID3.add(textPublisher_6);

        textOriginalArtist_6 = new JTextField();
        textOriginalArtist_6.setFont(new Font("Arial", Font.PLAIN, 11));
        textOriginalArtist_6.setColumns(10);
        textOriginalArtist_6.setBounds(716, 271, 84, 30);
        panelID3.add(textOriginalArtist_6);

        textAlbumArtist_6 = new JTextField();
        textAlbumArtist_6.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbumArtist_6.setColumns(10);
        textAlbumArtist_6.setBounds(800, 271, 84, 30);
        panelID3.add(textAlbumArtist_6);

        textTrackNumber_7 = new JTextField();
        textTrackNumber_7.setFont(new Font("Arial", Font.PLAIN, 11));
        textTrackNumber_7.setText("7");
        textTrackNumber_7.setHorizontalAlignment(SwingConstants.CENTER);
        textTrackNumber_7.setColumns(10);
        textTrackNumber_7.setBounds(44, 312, 84, 30);
        panelID3.add(textTrackNumber_7);

        textArtist_7 = new JTextField();
        textArtist_7.setFont(new Font("Arial", Font.PLAIN, 11));
        textArtist_7.setColumns(10);
        textArtist_7.setBounds(128, 312, 84, 30);
        panelID3.add(textArtist_7);

        textTitle_7 = new JTextField();
        textTitle_7.setFont(new Font("Arial", Font.PLAIN, 11));
        textTitle_7.setColumns(10);
        textTitle_7.setBounds(212, 312, 84, 30);
        textTitle_7.setText("TRACK_7");
        panelID3.add(textTitle_7);

        textAlbum_7 = new JTextField();
        textAlbum_7.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbum_7.setColumns(10);
        textAlbum_7.setBounds(296, 312, 84, 30);
        textAlbum_7.setText(v.getAlbumName());
        panelID3.add(textAlbum_7);

        textYear_7 = new JTextField();
        textYear_7.setFont(new Font("Arial", Font.PLAIN, 11));
        textYear_7.setColumns(10);
        textYear_7.setBounds(380, 312, 84, 30);
        panelID3.add(textYear_7);

        textComment_7 = new JTextField();
        textComment_7.setFont(new Font("Arial", Font.PLAIN, 11));
        textComment_7.setColumns(10);
        textComment_7.setBounds(464, 312, 84, 30);
        panelID3.add(textComment_7);

        textComposer_7 = new JTextField();
        textComposer_7.setFont(new Font("Arial", Font.PLAIN, 11));
        textComposer_7.setColumns(10);
        textComposer_7.setBounds(548, 312, 84, 30);
        panelID3.add(textComposer_7);

        textPublisher_7 = new JTextField();
        textPublisher_7.setFont(new Font("Arial", Font.PLAIN, 11));
        textPublisher_7.setColumns(10);
        textPublisher_7.setBounds(632, 312, 84, 30);
        panelID3.add(textPublisher_7);

        textOriginalArtist_7 = new JTextField();
        textOriginalArtist_7.setFont(new Font("Arial", Font.PLAIN, 11));
        textOriginalArtist_7.setColumns(10);
        textOriginalArtist_7.setBounds(716, 312, 84, 30);
        panelID3.add(textOriginalArtist_7);

        textAlbumArtist_7 = new JTextField();
        textAlbumArtist_7.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbumArtist_7.setColumns(10);
        textAlbumArtist_7.setBounds(800, 312, 84, 30);
        panelID3.add(textAlbumArtist_7);

        textTrackNumber_8 = new JTextField();
        textTrackNumber_8.setFont(new Font("Arial", Font.PLAIN, 11));
        textTrackNumber_8.setText("8");
        textTrackNumber_8.setHorizontalAlignment(SwingConstants.CENTER);
        textTrackNumber_8.setColumns(10);
        textTrackNumber_8.setBounds(44, 353, 84, 30);
        panelID3.add(textTrackNumber_8);

        textArtist_8 = new JTextField();
        textArtist_8.setFont(new Font("Arial", Font.PLAIN, 11));
        textArtist_8.setColumns(10);
        textArtist_8.setBounds(128, 353, 84, 30);
        panelID3.add(textArtist_8);

        textTitle_8 = new JTextField();
        textTitle_8.setFont(new Font("Arial", Font.PLAIN, 11));
        textTitle_8.setColumns(10);
        textTitle_8.setBounds(212, 353, 84, 30);
        textTitle_8.setText("TRACK_8");
        panelID3.add(textTitle_8);

        textAlbum_8 = new JTextField();
        textAlbum_8.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbum_8.setColumns(10);
        textAlbum_8.setBounds(296, 353, 84, 30);
        textAlbum_8.setText(v.getAlbumName());
        panelID3.add(textAlbum_8);

        textYear_8 = new JTextField();
        textYear_8.setFont(new Font("Arial", Font.PLAIN, 11));
        textYear_8.setColumns(10);
        textYear_8.setBounds(380, 353, 84, 30);
        panelID3.add(textYear_8);

        textComment_8 = new JTextField();
        textComment_8.setFont(new Font("Arial", Font.PLAIN, 11));
        textComment_8.setColumns(10);
        textComment_8.setBounds(464, 353, 84, 30);
        panelID3.add(textComment_8);

        textComposer_8 = new JTextField();
        textComposer_8.setFont(new Font("Arial", Font.PLAIN, 11));
        textComposer_8.setColumns(10);
        textComposer_8.setBounds(548, 353, 84, 30);
        panelID3.add(textComposer_8);

        textPublisher_8 = new JTextField();
        textPublisher_8.setFont(new Font("Arial", Font.PLAIN, 11));
        textPublisher_8.setColumns(10);
        textPublisher_8.setBounds(632, 353, 84, 30);
        panelID3.add(textPublisher_8);

        textOriginalArtist_8 = new JTextField();
        textOriginalArtist_8.setFont(new Font("Arial", Font.PLAIN, 11));
        textOriginalArtist_8.setColumns(10);
        textOriginalArtist_8.setBounds(716, 353, 84, 30);
        panelID3.add(textOriginalArtist_8);

        textAlbumArtist_8 = new JTextField();
        textAlbumArtist_8.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbumArtist_8.setColumns(10);
        textAlbumArtist_8.setBounds(800, 353, 84, 30);
        panelID3.add(textAlbumArtist_8);

        textTrackNumber_9 = new JTextField();
        textTrackNumber_9.setFont(new Font("Arial", Font.PLAIN, 11));
        textTrackNumber_9.setText("9");
        textTrackNumber_9.setHorizontalAlignment(SwingConstants.CENTER);
        textTrackNumber_9.setColumns(10);
        textTrackNumber_9.setBounds(44, 394, 84, 30);
        panelID3.add(textTrackNumber_9);

        textArtist_9 = new JTextField();
        textArtist_9.setFont(new Font("Arial", Font.PLAIN, 11));
        textArtist_9.setColumns(10);
        textArtist_9.setBounds(128, 394, 84, 30);
        panelID3.add(textArtist_9);

        textTitle_9 = new JTextField();
        textTitle_9.setFont(new Font("Arial", Font.PLAIN, 11));
        textTitle_9.setColumns(10);
        textTitle_9.setBounds(212, 394, 84, 30);
        textTitle_9.setText("TRACK_9");
        panelID3.add(textTitle_9);

        textAlbum_9 = new JTextField();
        textAlbum_9.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbum_9.setColumns(10);
        textAlbum_9.setBounds(296, 394, 84, 30);
        textAlbum_9.setText(v.getAlbumName());
        panelID3.add(textAlbum_9);

        textYear_9 = new JTextField();
        textYear_9.setFont(new Font("Arial", Font.PLAIN, 11));
        textYear_9.setColumns(10);
        textYear_9.setBounds(380, 394, 84, 30);
        panelID3.add(textYear_9);

        textComment_9 = new JTextField();
        textComment_9.setFont(new Font("Arial", Font.PLAIN, 11));
        textComment_9.setColumns(10);
        textComment_9.setBounds(464, 394, 84, 30);
        panelID3.add(textComment_9);

        textComposer_9 = new JTextField();
        textComposer_9.setFont(new Font("Arial", Font.PLAIN, 11));
        textComposer_9.setColumns(10);
        textComposer_9.setBounds(548, 394, 84, 30);
        panelID3.add(textComposer_9);

        textPublisher_9 = new JTextField();
        textPublisher_9.setFont(new Font("Arial", Font.PLAIN, 11));
        textPublisher_9.setColumns(10);
        textPublisher_9.setBounds(632, 394, 84, 30);
        panelID3.add(textPublisher_9);

        textOriginalArtist_9 = new JTextField();
        textOriginalArtist_9.setFont(new Font("Arial", Font.PLAIN, 11));
        textOriginalArtist_9.setColumns(10);
        textOriginalArtist_9.setBounds(716, 394, 84, 30);
        panelID3.add(textOriginalArtist_9);

        textAlbumArtist_9 = new JTextField();
        textAlbumArtist_9.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbumArtist_9.setColumns(10);
        textAlbumArtist_9.setBounds(800, 394, 84, 30);
        panelID3.add(textAlbumArtist_9);

        textTrackNumber_10 = new JTextField();
        textTrackNumber_10.setFont(new Font("Arial", Font.PLAIN, 11));
        textTrackNumber_10.setText("10");
        textTrackNumber_10.setHorizontalAlignment(SwingConstants.CENTER);
        textTrackNumber_10.setColumns(10);
        textTrackNumber_10.setBounds(44, 435, 84, 30);
        panelID3.add(textTrackNumber_10);

        textArtist_10 = new JTextField();
        textArtist_10.setFont(new Font("Arial", Font.PLAIN, 11));
        textArtist_10.setColumns(10);
        textArtist_10.setBounds(128, 435, 84, 30);
        panelID3.add(textArtist_10);

        textTitle_10 = new JTextField();
        textTitle_10.setFont(new Font("Arial", Font.PLAIN, 11));
        textTitle_10.setColumns(10);
        textTitle_10.setBounds(212, 435, 84, 30);
        textTitle_10.setText("TRACK_10");
        panelID3.add(textTitle_10);

        textAlbum_10 = new JTextField();
        textAlbum_10.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbum_10.setColumns(10);
        textAlbum_10.setBounds(296, 435, 84, 30);
        textAlbum_10.setText(v.getAlbumName());
        panelID3.add(textAlbum_10);

        textYear_10 = new JTextField();
        textYear_10.setFont(new Font("Arial", Font.PLAIN, 11));
        textYear_10.setColumns(10);
        textYear_10.setBounds(380, 435, 84, 30);
        panelID3.add(textYear_10);

        textComment_10 = new JTextField();
        textComment_10.setFont(new Font("Arial", Font.PLAIN, 11));
        textComment_10.setColumns(10);
        textComment_10.setBounds(464, 435, 84, 30);
        panelID3.add(textComment_10);

        textComposer_10 = new JTextField();
        textComposer_10.setFont(new Font("Arial", Font.PLAIN, 11));
        textComposer_10.setColumns(10);
        textComposer_10.setBounds(548, 435, 84, 30);
        panelID3.add(textComposer_10);

        textPublisher_10 = new JTextField();
        textPublisher_10.setFont(new Font("Arial", Font.PLAIN, 11));
        textPublisher_10.setColumns(10);
        textPublisher_10.setBounds(632, 435, 84, 30);
        panelID3.add(textPublisher_10);

        textOriginalArtist_10 = new JTextField();
        textOriginalArtist_10.setFont(new Font("Arial", Font.PLAIN, 11));
        textOriginalArtist_10.setColumns(10);
        textOriginalArtist_10.setBounds(716, 435, 84, 30);
        panelID3.add(textOriginalArtist_10);

        textAlbumArtist_10 = new JTextField();
        textAlbumArtist_10.setFont(new Font("Arial", Font.PLAIN, 11));
        textAlbumArtist_10.setColumns(10);
        textAlbumArtist_10.setBounds(800, 435, 84, 30);
        panelID3.add(textAlbumArtist_10);



        tracksRecorded = new JLabel("Tracks Recorded: " + trackQuantity);
        tracksRecorded.setBounds(44, 482, 118, 14);
        panelID3.add(tracksRecorded);

        JButton CopyTags = new JButton("Copy");
        CopyTags.setBounds(291, 478, 89, 23);
        panelID3.add(CopyTags);

        playTrack_1 = new JButton("");
        playTrack_1.setIcon(new ImageIcon(ID3tags.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
        playTrack_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_1.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_1.mp3";
                }
                disablePlay();
                stopTrack_1.setEnabled(true);
                playMusic(playTrack);
            }
        });
        playTrack_1.setBounds(894, 65, 30, 31);
        panelID3.add(playTrack_1);

        playTrack_2 = new JButton("");
        playTrack_2.setIcon(new ImageIcon(ID3tags.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
        playTrack_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle_2.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_2.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_2.mp3";
                }
                disablePlay();
                stopTrack_2.setEnabled(true);
                playMusic(playTrack);
            }
        });
        playTrack_2.setBounds(894, 107, 30, 31);
        panelID3.add(playTrack_2);

        playTrack_3 = new JButton("");
        playTrack_3.setIcon(new ImageIcon(ID3tags.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
        playTrack_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle_3.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_3.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_3.mp3";
                }
                disablePlay();
                stopTrack_3.setEnabled(true);
                playMusic(playTrack);
            }
        });
        playTrack_3.setBounds(894, 147, 30, 31);
        panelID3.add(playTrack_3);

        playTrack_4 = new JButton("");
        playTrack_4.setIcon(new ImageIcon(ID3tags.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
        playTrack_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle_4.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_4.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_4.mp3";
                }
                disablePlay();
                stopTrack_4.setEnabled(true);
                playMusic(playTrack);
            }
        });
        playTrack_4.setBounds(894, 189, 30, 31);
        panelID3.add(playTrack_4);

        playTrack_5 = new JButton("");
        playTrack_5.setIcon(new ImageIcon(ID3tags.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
        playTrack_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle_5.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_5.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_5.mp3";
                }
                disablePlay();
                stopTrack_5.setEnabled(true);
                playMusic(playTrack);
            }
        });
        playTrack_5.setBounds(894, 230, 30, 31);
        panelID3.add(playTrack_5);

        playTrack_6 = new JButton("");
        playTrack_6.setIcon(new ImageIcon(ID3tags.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
        playTrack_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle_6.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_6.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_6.mp3";
                }
                disablePlay();
                stopTrack_6.setEnabled(true);
                playMusic(playTrack);
            }
        });
        playTrack_6.setBounds(894, 271, 30, 31);
        panelID3.add(playTrack_6);

        playTrack_7 = new JButton("");
        playTrack_7.setIcon(new ImageIcon(ID3tags.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
        playTrack_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle_7.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_7.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_7.mp3";
                }
                disablePlay();
                stopTrack_7.setEnabled(true);
                playMusic(playTrack);
            }
        });
        playTrack_7.setBounds(894, 312, 30, 31);
        panelID3.add(playTrack_7);

        playTrack_8 = new JButton("");
        playTrack_8.setIcon(new ImageIcon(ID3tags.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
        playTrack_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle_8.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_8.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_8.mp3";
                }
                disablePlay();
                stopTrack_8.setEnabled(true);
                playMusic(playTrack);
            }
        });
        playTrack_8.setBounds(894, 353, 30, 31);
        panelID3.add(playTrack_8);

        playTrack_9 = new JButton("");
        playTrack_9.setIcon(new ImageIcon(ID3tags.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
        playTrack_9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle_9.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_9.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_9.mp3";
                }
                disablePlay();
                stopTrack_9.setEnabled(true);
                playMusic(playTrack);
            }
        });
        playTrack_9.setBounds(894, 394, 30, 31);
        panelID3.add(playTrack_9);

        playTrack_10 = new JButton("");
        playTrack_10.setIcon(new ImageIcon(ID3tags.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
        playTrack_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle_10.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_10.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_10.mp3";
                }
                disablePlay();
                stopTrack_10.setEnabled(true);
                playMusic(playTrack);
            }
        });
        playTrack_10.setBounds(894, 435, 30, 31);
        panelID3.add(playTrack_10);

        JLabel lblRefreshInfo = new JLabel("Tracks Selected: 0");
        lblRefreshInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblRefreshInfo.setBounds(652, 474, 183, 30);
        panelID3.add(lblRefreshInfo);

        track1chk = new JCheckBox("");
        track1chk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (track1chk.isSelected()) {
                    tracksSelected += 1;
                }else {
                    tracksSelected -= 1;
                }
                lblRefreshInfo.setText("Tracks Selected: " + tracksSelected);
            }
        });
        track1chk.setBounds(6, 69, 21, 23);
        panelID3.add(track1chk);

        track2chk = new JCheckBox("");
        track2chk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (track2chk.isSelected()) {
                    tracksSelected += 1;
                }else {
                    tracksSelected -= 1;
                }
                lblRefreshInfo.setText("Tracks Selected: " + tracksSelected);
            }
        });
        track2chk.setBounds(6, 110, 21, 23);
        panelID3.add(track2chk);

        track3chk = new JCheckBox("");
        track3chk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (track3chk.isSelected()) {
                    tracksSelected += 1;
                }else {
                    tracksSelected -= 1;
                }
                lblRefreshInfo.setText("Tracks Selected: " + tracksSelected);
            }
        });
        track3chk.setBounds(6, 151, 21, 23);
        panelID3.add(track3chk);

        track4chk = new JCheckBox("");
        track4chk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (track4chk.isSelected()) {
                    tracksSelected += 1;
                }else {
                    tracksSelected -= 1;
                }
                lblRefreshInfo.setText("Tracks Selected: " + tracksSelected);
            }
        });
        track4chk.setBounds(6, 192, 21, 23);
        panelID3.add(track4chk);

        track5chk = new JCheckBox("");
        track5chk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (track5chk.isSelected()) {
                    tracksSelected += 1;
                }else {
                    tracksSelected -= 1;
                }
                lblRefreshInfo.setText("Tracks Selected: " + tracksSelected);
            }
        });
        track5chk.setBounds(6, 233, 21, 23);
        panelID3.add(track5chk);

        track6chk = new JCheckBox("");
        track6chk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (track6chk.isSelected()) {
                    tracksSelected += 1;
                }else {
                    tracksSelected -= 1;
                }
                lblRefreshInfo.setText("Tracks Selected: " + tracksSelected);
            }
        });
        track6chk.setBounds(6, 274, 21, 23);
        panelID3.add(track6chk);

        track7chk = new JCheckBox("");
        track7chk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (track7chk.isSelected()) {
                    tracksSelected += 1;
                }else {
                    tracksSelected -= 1;
                }
                lblRefreshInfo.setText("Tracks Selected: " + tracksSelected);
            }
        });
        track7chk.setBounds(6, 315, 21, 23);
        panelID3.add(track7chk);

        track8chk = new JCheckBox("");
        track8chk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (track8chk.isSelected()) {
                    tracksSelected += 1;
                }else {
                    tracksSelected -= 1;
                }
                lblRefreshInfo.setText("Tracks Selected: " + tracksSelected);
            }
        });
        track8chk.setBounds(6, 356, 21, 23);
        panelID3.add(track8chk);

        track9chk = new JCheckBox("");
        track9chk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (track9chk.isSelected()) {
                    tracksSelected += 1;
                }else {
                    tracksSelected -= 1;
                }
                lblRefreshInfo.setText("Tracks Selected: " + tracksSelected);
            }
        });
        track9chk.setBounds(6, 397, 21, 23);
        panelID3.add(track9chk);

        track10chk = new JCheckBox("");
        track10chk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (track10chk.isSelected()) {
                    tracksSelected += 1;
                }else {
                    tracksSelected -= 1;
                }
                lblRefreshInfo.setText("Tracks Selected: " + tracksSelected);
            }
        });
        track10chk.setBounds(6, 438, 21, 23);
        panelID3.add(track10chk);

        AudioSplitter s = new AudioSplitter();
        File directory = new File(s.getAlbumName());
        trackQuantity = directory.list().length;
        for (int count = trackQuantity + 1; count <= 10; count++) {
            switch (count)
            {

                case 2:
                    textTrackNumber_2.setEnabled(false);
                    textArtist_2.setEnabled(false);
                    textTitle_2.setEnabled(false);
                    textAlbum_2.setEnabled(false);
                    textYear_2.setEnabled(false);
                    textComment_2.setEnabled(false);
                    textComposer_2.setEnabled(false);
                    textPublisher_2.setEnabled(false);
                    textOriginalArtist_2.setEnabled(false);
                    textAlbumArtist_2.setEnabled(false);
                    playTrack_2.setEnabled(false);
                    track2chk.setEnabled(false);
                    break;

                case 3:
                    textTrackNumber_3.setEnabled(false);
                    textArtist_3.setEnabled(false);
                    textTitle_3.setEnabled(false);
                    textAlbum_3.setEnabled(false);
                    textYear_3.setEnabled(false);
                    textComment_3.setEnabled(false);
                    textComposer_3.setEnabled(false);
                    textPublisher_3.setEnabled(false);
                    textOriginalArtist_3.setEnabled(false);
                    textAlbumArtist_3.setEnabled(false);
                    playTrack_3.setEnabled(false);
                    track3chk.setEnabled(false);
                    break;

                case 4:
                    textTrackNumber_4.setEnabled(false);
                    textArtist_4.setEnabled(false);
                    textTitle_4.setEnabled(false);
                    textAlbum_4.setEnabled(false);
                    textYear_4.setEnabled(false);
                    textComment_4.setEnabled(false);
                    textComposer_4.setEnabled(false);
                    textPublisher_4.setEnabled(false);
                    textOriginalArtist_4.setEnabled(false);
                    textAlbumArtist_4.setEnabled(false);
                    playTrack_4.setEnabled(false);
                    track4chk.setEnabled(false);
                    break;

                case 5:
                    textTrackNumber_5.setEnabled(false);
                    textArtist_5.setEnabled(false);
                    textTitle_5.setEnabled(false);
                    textAlbum_5.setEnabled(false);
                    textYear_5.setEnabled(false);
                    textComment_5.setEnabled(false);
                    textComposer_5.setEnabled(false);
                    textPublisher_5.setEnabled(false);
                    textOriginalArtist_5.setEnabled(false);
                    textAlbumArtist_5.setEnabled(false);
                    playTrack_5.setEnabled(false);
                    track5chk.setEnabled(false);
                    break;

                case 6:
                    textTrackNumber_6.setEnabled(false);
                    textArtist_6.setEnabled(false);
                    textTitle_6.setEnabled(false);
                    textAlbum_6.setEnabled(false);
                    textYear_6.setEnabled(false);
                    textComment_6.setEnabled(false);
                    textComposer_6.setEnabled(false);
                    textPublisher_6.setEnabled(false);
                    textOriginalArtist_6.setEnabled(false);
                    textAlbumArtist_6.setEnabled(false);
                    playTrack_6.setEnabled(false);
                    track6chk.setEnabled(false);
                    break;

                case 7:
                    textTrackNumber_7.setEnabled(false);
                    textArtist_7.setEnabled(false);
                    textTitle_7.setEnabled(false);
                    textAlbum_7.setEnabled(false);
                    textYear_7.setEnabled(false);
                    textComment_7.setEnabled(false);
                    textComposer_7.setEnabled(false);
                    textPublisher_7.setEnabled(false);
                    textOriginalArtist_7.setEnabled(false);
                    textAlbumArtist_7.setEnabled(false);
                    playTrack_7.setEnabled(false);
                    track7chk.setEnabled(false);
                    break;

                case 8:
                    textTrackNumber_8.setEnabled(false);
                    textArtist_8.setEnabled(false);
                    textTitle_8.setEnabled(false);
                    textAlbum_8.setEnabled(false);
                    textYear_8.setEnabled(false);
                    textComment_8.setEnabled(false);
                    textComposer_8.setEnabled(false);
                    textPublisher_8.setEnabled(false);
                    textOriginalArtist_8.setEnabled(false);
                    textAlbumArtist_8.setEnabled(false);
                    playTrack_8.setEnabled(false);
                    track8chk.setEnabled(false);
                    break;

                case 9:
                    textTrackNumber_9.setEnabled(false);
                    textArtist_9.setEnabled(false);
                    textTitle_9.setEnabled(false);
                    textAlbum_9.setEnabled(false);
                    textYear_9.setEnabled(false);
                    textComment_9.setEnabled(false);
                    textComposer_9.setEnabled(false);
                    textPublisher_9.setEnabled(false);
                    textOriginalArtist_9.setEnabled(false);
                    textAlbumArtist_9.setEnabled(false);
                    playTrack_9.setEnabled(false);
                    track9chk.setEnabled(false);
                    break;

                case 10:
                    textTrackNumber_10.setEnabled(false);
                    textArtist_10.setEnabled(false);
                    textTitle_10.setEnabled(false);
                    textAlbum_10.setEnabled(false);
                    textYear_10.setEnabled(false);
                    textComment_10.setEnabled(false);
                    textComposer_10.setEnabled(false);
                    textPublisher_10.setEnabled(false);
                    textOriginalArtist_10.setEnabled(false);
                    textAlbumArtist_10.setEnabled(false);
                    playTrack_10.setEnabled(false);
                    track10chk.setEnabled(false);
                    break;

                default:
                    break;
            }
        }

        JButton SaveTags = new JButton("Save");
        SaveTags.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    File f = new File("ID3tag.txt");
                    if(f.exists() && !f.isDirectory()) {

                    }
                    else{
                        firstTag = true;
                    }

                    for (trackPosition = 1; trackPosition <= trackQuantity; trackPosition++) {
                        addID3tags();
                    }
                    //setID3tag(textTrackNumber.getText(),textArtist.getText(),textAlbum.getText(),textYear.getText(),textComposer.getText(),textPublisher.getText(),textOriginalArtist.getText(),textAlbumArtist.getText());
                    //if(firstTag == true){
                    //	saveID3tag(textArtist.getText(),textAlbum.getText(),textYear.getText(),textComposer.getText(),textPublisher.getText(),textOriginalArtist.getText(),textAlbumArtist.getText());
                    //	firstTag = false;
                    //}
                    JOptionPane.showMessageDialog(null, "Successful!", "Message", JOptionPane.INFORMATION_MESSAGE);

                } catch (UnsupportedTagException | InvalidDataException | NotSupportedException | IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        SaveTags.setBounds(548, 478, 89, 23);
        panelID3.add(SaveTags);

        //MergeTracks merge = new MergeTracks(this);
        JButton btnMergeTracks = new JButton("Merge Tracks");
        btnMergeTracks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (track1chk.isSelected()) {
                    combine(1);
                } else if (track2chk.isSelected()) {
                    combine(2);
                } else if (track3chk.isSelected()) {
                    combine(3);
                } else if (track4chk.isSelected()) {
                    combine(4);
                } else if (track5chk.isSelected()) {
                    combine(5);
                } else if (track6chk.isSelected()) {
                    combine(6);
                } else if (track7chk.isSelected()) {
                    combine(7);
                } else if (track8chk.isSelected()) {
                    combine(8);
                } else if (track9chk.isSelected()) {
                    combine(9);
                }

                track1chk.setSelected(false);
                track2chk.setSelected(false);
                track3chk.setSelected(false);
                track4chk.setSelected(false);
                track5chk.setSelected(false);
                track6chk.setSelected(false);
                track7chk.setSelected(false);
                track8chk.setSelected(false);
                track9chk.setSelected(false);
                track10chk.setSelected(false);
                lblRefreshInfo.setText("Tracks Selected: 0");
                refresh();
                JOptionPane.showMessageDialog(null, "Merging Completed", "Message", JOptionPane.INFORMATION_MESSAGE);
                //lblMergeSuccess.setText("Success!");
                //lblMergeSuccess.setForeground(Color.BLUE);
            }
        });
        btnMergeTracks.setBounds(380, 478, 168, 23);
        panelID3.add(btnMergeTracks);


        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
        btnRefresh.setBounds(835, 478, 89, 23);
        panelID3.add(btnRefresh);

        stopTrack_1 = new JButton("");
        stopTrack_1.setEnabled(false);
        stopTrack_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_1.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_1.mp3";
                }
                stopTrack_1.setEnabled(false);
                enablePlay();
                playMusic(playTrack);
            }
        });
        stopTrack_1.setBounds(934, 65, 30, 31);
        panelID3.add(stopTrack_1);

        stopTrack_2 = new JButton("");
        stopTrack_2.setEnabled(false);
        stopTrack_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_1.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_1.mp3";
                }
                stopTrack_2.setEnabled(false);
                enablePlay();
                playMusic(playTrack);
            }
        });
        stopTrack_2.setBounds(934, 106, 30, 31);
        panelID3.add(stopTrack_2);

        stopTrack_3 = new JButton("");
        stopTrack_3.setEnabled(false);
        stopTrack_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_1.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_1.mp3";
                }
                stopTrack_3.setEnabled(false);
                enablePlay();
                playMusic(playTrack);
            }
        });
        stopTrack_3.setBounds(934, 147, 30, 31);
        panelID3.add(stopTrack_3);

        stopTrack_4 = new JButton("");
        stopTrack_4.setEnabled(false);
        stopTrack_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_1.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_1.mp3";
                }
                stopTrack_4.setEnabled(false);
                enablePlay();
                playMusic(playTrack);
            }
        });
        stopTrack_4.setBounds(934, 188, 30, 31);
        panelID3.add(stopTrack_4);

        stopTrack_5 = new JButton("");
        stopTrack_5.setEnabled(false);
        stopTrack_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_1.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_1.mp3";
                }
                stopTrack_5.setEnabled(false);
                enablePlay();
                playMusic(playTrack);
            }
        });
        stopTrack_5.setBounds(934, 230, 30, 31);
        panelID3.add(stopTrack_5);

        stopTrack_6 = new JButton("");
        stopTrack_6.setEnabled(false);
        stopTrack_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_1.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_1.mp3";
                }
                stopTrack_6.setEnabled(false);
                enablePlay();
                playMusic(playTrack);
            }
        });
        stopTrack_6.setBounds(934, 270, 30, 31);
        panelID3.add(stopTrack_6);

        stopTrack_7 = new JButton("");
        stopTrack_7.setEnabled(false);
        stopTrack_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_1.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_1.mp3";
                }
                stopTrack_7.setEnabled(false);
                enablePlay();
                playMusic(playTrack);
            }
        });
        stopTrack_7.setBounds(934, 312, 30, 31);
        panelID3.add(stopTrack_7);

        stopTrack_8 = new JButton("");
        stopTrack_8.setEnabled(false);
        stopTrack_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_1.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_1.mp3";
                }
                stopTrack_8.setEnabled(false);
                enablePlay();
                playMusic(playTrack);
            }
        });
        stopTrack_8.setBounds(934, 353, 30, 31);
        panelID3.add(stopTrack_8);

        stopTrack_9 = new JButton("");
        stopTrack_9.setEnabled(false);
        stopTrack_9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_1.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_1.mp3";
                }
                stopTrack_9.setEnabled(false);
                enablePlay();
                playMusic(playTrack);
            }
        });
        stopTrack_9.setBounds(934, 394, 30, 31);
        panelID3.add(stopTrack_9);

        stopTrack_10 = new JButton("");
        stopTrack_10.setEnabled(false);
        stopTrack_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = v.getAlbumName() + "\\" + textTitle.getText() + ".mp3";
                if (playTrack != v.getAlbumName() + "\\TRACK_1.mp3") {
                    playTrack = v.getAlbumName() + "\\TRACK_1.mp3";
                }
                stopTrack_10.setEnabled(false);
                enablePlay();
                playMusic(playTrack);
            }
        });
        stopTrack_10.setBounds(934, 435, 30, 31);
        panelID3.add(stopTrack_10);


        CopyTags.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int count = 1; count <= trackQuantity; count++) {
                    switch (count)
                    {

                        case 2:
                            textArtist_2.setText(textArtist.getText());
                            textAlbum_2.setText(textAlbum.getText());
                            textYear_2.setText(textYear.getText());
                            textComposer_2.setText(textComposer.getText());
                            textPublisher_2.setText(textPublisher.getText());
                            textOriginalArtist_2.setText(textOriginalArtist.getText());
                            textAlbumArtist_2.setText(textAlbumArtist.getText());
                            break;

                        case 3:
                            textArtist_3.setText(textArtist.getText());
                            textAlbum_3.setText(textAlbum.getText());
                            textYear_3.setText(textYear.getText());
                            textComposer_3.setText(textComposer.getText());
                            textPublisher_3.setText(textPublisher.getText());
                            textOriginalArtist_3.setText(textOriginalArtist.getText());
                            textAlbumArtist_3.setText(textAlbumArtist.getText());
                            break;

                        case 4:
                            textArtist_4.setText(textArtist.getText());
                            textAlbum_4.setText(textAlbum.getText());
                            textYear_4.setText(textYear.getText());
                            textComposer_4.setText(textComposer.getText());
                            textPublisher_4.setText(textPublisher.getText());
                            textOriginalArtist_4.setText(textOriginalArtist.getText());
                            textAlbumArtist_4.setText(textAlbumArtist.getText());
                            break;

                        case 5:
                            textArtist_5.setText(textArtist.getText());
                            textAlbum_5.setText(textAlbum.getText());
                            textYear_5.setText(textYear.getText());
                            textComposer_5.setText(textComposer.getText());
                            textPublisher_5.setText(textPublisher.getText());
                            textOriginalArtist_5.setText(textOriginalArtist.getText());
                            textAlbumArtist_5.setText(textAlbumArtist.getText());
                            break;

                        case 6:
                            textArtist_6.setText(textArtist.getText());
                            textAlbum_6.setText(textAlbum.getText());
                            textYear_6.setText(textYear.getText());
                            textComposer_6.setText(textComposer.getText());
                            textPublisher_6.setText(textPublisher.getText());
                            textOriginalArtist_6.setText(textOriginalArtist.getText());
                            textAlbumArtist_6.setText(textAlbumArtist.getText());
                            break;

                        case 7:
                            textArtist_7.setText(textArtist.getText());
                            textAlbum_7.setText(textAlbum.getText());
                            textYear_7.setText(textYear.getText());
                            textComposer_7.setText(textComposer.getText());
                            textPublisher_7.setText(textPublisher.getText());
                            textOriginalArtist_7.setText(textOriginalArtist.getText());
                            textAlbumArtist_7.setText(textAlbumArtist.getText());
                            break;

                        case 8:
                            textArtist_8.setText(textArtist.getText());
                            textAlbum_8.setText(textAlbum.getText());
                            textYear_8.setText(textYear.getText());
                            textComposer_8.setText(textComposer.getText());
                            textPublisher_8.setText(textPublisher.getText());
                            textOriginalArtist_8.setText(textOriginalArtist.getText());
                            textAlbumArtist_8.setText(textAlbumArtist.getText());
                            break;

                        case 9:
                            textArtist_9.setText(textArtist.getText());
                            textAlbum_9.setText(textAlbum.getText());
                            textYear_9.setText(textYear.getText());
                            textComposer_9.setText(textComposer.getText());
                            textPublisher_9.setText(textPublisher.getText());
                            textOriginalArtist_9.setText(textOriginalArtist.getText());
                            textAlbumArtist_9.setText(textAlbumArtist.getText());
                            break;

                        case 10:
                            textArtist_10.setText(textArtist.getText());
                            textAlbum_10.setText(textAlbum.getText());
                            textYear_10.setText(textYear.getText());
                            textComposer_10.setText(textComposer.getText());
                            textPublisher_10.setText(textPublisher.getText());
                            textOriginalArtist_10.setText(textOriginalArtist.getText());
                            textAlbumArtist_10.setText(textAlbumArtist.getText());
                            break;

                        default:
                            break;

                    }
                }
            }
        });

        btnPreviousTrack = new JButton("Previous Track");
        btnPreviousTrack.setFont(new Font("Arial", Font.PLAIN, 20));
        btnPreviousTrack.setEnabled(false);
        btnPreviousTrack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                btnNextTrack.setEnabled(true);

                the_trackNumber -= 1;

                if(firstTag==false) {
                    copyID3tag();
                }

                trackPosition -= 1;

                if(trackPosition == 1) {
                    btnPreviousTrack.setEnabled(false);
                    btnLoadID3tag.setEnabled(true);
                }
                else{
                    btnLoadID3tag.setEnabled(false);
                }


                lblOutputTrackNumber.setText("This is Track " + trackPosition);
            }
        });
        //panelID3.add(btnPreviousTrack);

        btnNextTrack = new JButton("Next Track");
        btnNextTrack.setFont(new Font("Arial", Font.PLAIN, 20));
        if(trackQuantity == 1){
            btnNextTrack.setEnabled(false);
        }
        else {
            btnNextTrack.setEnabled(true);
        }
        btnNextTrack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                btnPreviousTrack.setEnabled(true);

                the_trackNumber += 1;

                if(firstTag==false) {
                    copyID3tag();
                }

                trackPosition += 1;

                if(trackPosition == trackQuantity) {
                    btnNextTrack.setEnabled(false);
                }

                if(trackPosition == 1) {
                    btnLoadID3tag.setEnabled(true);
                }
                else{
                    btnLoadID3tag.setEnabled(false);
                }

                lblOutputTrackNumber.setText("This is Track_" + trackPosition);
            }
        });
        //panelID3.add(btnNextTrack);

        btnSaveID3tag = new JButton("Add ID3 tag");
        btnSaveID3tag.setFont(new Font("Arial", Font.PLAIN, 20));
        btnSaveID3tag.setEnabled(true);
        btnSaveID3tag.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {

                    File f = new File("ID3tag.txt");
                    if(f.exists() && !f.isDirectory()) {

                    }
                    else{
                        firstTag = true;
                    }

                    addID3tags();
                    setID3tag(textTrackNumber.getText(),textArtist.getText(),textAlbum.getText(),textYear.getText(),textComposer.getText(),textPublisher.getText(),textOriginalArtist.getText(),textAlbumArtist.getText());
                    if(firstTag == true){
                        saveID3tag(textArtist.getText(),textAlbum.getText(),textYear.getText(),textComposer.getText(),textPublisher.getText(),textOriginalArtist.getText(),textAlbumArtist.getText());
                        firstTag = false;
                    }
                    JOptionPane.showMessageDialog(null, "Successful! \n Reminder: The content of the saved file is fixed. If you want to update the content of the file, please delete the ID3tag.txt file before you want to save a new one.", "Message", JOptionPane.INFORMATION_MESSAGE);

                } catch (UnsupportedTagException | InvalidDataException | NotSupportedException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        //panelID3.add(btnSaveID3tag);

        btnLoadID3tag = new JButton("Load ID3 tag");
        btnLoadID3tag.setFont(new Font("Arial", Font.PLAIN, 20));
        btnLoadID3tag.setEnabled(true);
        btnLoadID3tag.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                loadID3tag();
            }
        });
        ///panelID3.add(btnLoadID3tag);

    }

    public int getTrackNumber(){
        return the_trackNumber;
    }

    public void addID3tags() throws UnsupportedTagException, InvalidDataException, IOException, NotSupportedException {
//		Mp3File mp3file = new Mp3File("output\\TRACK_" + trackPosition + ".mp3");
        AudioSplitter s = new AudioSplitter();
        Mp3File mp3file = new Mp3File(s.getAlbumName() + "/TRACK_" + trackPosition + ".mp3");
        ID3v2 id3v2Tag;
        if (mp3file.hasId3v2Tag()) {
//		  id3v2Tag = mp3file.getId3v2Tag();
            id3v2Tag = new ID3v23Tag();
            mp3file.setId3v2Tag(id3v2Tag);
        }
        else {
            // mp3 does not have an ID3v2 tag, let's create one..
            id3v2Tag = new ID3v23Tag();
            mp3file.setId3v2Tag(id3v2Tag);
        }
        switch (trackPosition)
        {

            case 1:
                id3v2Tag.setTrack(textTrackNumber.getText());
                id3v2Tag.setArtist(textArtist.getText());
                id3v2Tag.setTitle(textTitle.getText());
                id3v2Tag.setAlbum(textAlbum.getText());
                id3v2Tag.setYear(textYear.getText());
                id3v2Tag.setComment(textComment.getText());
                id3v2Tag.setComposer(textComposer.getText());
                id3v2Tag.setPublisher(textPublisher.getText());
                id3v2Tag.setOriginalArtist(textOriginalArtist.getText());
                id3v2Tag.setAlbumArtist(textAlbumArtist.getText());
                mp3file.save(s.getAlbumName() + "/" + textTitle.getText() + ".mp3");
                break;

            case 2:
                id3v2Tag.setTrack(textTrackNumber_2.getText());
                id3v2Tag.setArtist(textArtist_2.getText());
                id3v2Tag.setTitle(textTitle_2.getText());
                id3v2Tag.setAlbum(textAlbum_2.getText());
                id3v2Tag.setYear(textYear_2.getText());
                id3v2Tag.setComment(textComment_2.getText());
                id3v2Tag.setComposer(textComposer_2.getText());
                id3v2Tag.setPublisher(textPublisher_2.getText());
                id3v2Tag.setOriginalArtist(textOriginalArtist_2.getText());
                id3v2Tag.setAlbumArtist(textAlbumArtist_2.getText());
                mp3file.save(s.getAlbumName() + "/" + textTitle_2.getText() + ".mp3");
                break;

            case 3:
                id3v2Tag.setTrack(textTrackNumber_3.getText());
                id3v2Tag.setArtist(textArtist_3.getText());
                id3v2Tag.setTitle(textTitle_3.getText());
                id3v2Tag.setAlbum(textAlbum_3.getText());
                id3v2Tag.setYear(textYear_3.getText());
                id3v2Tag.setComment(textComment_3.getText());
                id3v2Tag.setComposer(textComposer_3.getText());
                id3v2Tag.setPublisher(textPublisher_3.getText());
                id3v2Tag.setOriginalArtist(textOriginalArtist_3.getText());
                id3v2Tag.setAlbumArtist(textAlbumArtist_3.getText());
                mp3file.save(s.getAlbumName() + "/" + textTitle_3.getText() + ".mp3");
                break;

            case 4:
                id3v2Tag.setTrack(textTrackNumber_4.getText());
                id3v2Tag.setArtist(textArtist_4.getText());
                id3v2Tag.setTitle(textTitle_4.getText());
                id3v2Tag.setAlbum(textAlbum_4.getText());
                id3v2Tag.setYear(textYear_4.getText());
                id3v2Tag.setComment(textComment_4.getText());
                id3v2Tag.setComposer(textComposer_4.getText());
                id3v2Tag.setPublisher(textPublisher_4.getText());
                id3v2Tag.setOriginalArtist(textOriginalArtist_4.getText());
                id3v2Tag.setAlbumArtist(textAlbumArtist_4.getText());
                mp3file.save(s.getAlbumName() + "/" + textTitle_4.getText() + ".mp3");
                break;

            case 5:
                id3v2Tag.setTrack(textTrackNumber_5.getText());
                id3v2Tag.setArtist(textArtist_5.getText());
                id3v2Tag.setTitle(textTitle_5.getText());
                id3v2Tag.setAlbum(textAlbum_5.getText());
                id3v2Tag.setYear(textYear_5.getText());
                id3v2Tag.setComment(textComment_5.getText());
                id3v2Tag.setComposer(textComposer_5.getText());
                id3v2Tag.setPublisher(textPublisher_5.getText());
                id3v2Tag.setOriginalArtist(textOriginalArtist_5.getText());
                id3v2Tag.setAlbumArtist(textAlbumArtist_5.getText());
                mp3file.save(s.getAlbumName() + "/" + textTitle_5.getText() + ".mp3");
                break;

            case 6:
                id3v2Tag.setTrack(textTrackNumber_6.getText());
                id3v2Tag.setArtist(textArtist_6.getText());
                id3v2Tag.setTitle(textTitle_6.getText());
                id3v2Tag.setAlbum(textAlbum_6.getText());
                id3v2Tag.setYear(textYear_6.getText());
                id3v2Tag.setComment(textComment_6.getText());
                id3v2Tag.setComposer(textComposer_6.getText());
                id3v2Tag.setPublisher(textPublisher_6.getText());
                id3v2Tag.setOriginalArtist(textOriginalArtist_6.getText());
                id3v2Tag.setAlbumArtist(textAlbumArtist_6.getText());
                mp3file.save(s.getAlbumName() + "/" + textTitle_6.getText() + ".mp3");
                break;

            case 7:
                id3v2Tag.setTrack(textTrackNumber_7.getText());
                id3v2Tag.setArtist(textArtist_7.getText());
                id3v2Tag.setTitle(textTitle_7.getText());
                id3v2Tag.setAlbum(textAlbum_7.getText());
                id3v2Tag.setYear(textYear_7.getText());
                id3v2Tag.setComment(textComment_7.getText());
                id3v2Tag.setComposer(textComposer_7.getText());
                id3v2Tag.setPublisher(textPublisher_7.getText());
                id3v2Tag.setOriginalArtist(textOriginalArtist_7.getText());
                id3v2Tag.setAlbumArtist(textAlbumArtist_7.getText());
                mp3file.save(s.getAlbumName() + "/" + textTitle_7.getText() + ".mp3");
                break;

            case 8:
                id3v2Tag.setTrack(textTrackNumber_8.getText());
                id3v2Tag.setArtist(textArtist_8.getText());
                id3v2Tag.setTitle(textTitle_8.getText());
                id3v2Tag.setAlbum(textAlbum_8.getText());
                id3v2Tag.setYear(textYear_8.getText());
                id3v2Tag.setComment(textComment_8.getText());
                id3v2Tag.setComposer(textComposer_8.getText());
                id3v2Tag.setPublisher(textPublisher_8.getText());
                id3v2Tag.setOriginalArtist(textOriginalArtist_8.getText());
                id3v2Tag.setAlbumArtist(textAlbumArtist_8.getText());
                mp3file.save(s.getAlbumName() + "/" + textTitle_8.getText() + ".mp3");
                break;

            case 9:
                id3v2Tag.setTrack(textTrackNumber_9.getText());
                id3v2Tag.setArtist(textArtist_9.getText());
                id3v2Tag.setTitle(textTitle_9.getText());
                id3v2Tag.setAlbum(textAlbum_9.getText());
                id3v2Tag.setYear(textYear_9.getText());
                id3v2Tag.setComment(textComment_9.getText());
                id3v2Tag.setComposer(textComposer_9.getText());
                id3v2Tag.setPublisher(textPublisher_9.getText());
                id3v2Tag.setOriginalArtist(textOriginalArtist_9.getText());
                id3v2Tag.setAlbumArtist(textAlbumArtist_9.getText());
                mp3file.save(s.getAlbumName() + "/" + textTitle_9.getText() + ".mp3");
                break;

            case 10:
                id3v2Tag.setTrack(textTrackNumber_10.getText());
                id3v2Tag.setArtist(textArtist_10.getText());
                id3v2Tag.setTitle(textTitle_10.getText());
                id3v2Tag.setAlbum(textAlbum_10.getText());
                id3v2Tag.setYear(textYear_10.getText());
                id3v2Tag.setComment(textComment_10.getText());
                id3v2Tag.setComposer(textComposer_10.getText());
                id3v2Tag.setPublisher(textPublisher_10.getText());
                id3v2Tag.setOriginalArtist(textOriginalArtist_10.getText());
                id3v2Tag.setAlbumArtist(textAlbumArtist_10.getText());
                mp3file.save(s.getAlbumName() + "/" + textTitle_10.getText() + ".mp3");
                break;
        }
    }

    public void setID3tag(String trackNo, String artist, String album, String year, String composer, String publisher, String originalArtist, String albumArtist){

        the_trackNumber = Integer.parseInt(trackNo);

        the_trackNo = Integer.toString(the_trackNumber);
        the_artist = artist;
        the_album = album;
        the_year = year;
        the_composer = composer;
        the_publisher = publisher;
        the_originalArtist = originalArtist;
        the_albumArtist = albumArtist;

    }

    public void copyID3tag(){
        textTrackNumber.setText(Integer.toString(getTrackNumber()));
        textArtist.setText(the_artist);
        textTitle.setText("");
        textAlbum.setText(the_album);
        textYear.setText(the_year);
        textComment.setText("");
        textComposer.setText(the_composer);
        textPublisher.setText(the_publisher);
        textOriginalArtist.setText(the_originalArtist);
        textAlbumArtist.setText(the_albumArtist);
    }

    public void saveID3tag(String artist, String album, String year, String composer, String publisher, String originalArtist, String albumArtist) {
        try	{
            FileWriter fw = new FileWriter(filepath,false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(artist+","+album+","+year+","+composer+","+publisher+","+originalArtist+","+albumArtist);
            pw.flush();
            pw.close();
        }catch(Exception E) {

        }
    }

    private static MediaPlayer mediaPlayer;

    public static void playMusic(String playTrack) {
        if (playingTrack == true) {
            mediaPlayer.stop();
            playingTrack = false;
        } else {
            playingTrack = true;
            Media go = new Media(Paths.get(playTrack).toUri().toString());
            mediaPlayer = new MediaPlayer(go);
            mediaPlayer.play();
        }

    }

    public void refresh() {
        AudioSplitter s = new AudioSplitter();
        File directory = new File(s.getAlbumName());
        trackQuantity = directory.list().length;
        tracksRecorded.setText("Tracks Recorded: " + trackQuantity);
        for (int count = trackQuantity + 1; count <= 10; count++) {
            switch (count)
            {

                case 2:
                    textTrackNumber_2.setEnabled(false);
                    textArtist_2.setEnabled(false);
                    textTitle_2.setEnabled(false);
                    textAlbum_2.setEnabled(false);
                    textYear_2.setEnabled(false);
                    textComment_2.setEnabled(false);
                    textComposer_2.setEnabled(false);
                    textPublisher_2.setEnabled(false);
                    textOriginalArtist_2.setEnabled(false);
                    textAlbumArtist_2.setEnabled(false);
                    playTrack_2.setEnabled(false);
                    break;

                case 3:
                    textTrackNumber_3.setEnabled(false);
                    textArtist_3.setEnabled(false);
                    textTitle_3.setEnabled(false);
                    textAlbum_3.setEnabled(false);
                    textYear_3.setEnabled(false);
                    textComment_3.setEnabled(false);
                    textComposer_3.setEnabled(false);
                    textPublisher_3.setEnabled(false);
                    textOriginalArtist_3.setEnabled(false);
                    textAlbumArtist_3.setEnabled(false);
                    playTrack_3.setEnabled(false);
                    break;

                case 4:
                    textTrackNumber_4.setEnabled(false);
                    textArtist_4.setEnabled(false);
                    textTitle_4.setEnabled(false);
                    textAlbum_4.setEnabled(false);
                    textYear_4.setEnabled(false);
                    textComment_4.setEnabled(false);
                    textComposer_4.setEnabled(false);
                    textPublisher_4.setEnabled(false);
                    textOriginalArtist_4.setEnabled(false);
                    textAlbumArtist_4.setEnabled(false);
                    playTrack_4.setEnabled(false);
                    break;

                case 5:
                    textTrackNumber_5.setEnabled(false);
                    textArtist_5.setEnabled(false);
                    textTitle_5.setEnabled(false);
                    textAlbum_5.setEnabled(false);
                    textYear_5.setEnabled(false);
                    textComment_5.setEnabled(false);
                    textComposer_5.setEnabled(false);
                    textPublisher_5.setEnabled(false);
                    textOriginalArtist_5.setEnabled(false);
                    textAlbumArtist_5.setEnabled(false);
                    playTrack_5.setEnabled(false);
                    break;

                case 6:
                    textTrackNumber_6.setEnabled(false);
                    textArtist_6.setEnabled(false);
                    textTitle_6.setEnabled(false);
                    textAlbum_6.setEnabled(false);
                    textYear_6.setEnabled(false);
                    textComment_6.setEnabled(false);
                    textComposer_6.setEnabled(false);
                    textPublisher_6.setEnabled(false);
                    textOriginalArtist_6.setEnabled(false);
                    textAlbumArtist_6.setEnabled(false);
                    playTrack_6.setEnabled(false);
                    break;

                case 7:
                    textTrackNumber_7.setEnabled(false);
                    textArtist_7.setEnabled(false);
                    textTitle_7.setEnabled(false);
                    textAlbum_7.setEnabled(false);
                    textYear_7.setEnabled(false);
                    textComment_7.setEnabled(false);
                    textComposer_7.setEnabled(false);
                    textPublisher_7.setEnabled(false);
                    textOriginalArtist_7.setEnabled(false);
                    textAlbumArtist_7.setEnabled(false);
                    playTrack_7.setEnabled(false);
                    break;

                case 8:
                    textTrackNumber_8.setEnabled(false);
                    textArtist_8.setEnabled(false);
                    textTitle_8.setEnabled(false);
                    textAlbum_8.setEnabled(false);
                    textYear_8.setEnabled(false);
                    textComment_8.setEnabled(false);
                    textComposer_8.setEnabled(false);
                    textPublisher_8.setEnabled(false);
                    textOriginalArtist_8.setEnabled(false);
                    textAlbumArtist_8.setEnabled(false);
                    playTrack_8.setEnabled(false);
                    break;

                case 9:
                    textTrackNumber_9.setEnabled(false);
                    textArtist_9.setEnabled(false);
                    textTitle_9.setEnabled(false);
                    textAlbum_9.setEnabled(false);
                    textYear_9.setEnabled(false);
                    textComment_9.setEnabled(false);
                    textComposer_9.setEnabled(false);
                    textPublisher_9.setEnabled(false);
                    textOriginalArtist_9.setEnabled(false);
                    textAlbumArtist_9.setEnabled(false);
                    playTrack_9.setEnabled(false);
                    break;

                case 10:
                    textTrackNumber_10.setEnabled(false);
                    textArtist_10.setEnabled(false);
                    textTitle_10.setEnabled(false);
                    textAlbum_10.setEnabled(false);
                    textYear_10.setEnabled(false);
                    textComment_10.setEnabled(false);
                    textComposer_10.setEnabled(false);
                    textPublisher_10.setEnabled(false);
                    textOriginalArtist_10.setEnabled(false);
                    textAlbumArtist_10.setEnabled(false);
                    playTrack_10.setEnabled(false);
                    break;

                default:
                    break;
            }
        }
    }

    public void loadID3tag() {
        String filename = "ID3tag.txt";

        File file = new File(filename);

        BufferedReader reader = null;
        try {

            reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                textTrackNumber.setText("");
                textArtist.setText(values[0]);
                textTitle.setText("");
                textAlbum.setText(values[1]);
                textYear.setText(values[2]);
                textComment.setText("");
                textComposer.setText(values[3]);
                textPublisher.setText(values[4]);
                textOriginalArtist.setText(values[5]);
                textAlbumArtist.setText(values[6]);
            }
        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(null, "No file found!", "Error", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        setID3tag(textTrackNumber.getText(),textArtist.getText(),textAlbum.getText(),textYear.getText(),textComposer.getText(),textPublisher.getText(),textOriginalArtist.getText(),textAlbumArtist.getText());
        firstTag=false;
    }

    public void combine(int trackChosen) {
        AudioSplitter s = new AudioSplitter();

        for (int i = tracksSelected - 1; i > 0; i--) {
            try {
                int temp = trackChosen + 1;
                String file1 = s.getAlbumName() + "\\TRACK_" + trackChosen + ".mp3";
                String file2 = s.getAlbumName() + "\\TRACK_" + temp + ".mp3";
                FileInputStream fistream1 = new FileInputStream(file1);
                FileInputStream fistream2 = new FileInputStream(file2);
                SequenceInputStream sistream = new SequenceInputStream(fistream1, fistream2);
                FileOutputStream fostream = new FileOutputStream(s.getAlbumName() + "\\MergedTrack.mp3");


                while(( temp = sistream.read()) != -1)
                {
                    fostream.write(temp);
                }
                fostream.close();
                sistream.close();
                fistream1.close();
                fistream2.close();
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }


            File cleanFile1 = new File(s.getAlbumName() + "\\TRACK_" + trackChosen + ".mp3");
            int temp = trackChosen + 1;
            File cleanFile2 = new File(s.getAlbumName() + "\\TRACK_" + temp + ".mp3");
            boolean a = cleanFile1.delete();
            boolean b = cleanFile2.delete();
            File f1 = new File(s.getAlbumName() + "\\MergedTrack.mp3");
            File f2 = new File(s.getAlbumName() + "\\TRACK_" + trackChosen + ".mp3");
            if (f1.renameTo(f2)) {
                System.out.println("File rename success");;
            }else{
                System.out.println("File rename failed");
            }
            rearrangeTrack(trackChosen);
        }
    }

    public void rearrangeTrack(int trackChosen) {
        AudioSplitter s = new AudioSplitter();
        File directory = new File(s.getAlbumName());
        int quantity = directory.list().length;
        for (int i = trackChosen + 2; i <= quantity + 1; i++) {
            File f1 = new File(s.getAlbumName() + "\\TRACK_" + i + ".mp3");
            int temp = i-1;
            File f2 = new File(s.getAlbumName() + "\\TRACK_" + temp + ".mp3");
            if (f1.renameTo(f2)){
                System.out.println("Success");
            } else {
                System.out.println("Failed");
            }
        }

    }

    public void enablePlay() {
        AudioSplitter s = new AudioSplitter();
        File directory = new File(s.getAlbumName());
        trackQuantity = directory.list().length;
        for (int count = 1; count <= trackQuantity; count++) {
            switch (count)
            {

                case 1:
                    playTrack_1.setEnabled(true);
                    break;

                case 2:
                    playTrack_2.setEnabled(true);
                    break;

                case 3:
                    playTrack_3.setEnabled(true);
                    break;

                case 4:
                    playTrack_4.setEnabled(true);
                    break;

                case 5:
                    playTrack_5.setEnabled(true);
                    break;

                case 6:
                    playTrack_6.setEnabled(true);
                    break;

                case 7:
                    playTrack_7.setEnabled(true);
                    break;

                case 8:
                    playTrack_8.setEnabled(true);
                    break;

                case 9:
                    playTrack_9.setEnabled(true);

                    break;

                case 10:
                    playTrack_10.setEnabled(true);
                    break;

                default:
                    break;
            }
        }
    }

    public void disablePlay() {
        AudioSplitter s = new AudioSplitter();
        File directory = new File(s.getAlbumName());
        trackQuantity = directory.list().length;
        for (int count = 1; count <= trackQuantity; count++) {
            switch (count)
            {

                case 1:
                    playTrack_1.setEnabled(false);
                    break;

                case 2:
                    playTrack_2.setEnabled(false);
                    break;

                case 3:
                    playTrack_3.setEnabled(false);
                    break;

                case 4:
                    playTrack_4.setEnabled(false);
                    break;

                case 5:
                    playTrack_5.setEnabled(false);
                    break;

                case 6:
                    playTrack_6.setEnabled(false);
                    break;

                case 7:
                    playTrack_7.setEnabled(false);
                    break;

                case 8:
                    playTrack_8.setEnabled(false);
                    break;

                case 9:
                    playTrack_9.setEnabled(false);

                    break;

                case 10:
                    playTrack_10.setEnabled(false);
                    break;

                default:
                    break;
            }
        }
    }
}

