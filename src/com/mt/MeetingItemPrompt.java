package com.mt;

import javax.swing.*;
import java.awt.*;

public class MeetingItemPrompt {

    /**
     * This method can be called in 2 ways.
     * <br />
     * Way 1: MeetingItemPrompt.editInfo(...)
     * <br />
     * When used in this way, the return value is null. The function simply modifies its parameter.
     * <br />
     * Way 2: model.addElement(MeetingItemPrompt.editInfo(null)
     * <br />
     * When used this way, the return value is a ZoomMeeting (as a new one is being created). No old ZoomMeeting objects are being modified, so the parameter can be null.
     * @param zoomMeeting if null, create a new object. If not null, edit an existing object.
     * @return A new ZoomMeeting if zoomMeeting is null. If not null, edit zoomMeeting.
     */
    public static ZoomMeeting editInfo(ZoomMeeting zoomMeeting) {
        JPanel dialogPanel = new JPanel();
        BoxLayout bl = new BoxLayout(dialogPanel, BoxLayout.Y_AXIS);
        dialogPanel.setLayout(bl);
        JTextField meetingName = new JTextField(zoomMeeting != null ? zoomMeeting.meeting_Name : "", 20),
                meetingID = new JTextField(zoomMeeting != null ? zoomMeeting.meeting_ID : "", 20),
                meetingPass = new JTextField(zoomMeeting != null ? zoomMeeting.meeting_PSWD: "", 20),
                meetingUsername = new JTextField(zoomMeeting != null ? zoomMeeting.join_Name: "", 20),
                date = new JTextField(zoomMeeting != null ? zoomMeeting.date : "", 20);
        dialogPanel.add(new JLabel("Meeting Name:"));
        dialogPanel.add(meetingName);
        dialogPanel.add(new JLabel("Meeting ID:"));
        dialogPanel.add(meetingID);
        dialogPanel.add(new JLabel("Meeting Password:"));
        dialogPanel.add(meetingPass);
        dialogPanel.add(new JLabel("Meeting Username:"));
        dialogPanel.add(meetingUsername);
        dialogPanel.add(new JLabel("Meeting Date:"));
        dialogPanel.add(date);

        int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Edit Meeting", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(result == JOptionPane.OK_OPTION && zoomMeeting != null) {
            zoomMeeting.setMeeting_Name(meetingName.getText());
            zoomMeeting.setMeeting_ID(meetingID.getText());
            zoomMeeting.setMeeting_PSWD(meetingPass.getText());
            zoomMeeting.setJoin_Name(meetingUsername.getText());
            zoomMeeting.setDate(date.getText());
        } else if(zoomMeeting == null) {
            return new ZoomMeeting(meetingName.getText(), meetingID.getText(), meetingPass.getText(), meetingUsername.getText(), date.getText());
        }
        return null; // i don't use it like this - this is for calling it without assigning it to a variable
    }
}
