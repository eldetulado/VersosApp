package com.example.oso.versosbiblicos;

public class Message {
    private String verseContent;
    private String verseAddress;

    public Message() {

    }

    public Message(String verseContent, String verseAddress) {
        this.verseContent = verseContent;
        this.verseAddress = verseAddress;
    }

    public String getVerseContent() {
        return verseContent;
    }

    public String getVerseAddress() {
        return verseAddress;
    }

}
