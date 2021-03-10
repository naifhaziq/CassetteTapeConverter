package cassetteTapeConverter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Line.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;

import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import org.apache.commons.io.FileUtils;
import javax.swing.border.LineBorder;



public class AudioSplitter extends JFrame {

    private static final long serialVersionUID = 1L;

    static int boxWidth = 4;
    static Dimension size = new Dimension(boxWidth == 1 ? 512 : 513, 97);

    static BufferedImage img;
    static JPanel view;
    MediaPlayer.Status status = MediaPlayer.Status.UNKNOWN;
    private static Boolean playingTrack = false;

    private JPanel contentPane;
    private JComboBox<Info> box;
    private JLabel lblStatusMessage;
    private JPanel panel;
    private static JPanel graphPanel;
    private JTextField textStatus;
    private static JTextField textNR;
    private static JTextField textThreshold;
    private static JTextField textDuration;
    private JButton btnReprocess;
    private JMenu mbFile;
    private JMenu mbHelp;
    private JMenuBar mb;
    private JMenuItem subReprocess;
    private JMenuItem subID3tags;
    private JMenuItem subNew;
    private JMenuItem subSave;
    private JFrame exitWarning;
    private static JButton btnStart;
    private JButton btnID3tags;
    private static JButton btnStop;
    private static boolean newTrack = false;
    private static boolean savedAlbum = false;

    static JProgressBar pbar = new JProgressBar();

    private final static int DEFAULT_STOP_TIME = 15; // Duration to detect end of recording
    private final static String DEFAULT_WAV_FILE = "Full_Audio.wav"; // File name of the full recording
    private final static String DEFAULT_WAV_FILE2 = "audio-clean.wav"; // File name of the full recording after noise reduction
    private final static String DEFAULT_TRACK_FILE = "TRACK"; // Prefix of each track audio
    //private final static String OUTPUT_FOLDER = JOptionPane.showInputDialog(null, "Please enter album name", "Album Name", JOptionPane.INFORMATION_MESSAGE);; // Prefix of each track audio
    private final static String OUTPUT_FOLDER = "output";
    private static String recordedFolder = OUTPUT_FOLDER;
    private static String renameFolder;
    public String getAlbumName() {
        return AudioSplitter.recordedFolder;
    }
    private final static String DEFAULT_TRACK_EXT = "mp3"; // File extension of track audio
    private final static int FRAME_RATE = 44100; // Frame Rate of audio format
    private final static int CHANNELS = 2; // Number of channels
    private final static int SAMPLE_BITS = 16; // Sample Bits

    private final static int MP3_BIT_RATES = 128000; // MP3 Bit rates

    private final static int MULTIPLIER = 1; // Multiplier to make the counter to match the duration in second

    private final static int BYTE_BUFFER_SIZE = FRAME_RATE * CHANNELS; // Size of the buffer to read a chunk of audio to check threshold

    private final static int MAX_BUFFER_SIZE_IN_MB = 40; // Max buffer size in memory to avoid out of memory error

    private static int DEFAULT_THRESHOLD = -70; // Threshold decibel (dB). Below threshold will be considered silent
    private static int DEFAULT_DURATION = 3; // Duration to detect silence
    private static int trackCount = 1; // Quantity of generated track
    private static String noiseReduction = "0.1";
    private static String stopDuration = "20";
    private static String threshold = "-70";

    private Info[] targetline; //Array storing available targetline
    private ItemHandler handler = new ItemHandler();

    private Mixer.Info[] mixers = AudioSystem.getMixerInfo();
    private static ArrayList<Info> availableLines = new ArrayList<Line.Info>();
    private static TargetDataLine line;

    private static Boolean played = false;

    private static Boolean played2 = false;

    public static Boolean stop = false;

    public static Boolean playing = false;

    static int minimum = 0;
    static int maximum = 0;

    private static DecimalFormat df2 = new DecimalFormat(".##");
    private static JButton btnPlay;
    private static JButton btnStopPlaying;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AudioSplitter frame = new AudioSplitter();
                    frame.pack();
                    frame.setVisible(true);
                    createSettings();
                    //writeSettings();
                    readSettings();

//					frame.cleanOldFiles(DEFAULT_TRACK_FILE + "_");
//					frame.cleanOldFiles(OUTPUT_FOLDER + File.separator + DEFAULT_TRACK_FILE + "_");
//					frame.cleanOldFiles("temp_");
//					Thread recorder = new Thread(new Recorder(frame));
//					recorder.start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AudioSplitter() {
        setResizable(false);

        for (Mixer.Info mixerInfo : mixers){
            System.out.println("Found Mixer: " + mixerInfo);

            Mixer m = AudioSystem.getMixer(mixerInfo);

            Line.Info[] lines = m.getTargetLineInfo();

            for (Line.Info li : lines){
//		        	m.close();
//		            m.open();
                availableLines.add(li);
            }
            targetline = new Info[availableLines.size()];
            targetline = availableLines.toArray(targetline);

        }

        final JFXPanel fxPanel = new JFXPanel();

        System.out.println("Available lines: " + availableLines);
        setPreferredSize(new Dimension(540, 370));
        //setBounds(100, 100, 775, 435);
        setTitle("Cassette Tape Converter");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent evt){
                if (!savedAlbum) {
                    int x = JOptionPane.showConfirmDialog(null,
                            "You have not saved yet!" + "\n" + "Are you sure you want to exit?", "Warning!",
                            JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    } else {
                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    }
                }
                if (savedAlbum == true) {
                    int x = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to exit?", "Warning!",
                            JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    } else {
                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); }
                }
            }
        });
        setBounds(100, 100, 940, 562);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JMenuItem subIns = new JMenuItem("Help");
        subIns.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Help frame3 = new Help();
                frame3.pack();
                frame3.setVisible(true);
            }
        });

        mb = new JMenuBar();
        mbFile = new JMenu("File");
        mbHelp = new JMenu("Help");
        JMenu mbTracks = new JMenu("Tracks");
        JMenu mbSettings = new JMenu("Settings");
        mb.add(mbFile);
        mb.add(mbTracks);
        mb.add(mbSettings);
        mb.add(mbHelp);

        subNew = new JMenuItem("New");
        subNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                newTrack = true;
                //loadImage();
                btnStart.setEnabled(true);
                stop = false;
                played = false;
                played2 = false;
                graphPanel.removeAll();
                graphPanel.updateUI();
            }
        });
        subNew.setEnabled(false);

        JMenuItem subOpen = new JMenuItem("Open");
        subOpen.addActionListener(new ActionListener () {
           public void actionPerformed(ActionEvent event){
               recordedFolder = JOptionPane.showInputDialog(null, "Enter the name of the album", "Open", JOptionPane.INFORMATION_MESSAGE);
               ID3tags frame2 = new ID3tags();
               frame2.pack();
               frame2.setVisible(true);
           }
        });

        subID3tags = new JMenuItem("Naming the Tracks");
        subID3tags.setEnabled(true);
        subID3tags.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent event) {
                ID3tags frame2 = new ID3tags();
                frame2.pack();
                frame2.setVisible(true);
            }
        });

        JMenuItem subExit = new JMenuItem("Exit");
        subExit.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent exit) {
                exitWarning = new JFrame("Exit");
                if (JOptionPane.showConfirmDialog( exitWarning,"Are you sure you want to exit?","Warning!",
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });

        subSave = new JMenuItem("Save");
        subSave.setEnabled(true);
        subSave.addActionListener(new ActionListener () {
            public void actionPerformed (ActionEvent event) {
                renameFolder = JOptionPane.showInputDialog(null, "Please enter album name", "Album Name", JOptionPane.INFORMATION_MESSAGE);
                File sourceFolder = new File(recordedFolder);
                File destFolder = new File(renameFolder);

                if (sourceFolder.renameTo(destFolder)) {
                    System.out.println("Album saved successfully");
                    JOptionPane.showMessageDialog(null, "Album saved!");
                    recordedFolder = renameFolder;
                    savedAlbum = true;
                } else {
                    System.out.println("Failed to save album");
                    JOptionPane.showMessageDialog(null, "Album failed to save");
                }


            }
        });

        subReprocess = new JMenuItem("Reprocess");
        subReprocess.setEnabled(false);
        final AudioSplitter frame = this;
        subReprocess.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frame.cleanOldFiles(DEFAULT_TRACK_FILE + "_");
                frame.cleanOldFiles("temp_");
                try {
                    FileUtils.cleanDirectory(new File(OUTPUT_FOLDER));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                new Thread(new Processor(frame)).start();
                frame.cleanOldFiles("temp_");
                subReprocess.setEnabled(false);

            }
        });

        JMenuItem subNoise = new JMenuItem("Noise Reduction Sensitivity");
        subNoise.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                noiseReduction = (String) JOptionPane.showInputDialog(null, "Noise Reduction Value", "Settings", JOptionPane.INFORMATION_MESSAGE,null,null,noiseReduction);;
                writeSettings();
                if (noiseReduction == null) {
                    readSettings();
                }
                writeSettings();
            }
        });

        JMenuItem subStop = new JMenuItem("Stop Duration");
        subStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                stopDuration = (String) JOptionPane.showInputDialog(null, "Stop Duration", "Settings", JOptionPane.INFORMATION_MESSAGE,null,null,stopDuration);;
                writeSettings();
                if (stopDuration == null) {
                    readSettings();
                }
                writeSettings();
            }
        });

        JMenuItem subThreshold = new JMenuItem("Threshold Value");
        subThreshold.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                threshold = (String) JOptionPane.showInputDialog(null, "Threshold Value(Higher value -> More Tracks)", "Settings", JOptionPane.INFORMATION_MESSAGE,null,null,threshold);;
                if (threshold == null) {
                    readSettings();
                }
                writeSettings();
            }
        });

        //mbFile.add(subNew);
        mbFile.add(subOpen);
        mbFile.add(subSave);
        mbFile.add(subExit);
        mbTracks.add(subID3tags);
        mbTracks.add(subReprocess);
        mbSettings.add(subNoise);
        mbSettings.add(subStop);
        mbSettings.add(subThreshold);
        mbHelp.add(subIns);


        contentPane.add(mb, BorderLayout.NORTH);

        panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        //final AudioSplitter frame = this;
        panel.setLayout(null);

        btnReprocess = new JButton("Reprocess");
        btnReprocess.setBounds(340, 11, 100, 33);
        btnReprocess.setFont(new Font("Arial", Font.PLAIN, 15));
        btnReprocess.setEnabled(false);
        btnReprocess.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                frame.cleanOldFiles(DEFAULT_TRACK_FILE + "_");
                frame.cleanOldFiles("temp_");
                try {
                    FileUtils.cleanDirectory(new File(OUTPUT_FOLDER));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                new Thread(new Processor(frame)).start();
                frame.cleanOldFiles("temp_");
                btnReprocess.setEnabled(false);

            }
        });
        btnStart = new JButton("Start");
        btnStart.setBounds(10, 11, 100, 33);
        btnStart.setFont(new Font("Arial", Font.PLAIN, 15));
        btnStart.setEnabled(true);
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                //nameFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                frame.cleanOldFiles(DEFAULT_TRACK_FILE + "_");
                frame.cleanOldFiles(OUTPUT_FOLDER + File.separator + DEFAULT_TRACK_FILE + "_");
                frame.cleanOldFiles("temp_");
                recordedFolder = "output";
                Thread recorder = new Thread(new Recorder(frame));
                recorder.start();
                btnStart.setEnabled(false);
            }
        });
        panel.add(btnStart);

        btnID3tags = new JButton("Add ID3 tag");
        btnID3tags.setBounds(230, 11, 100, 33);
        btnID3tags.setFont(new Font("Arial", Font.PLAIN, 20));
        btnID3tags.setEnabled(false);
        btnID3tags.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                dispose();
                ID3tags frame2 = new ID3tags();
                frame2.pack();
                frame2.setVisible(true);
            }
        });
        //panel.add(btnID3tags);

        btnStop = new JButton("Stop");
        btnStop.setBounds(120, 11, 100, 33);
        btnStop.setFont(new Font("Arial", Font.PLAIN, 15));
        btnStop.setEnabled(false);
        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                stop = true;
                btnStop.setEnabled(false);
            }
        });
        panel.add(btnStop);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(0, 55, 894, 33);
        panel.add(panel_2);
        FlowLayout flowLayout2 = (FlowLayout) panel_2.getLayout();
        flowLayout2.setAlignment(FlowLayout.LEFT);

        graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if(img != null) {
                    g.drawImage(img, 1, 1, img.getWidth(), img.getHeight(), null);
                }
            }
        };
        graphPanel.setBorder(new LineBorder(Color.BLACK));
        graphPanel.setBounds(0, 111, 510, 107);
        panel.add(graphPanel);

        JPanel panel_1 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        contentPane.add(panel_1, BorderLayout.SOUTH);

        JLabel lblThreshold = new JLabel("Threshold value: ");
        lblThreshold.setFont(new Font("Arial", Font.PLAIN, 20));
        lblThreshold.setForeground(Color.BLUE);
        //panel_1.add(lblThreshold);

        textThreshold = new JTextField("-85");
        textThreshold.setEditable(true);
        //panel_1.add(textThreshold);
        textThreshold.setColumns(5);

        JLabel lblDuration = new JLabel("Duration in sec: ");
        lblDuration.setFont(new Font("Arial", Font.PLAIN, 20));
        lblDuration.setForeground(Color.BLUE);
        //panel_1.add(lblDuration);

        textDuration = new JTextField("20");
        textDuration.setEditable(true);
        //panel_1.add(textDuration);
        textDuration.setColumns(5);

        JLabel lblNR = new JLabel("Noise Reduction Sensitivity: ");
        lblNR.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNR.setForeground(Color.BLUE);
        //panel_1.add(lblNR);

        textNR = new JTextField("0.1");
        textNR.setEditable(true);
        //panel_1.add(textNR);
        textNR.setColumns(5);

        JLabel lblCurrentDb = new JLabel("Status :");
        lblCurrentDb.setFont(new Font("Arial", Font.PLAIN, 20));
        lblCurrentDb.setForeground(Color.BLUE);
        panel_1.add(lblCurrentDb);

        textStatus = new JTextField();
        textStatus.setEditable(false);
        panel_1.add(textStatus);
        textStatus.setColumns(20);

        lblStatusMessage = new JLabel("");
        panel_1.add(lblStatusMessage);

        pbar.setMinimum(0);
        pbar.setMaximum(100);
        pbar.setStringPainted(true);
        panel_1.add(pbar);

        box = new JComboBox<Info>(targetline);
        panel_2.add(box);
        box.addItemListener(handler);

        btnPlay = new JButton("Play");
        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = "Full_Audio.wav";
                playMusic(playTrack);
            }
        });
        btnPlay.setFont(new Font("Arial", Font.PLAIN, 15));
        btnPlay.setEnabled(false);
        btnPlay.setBounds(230, 11, 100, 33);
        panel.add(btnPlay);

        btnStopPlaying = new JButton("Stop Play");
        btnStopPlaying.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playTrack = "Full_Audio.wav";
                playMusic(playTrack);
            }
        });
        btnStopPlaying.setFont(new Font("Arial", Font.PLAIN, 15));
        btnStopPlaying.setEnabled(false);
        btnStopPlaying.setBounds(340, 11, 100, 33);
        panel.add(btnStopPlaying);


    }

    /**
     * Method to update the status text
     * @param msg
     */
    void setStatus(final String msg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                textStatus.setText(msg);
                textStatus.setFont(new Font("Arial", Font.PLAIN, 20));
            }
        });
    }

    /**
     * Enables reprocess button
     */
    void enableReprocess() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                subID3tags.setEnabled(true);
                btnStop.setEnabled(false);
                btnStart.setEnabled(false);
                subReprocess.setEnabled(true);
                btnPlay.setEnabled(true);
                subSave.setEnabled(true);
                subNew.setEnabled(true);

            }
        });

    }

    /**
     * Enables add ID3tag button
     */
    void enableAddID3tag() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                subID3tags.setEnabled(true);
            }
        });

    }

    /**
     * Delete files with given pattern
     * @param startPattern
     */
    public void cleanOldFiles(String startPattern) {
//		System.out.println("Cleaning old files....");
        final File folder = new File(".");
        for (File f : folder.listFiles()) {
            if (f.getName().startsWith(startPattern)) {
                f.delete();
            }
        }
    }

    public static double getNR(){
        return Double.parseDouble(noiseReduction);
    }

    /**
     * Get threshold for silent check
     * @return threshold
     */
    public double getThreshold() {
        return Double.parseDouble(threshold);
    }

    public int getStopDuration() {
        return Integer.parseInt(stopDuration);
    }


    /**
     * Get duration for silent check
     * @return duration
     */
    public int getDuration() {
        return DEFAULT_DURATION;
    }

    public static int getTrackCount() {
        return trackCount;
    }

    public static Boolean getPlayed() {
        return played;
    }

    public static void updateBar(float max,float newValue){
        float step1 = max - newValue;
        float step2 = step1*100.0f/max;
        int percent = (int)step2;
        pbar.setValue(percent);
    }

    /**
     * Choose available targetline
     * @return targetline
     */
    private class ItemHandler implements ItemListener{

        public void itemStateChanged(ItemEvent event){
            //for(int i = 0; i<targetline.length; i++) {
            //box.getSelectedItem().equals(targetline[i]);
            if(event.getSource()==box){
                for(int j = 0;j<targetline.length;j++){
                    if(box.getSelectedItem().equals(targetline[j])){
                        try {
                            line = (TargetDataLine) AudioSystem.getLine(availableLines.get(j));
                        } catch (Exception e) {
                            //JOptionPane.showMessageDialog(null, "Please choose another data line!", "Error", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        }
    }
    //}

    /**
     *
     * Static inner class to record audio from LINE IN and to save the Full Audio to
     * a temporary wav file. It runs as a separate thread.
     *
     */
    static class Recorder implements Runnable {
        final AudioSplitter audioSplitter;

        SwingWorkerRealTime swingWorkerRealTime = new SwingWorkerRealTime();

        Recorder(final AudioSplitter audioSplitter) {
            this.audioSplitter = audioSplitter;
        }

        @Override
        public void run() {
            AudioFormat fmt = new AudioFormat(FRAME_RATE, SAMPLE_BITS, CHANNELS, true, false);
            long counter = 0;
            byte[] buf = new byte[BYTE_BUFFER_SIZE];
            double currentSPL = 0;
            boolean track_started = false;
            boolean line_connected = false;


            try {
                line.open(fmt, BYTE_BUFFER_SIZE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please choose another data line!", "Error", JOptionPane.INFORMATION_MESSAGE);
                btnStart.setEnabled(true);
            }

            line.start();
//			System.out.println("Started Recording....");


            WaveWriter writer = null;
            try {
                writer = new WaveWriter(FRAME_RATE, SAMPLE_BITS, CHANNELS);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            for (int b; (b = line.read(buf, 0, buf.length)) > -1;) {
                try {
                    writer.write(buf, 0, b);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                float[] samples = AudioUtils.convertBytesToSamples(buf, b);
                currentSPL = AudioUtils.getSoundPressureLevel(samples);
                double threshold = audioSplitter.getThreshold();
                boolean silence = currentSPL < threshold;

                if (silence) {
                    counter++;
                    if (counter >= audioSplitter.getStopDuration() * MULTIPLIER) {
                        try {
                            if (track_started) {
                                File file = AudioUtils.appendBytesToFile(writer.getData(), DEFAULT_WAV_FILE);
                                Files.copy(file.toPath(), new File(DEFAULT_WAV_FILE).toPath(), StandardCopyOption.REPLACE_EXISTING);
                            } else {
                                AudioUtils.saveBytesToFile(writer.getData(), DEFAULT_WAV_FILE);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (stop) {
                            counter = 0;
                            line.stop();
                            line.close();
                            Thread processor = new Thread(new Processor(audioSplitter));
                            processor.start();
                            audioSplitter.setStatus("Stopped Recording..");
                            return;
                        } else {
                            counter = 0;
                            line.stop();
                            line.close();
                            Thread processor = new Thread(new Processor(audioSplitter));
                            processor.start();
                            audioSplitter.setStatus("Stopped Recording..");
                            return;
                        }
                    }
                } else {
                    counter = 0;
                }

                if (stop) {
                    try {
                        if (track_started) {
                            File file = AudioUtils.appendBytesToFile(writer.getData(), DEFAULT_WAV_FILE);
                            Files.copy(file.toPath(), new File(DEFAULT_WAV_FILE).toPath(), StandardCopyOption.REPLACE_EXISTING);
                        } else {
                            AudioUtils.saveBytesToFile(writer.getData(), DEFAULT_WAV_FILE);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    counter = 0;
                    line.stop();
                    line.close();
                    Thread processor = new Thread(new Processor(audioSplitter));
                    processor.start();
                    audioSplitter.setStatus("Stopped Recording..");
                    return;
                }

                int bufferSize = 0;
                try {
                    bufferSize = writer.getData().length;
                } catch (IOException e) {
                    e.printStackTrace();
                }


                // Handles out of memory issue. Once the buffer is over MAX_BUFFER_SIZE_IN_MB MB it will append it to the file.
                if (bufferSize > MAX_BUFFER_SIZE_IN_MB * 1024 * 1024) {
                    try {
                        if (track_started) {
                            File file = AudioUtils.appendBytesToFile(writer.getData(), DEFAULT_WAV_FILE);
                            Files.copy(file.toPath(), new File(DEFAULT_WAV_FILE).toPath(), StandardCopyOption.REPLACE_EXISTING);
                        } else {
                            AudioUtils.saveBytesToFile(writer.getData(), DEFAULT_WAV_FILE);
                        }
                        track_started = true;
//						System.out.println("Buffer Overflow : [" + DEFAULT_WAV_FILE + "]" + writer.getData().length);
                        writer = new WaveWriter(FRAME_RATE, SAMPLE_BITS, CHANNELS);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                double displaySPL = currentSPL;
                btnStop.setEnabled(true);
                audioSplitter.setStatus("Capturing... " + df2.format(displaySPL) + " dB");
//				audioSplitter.setStatus("The song is playing...");

                swingWorkerRealTime.go(currentSPL);

                played = true;

            }
            audioSplitter.cleanOldFiles(DEFAULT_TRACK_FILE + "_");
            audioSplitter.cleanOldFiles(OUTPUT_FOLDER + File.separator + DEFAULT_TRACK_FILE + "_");
            audioSplitter.cleanOldFiles("temp_");
//			System.out.println("Started Finished....");
            new Thread(new Processor(audioSplitter)).start();

        }
    }

    /**
     *
     * Static inner class to split the Full Audio to a different tracks based on dB
     * threshold and silence duration. It also runs as a separate thread.
     *
     */
    static class Processor implements Runnable {
        final AudioSplitter audioSplitter;

        Processor(final AudioSplitter audioSplitter) {
            this.audioSplitter = audioSplitter;
        }

        private void splitBySilentArea(AudioFormat targetFormat, AudioInputStream din)
                throws IOException, LineUnavailableException {

            trackCount = 1;

            long counter = 0;
            double currentSPL = 0;
            byte[] buf = new byte[BYTE_BUFFER_SIZE];
            boolean noise = true;
            boolean track_started = false;

            WaveWriter writer = null;
            try {
                writer = new WaveWriter(FRAME_RATE, SAMPLE_BITS, CHANNELS);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            for (int b; (b = din.read(buf, 0, buf.length)) > -1;) {

//				System.out.println("Frame length: " + din.getFrameLength());
//				System.out.println("Available frame: " + din.available());

                if(played2 == false){
                    maximum = (int) din.available();
                }
//				System.out.println("Maximum: " + maximum);

                updateBar((float)maximum,(float)din.available());

                played2 = true;

                float[] samples = AudioUtils.convertBytesToSamples(buf, b);

                currentSPL = AudioUtils.getSoundPressureLevel(samples);
                double threshold = audioSplitter.getThreshold();
                int duration = audioSplitter.getDuration();
                boolean silence = currentSPL < threshold;
//				System.out.println(currentSPL);
                if (silence) {
                    counter++;
                    if (counter >= duration * MULTIPLIER) {
                        if (!noise) {
                            String trackName = saveTrack(writer, trackCount, track_started, true);
                            track_started = false;
                            audioSplitter.setStatus("Generated Track: " + trackName);
                            trackCount++;
                            writer = new WaveWriter(FRAME_RATE, SAMPLE_BITS, CHANNELS);
                        }
                        else {
                        }
                        counter = 0;
                        noise = true;
                    }
                    else {
                        writer.write(buf, 0, b);
                    }
                }
                else {
                    writer.write(buf, 0, b);
                    counter = 0;
                    noise = false;
                }


                int bufferSize = 0;
                try {
                    bufferSize = writer.getData().length;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Handles out of memory issue. Once the buffer is over MAX_BUFFER_SIZE_IN_MB MB it will append it to the file.
                if (bufferSize > MAX_BUFFER_SIZE_IN_MB * 1024 * 1024) {
                    String trackName = saveTrack(writer, trackCount, track_started, false);
//					System.out.println("Buffer Overflow : [" + trackName + "]" + bufferSize);
                    track_started = true;
                    writer = new WaveWriter(FRAME_RATE, SAMPLE_BITS, CHANNELS);
                }

//				audioSplitter.setStatus("Processing ... " + currentSPL + " dB");
                audioSplitter.setStatus("Splitting audio...");

            }
            din.close();

            // Process last track
            if (!noise) {
                String trackName = saveTrack(writer, trackCount, track_started, true);
                track_started = false;
                audioSplitter.setStatus("Generated Track: " + trackName);
            } else {
                File wavTemp = new File(DEFAULT_TRACK_FILE + "_" + trackCount + ".wav");
                if(wavTemp.exists()) {
                    wavTemp.delete();
                }
                //System.out.println("Noise...");
            }

            audioSplitter.setStatus("Completed!");
            loadImage();
            audioSplitter.cleanOldFiles("temp");
            audioSplitter.enableReprocess();
            audioSplitter.enableAddID3tag();
        }

        private String saveTrack(WaveWriter currentWriter, int index, boolean append, boolean complete) {
            String trackName = DEFAULT_TRACK_FILE + "_" + index + "." + DEFAULT_TRACK_EXT;

            String wavTemp = DEFAULT_TRACK_FILE + "_" + index + ".wav";

            try {
                File tempFile = new File(wavTemp);
                if (append) {
                    File temp = AudioUtils.appendBytesToFile(currentWriter.getData(), wavTemp);
                    Files.copy(temp.toPath(), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } else {
                    AudioUtils.saveBytesToFile(currentWriter.getData(), wavTemp);
                }
                if (complete) {
                    AudioUtils.wav2mp3(wavTemp, OUTPUT_FOLDER + File.separator + trackName, MP3_BIT_RATES, CHANNELS, FRAME_RATE);
                    tempFile.deleteOnExit();
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InputFormatException e) {
                e.printStackTrace();
            } catch (EncoderException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return trackName;
        }

        @Override
        public void run() {
//			System.out.println("Started Processing....");
            try {
                audioSplitter.setStatus("Reducing noise..");
                NoiseReduction.runNoiseReduction();
                File file = new File(DEFAULT_WAV_FILE2);
//				File file = new File("C:\Users\User\Documents\Work\FYP\Tape Converter v2\Tape Converter v2\Full_Audio.wav");
                AudioInputStream in = AudioSystem.getAudioInputStream(file);
                AudioInputStream din = null;
//				System.out.println(in.getFrameLength());
                if (in != null) {
                    AudioFormat baseFormat = in.getFormat();
                    AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                            baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2,
                            baseFormat.getSampleRate(), false);
                    din = AudioSystem.getAudioInputStream(decodedFormat, in);
                    splitBySilentArea(decodedFormat, din);

                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
//			System.out.println("Finished Processing....");

        }

    }

    private static MediaPlayer mediaPlayer;

    static void enableStop() {
        btnStopPlaying.setEnabled(true);
    }


    // draw the image
    static void drawImage(float[] samples) {
        Graphics2D g2d = img.createGraphics();

        int numSubsets = size.width / boxWidth;
        int subsetLength = samples.length / numSubsets;

        float[] subsets = new float[numSubsets];

        // find average(abs) of each box subset
        int s = 0;
        for(int i = 0; i < subsets.length; i++) {

            double sum = 0;
            for(int k = 0; k < subsetLength; k++) {
                sum += Math.abs(samples[s++]);
            }

            subsets[i] = (float)(sum / subsetLength);
        }

        // find the peak so the waveform can be normalized
        // to the height of the image
        float normal = 0;
        for(float sample : subsets) {
            if(sample > normal)
                normal = sample;
        }

        // normalize and scale
        normal = 32768.0f / normal;
        for(int i = 0; i < subsets.length; i++) {
            subsets[i] *= normal;
            subsets[i] = (subsets[i] / 32768.0f) * (size.height / 2);
        }

        if (newTrack == true){
            g2d.setColor(Color.WHITE);
        } else {
            g2d.setColor(Color.GRAY);
        }

        // convert to image coords and do actual drawing
        for(int i = 0; i < subsets.length; i++) {
            int sample = (int) subsets[i];

            int posY = (size.height / 2) - sample;
            int negY = (size.height / 2) + sample;

            int x = i * boxWidth;

            if (newTrack == true){

                if (boxWidth == 1) {
                    g2d.drawLine(x, posY, x, negY);
                } else {
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(x + 1, posY + 1, boxWidth - 1, negY - posY - 1);
                    g2d.setColor(Color.WHITE);
                    g2d.drawRect(x, posY, boxWidth, negY - posY);
                    newTrack = false;
                }
            } else {
                if (boxWidth == 1) {
                    g2d.drawLine(x, posY, x, negY);
                } else {
                    g2d.setColor(Color.GRAY);
                    g2d.fillRect(x + 1, posY + 1, boxWidth - 1, negY - posY - 1);
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.drawRect(x, posY, boxWidth, negY - posY);
                }
            }
        }

        g2d.dispose();
        graphPanel.repaint();
        graphPanel.requestFocus();
    }

    // Load the track waveform
    static void loadImage() {

        //String audioFile = "";
        File file = new File("Full_Audio.wav");
        float[] samples;

        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(file);
            AudioFormat fmt = in.getFormat();

            if(fmt.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                throw new UnsupportedAudioFileException("unsigned");
            }

            boolean big = fmt.isBigEndian();
            int chans = fmt.getChannels();
            int bits = fmt.getSampleSizeInBits();
            int bytes = bits + 7 >> 3;

            int frameLength = (int)in.getFrameLength();
            int bufferLength = chans * bytes * 1024;

            samples = new float[frameLength];
            byte[] buf = new byte[bufferLength];

            int i = 0;
            int bRead;
            while((bRead = in.read(buf)) > -1) {

                for(int b = 0; b < bRead;) {
                    double sum = 0;

                    // (sums to mono if multiple channels)
                    for(int c = 0; c < chans; c++) {
                        if(bytes == 1) {
                            sum += buf[b++] << 8;

                        } else {
                            int sample = 0;

                            // (quantizes to 16-bit)
                            if(big) {
                                sample |= (buf[b++] & 0xFF) << 8;
                                sample |= (buf[b++] & 0xFF);
                                b += bytes - 2;
                            } else {
                                b += bytes - 2;
                                sample |= (buf[b++] & 0xFF);
                                sample |= (buf[b++] & 0xFF) << 8;
                            }

                            final int sign = 1 << 15;
                            final int mask = -1 << 16;
                            if((sample & sign) == sign) {
                                sample |= mask;
                            }

                            sum += sample;
                        }
                    }

                    samples[i++] = (float)(sum / chans);
                }
            }

        } catch(Exception e) {
            problem(e);
            return;
        }

        if(img == null) {
            img = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        }

        drawImage(samples);
    }

    static void problem(Object msg) {
        JOptionPane.showMessageDialog(null, String.valueOf(msg));
    }

    public void clear(Graphics g){
        super.paint(g);
        g.setColor(Color.WHITE);
    }

    public static void playMusic(String playTrack) {
        if (playingTrack == true) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            playingTrack = false;
            btnStopPlaying.setEnabled(false);
            btnPlay.setEnabled(true);
        } else {
            playingTrack = true;
            Media go = new Media(Paths.get(playTrack).toUri().toString());
            mediaPlayer = new MediaPlayer(go);
            mediaPlayer.play();
            btnStopPlaying.setEnabled(true);
            btnPlay.setEnabled(false);
            mediaPlayer.setOnEndOfMedia(() -> {
                btnStopPlaying.setEnabled(false);
                btnPlay.setEnabled(true);
                mediaPlayer.stop();
                mediaPlayer.dispose();
                playingTrack = false;
            });
        }

    }

    public static void createSettings() {
        try {
            File myObj = new File("settings.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                FileWriter myWriter = new FileWriter("settings.txt");
                myWriter.write(noiseReduction + " " + stopDuration + " " + threshold);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeSettings() {
        try {
            FileWriter myWriter = new FileWriter("settings.txt");
            myWriter.write(noiseReduction + " " + stopDuration + " " + threshold);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void readSettings() {
        try {
            File myObj = new File("settings.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                String nodeValue = data;
                String[] settings = nodeValue.split(" ");
                noiseReduction = settings[0];
                stopDuration = settings[1];
                threshold = settings[2];
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

