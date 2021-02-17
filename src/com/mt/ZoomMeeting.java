package com.mt;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ZoomMeeting implements Serializable {

    String meeting_Name, meeting_ID, meeting_PSWD, join_Name, date;
    public ZoomMeeting(String meeting_Name, String meeting_ID, String meeting_PSWD, String join_Name, String date) {
        meeting_ID = meeting_ID.replace("-", ""); // this way if you use xxx-xxx-xxxx it gets shortened. no validation for length
        if(join_Name.isEmpty()) {
            join_Name = "zm_"+new Random().nextInt(10000);
        }
        join_Name = join_Name.replace(" ", "%20");
        this.meeting_ID = meeting_ID;
        this.meeting_PSWD = meeting_PSWD;
        this.join_Name = join_Name;
        this.meeting_Name = meeting_Name;
        this.date = date;
    }


    public String getDate() {
        return date;
    }

    public Date getDateParsed() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("E hh:mm a");
        Date d = sdf.parse(this.date);
        return d;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMeeting_ID() {
        return meeting_ID;
    }

    public void setMeeting_ID(String meeting_ID) {
        meeting_ID = meeting_ID.replace("-", "");
        this.meeting_ID = meeting_ID;
    }

    public String getMeeting_PSWD() {
        return meeting_PSWD;
    }

    public void setMeeting_PSWD(String meeting_PSWD) {
        this.meeting_PSWD = meeting_PSWD;
    }

    public String getJoin_Name() {
        return join_Name;
    }

    public void setJoin_Name(String join_Name) {
        this.join_Name = join_Name;
    }

    public String getMeeting_Name() {
        return meeting_Name;
    }

    public void setMeeting_Name(String meeting_Name) {
        this.meeting_Name = meeting_Name;
    }

    public String getMeetingLink() {
        /*
        Desktop: zoommtg://
        Mobile: zoomus://
         */
        return String.format("zoommtg://zoom.us/join?confno=%s&pwd=%s&uname=%s", this.meeting_ID, this.meeting_PSWD, this.join_Name);
    }

    public void joinMeeting() {
        URLOpener.openURL(this.getMeetingLink());
    }

    @Override
    public String toString() {
        return String.format("%s | %s", this.meeting_Name, this.date);
    }

}
