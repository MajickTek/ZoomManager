package com.mt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {

    /*
    TODO:
    Refactor class:
    Currently there are lots of inner classes which could easily be separated.
    One obvious benefit is geting rid of DefaultListModel<ZoomMeeting> finalZmmodel = zmmodel; on line ~55
     */
    public static final int MEETING_LIMIT = 16;
    public static int meetingCounter = 0;
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e){
            Debug.callCrashDialog("Error", "The System LaF could not be set. Reason: " + e.getMessage(), Debug.ERR);
        }

        final JFrame window = new JFrame("Zoom Manager");
        window.setSize(800, 600);
        window.setResizable(false);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            window.setIconImage(ImageIO.read(new File("res/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
            Debug.callCrashDialog("Error", "An erorr occured while trying to set the App's ImageIcon.\nReason: " + e.getMessage(), Debug.ERR);
        }
        DefaultListModel<ZoomMeeting> zmmodel = new DefaultListModel<>();
        //zmmodel.addElement(new ZoomMeeting("Test", "96151030602", "a25IYjRvS0lBL0pvWVlma0FWWWpjZz09", "Test123"));

        Path workingDir = Paths.get(System.getProperty("user.home"), "Documents", "MajickTek", "ZoomManager");
        try {
            Files.createDirectories(workingDir);
            if(Files.exists(workingDir)) {
                FileInputStream fis = new FileInputStream(Paths.get(workingDir.toString(), "ZoomMeetings.lstbin").toString());
                ObjectInputStream ois = new ObjectInputStream(fis);
                zmmodel = (DefaultListModel<ZoomMeeting>) ois.readObject();
                Debug.callCrashDialog("Success!", "File has loaded successfully!\nUnless it hasn't, in which case you may see another popup in the near future.", Debug.INF);
            }
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
            //Debug.callCrashDialog("Error", String.format("Something failed while loading Save File paths/files.%nReason:%s%nFor more, view the stack trace in the debug console.%nP.S. This could just mean that there is no previous Save File.", ioException.getMessage()), Debug.ERR);
        }

        JList<ZoomMeeting> zmlist = new JList<>(zmmodel);
        zmlist.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "addItem");
        DefaultListModel<ZoomMeeting> finalZmmodel = zmmodel;

        zmlist.getActionMap().put("addItem", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                meetingCounter++;
                if(meetingCounter <= MEETING_LIMIT) {
                    //finalZmmodel.addElement(MeetingItemPrompt.getPrompt());
                    finalZmmodel.addElement(MeetingItemPrompt.editInfo(null));
                    zmlist.updateUI();
                }
            }
        });



        zmlist.getInputMap().put(KeyStroke.getKeyStroke("DELETE"), "removeItem");
        zmlist.getActionMap().put("removeItem", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalZmmodel.removeRange(zmlist.getMinSelectionIndex(), zmlist.getMaxSelectionIndex());
                zmlist.updateUI();
            }
        });

        zmlist.getInputMap().put(KeyStroke.getKeyStroke("ctrl R"), "refresh");
        zmlist.getActionMap().put("refresh", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zmlist.updateUI();
            }
        });

        zmlist.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "showInfo");
        zmlist.getActionMap().put("showInfo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MeetingItemPrompt.editInfo(finalZmmodel.elementAt(zmlist.getSelectedIndex()));
                zmlist.updateUI();
            }
        });

        zmlist.getInputMap().put(KeyStroke.getKeyStroke("E"), "launchMeeting");
        zmlist.getActionMap().put("launchMeeting", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalZmmodel.elementAt(zmlist.getSelectedIndex()).joinMeeting();

            }
        });

        zmlist.getInputMap().put(KeyStroke.getKeyStroke("ctrl S"), "saveList");
        zmlist.getActionMap().put("saveList", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Path path = Paths.get(System.getProperty("user.home"), "Documents", "MajickTek", "ZoomManager");
                try {
                    Files.createDirectories(path);
                    if(Files.exists(path)) {
                        FileOutputStream fos = new FileOutputStream(Paths.get(path.toString(), "ZoomMeetings.lstbin").toString());
                        ObjectOutputStream ost = new ObjectOutputStream(fos);
                        ost.writeObject(finalZmmodel);
                        Debug.callCrashDialog("Success!", "File has saved successfully!\nUnless it hasn't, in which case you may see another popup in the near future.", Debug.INF);
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    Debug.callCrashDialog("Error", String.format("Something failed while creating Save File paths/files.%nReason:%s%nFor more, view the stack trace in the debug console.", ioException.getMessage()), Debug.ERR);
                }

            }
        });

        window.add(zmlist, BorderLayout.CENTER);



        JMenuBar mb = new JMenuBar();
        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel aboutPanel = new JPanel();
                aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.Y_AXIS));
                JTextArea info = new JTextArea("A minimalist, open-source app to manage your Zoom meetings. Intended for use by students.\nAuthor: MajickTek\nhttps://github.com/MajickTek\nCurrent meeting limit: " + MEETING_LIMIT);
                info.setEditable(false);
                aboutPanel.add(info);
                JLabel iconLabel = new JLabel();
                try {
                    iconLabel.setIcon(new ImageIcon(ImageIO.read(new File("res/icon.png")).getScaledInstance(64, 64, Image.SCALE_SMOOTH)));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                };
                aboutPanel.add(iconLabel);
                Debug.callCrashDialog("About", aboutPanel, Debug.INF);
            }
        });
        JMenuItem guide = new JMenuItem("Guide");
        guide.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGuideBox();
            }
        });
        help.add(about);
        help.add(guide);
        mb.add(help);

        window.add(mb, BorderLayout.NORTH);
        window.setVisible(true);
    }

    private static void openGuideBox() {
        Debug.callCrashDialog("Guide", "Add Meeting: SPACE\nRemove Single Meeting: Select with LEFT MOUSE, DELETE\nRemove Multiple Meetings: Select with SHIFT+LEFT MOUSE, DELETE\nLaunch Meeting: E\nRefresh: CTRL + R", Debug.INF);
    }
}
