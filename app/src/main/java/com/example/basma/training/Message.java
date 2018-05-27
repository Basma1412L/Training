package com.example.basma.training;

public class Message {
    String id;
    String  sender;
    String content_sent;
    String date_sent;

    public Message()
    {

    }

    public Message(String sender, String content_sent, String date_sent) {
        this.sender = sender;
        this.content_sent = content_sent;
        this.date_sent = date_sent;
    }

    public Message(String id, String sender, String content_sent, String date_sent) {
        this.id = id;
        this.sender = sender;
        this.content_sent = content_sent;
        this.date_sent = date_sent;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent_sent() {
        return content_sent;
    }

    public void setContent_sent(String content_sent) {
        this.content_sent = content_sent;
    }

    public String getDate_sent() {
        return date_sent;
    }

    public void setDate_sent(String date_sent) {
        this.date_sent = date_sent;
    }
}
