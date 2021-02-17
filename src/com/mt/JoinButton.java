package com.mt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinButton extends javax.swing.JButton{
    ZoomMeeting meeting;
    public JoinButton() {

    }

    public JoinButton(Icon icon) {
        super(icon);
    }

    public JoinButton(String text) {
        super(text);
    }

    public JoinButton(Action a) {
        super(a);
    }

    public JoinButton(String text, Icon icon) {
        super(text, icon);
    }

    public JoinButton(String text, ZoomMeeting meeting) {
        super(text);
        this.meeting = meeting;
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                meeting.joinMeeting();
            }
        });
    }

    public ZoomMeeting getMeeting() {
        return meeting;
    }

    public void setMeeting(ZoomMeeting meeting) {
        this.meeting = meeting;
    }
}
