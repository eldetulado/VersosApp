package com.example.oso.versosbiblicos;

public class Message {
    private String verseContent;
    private String verseAddress;

    public Message() {

    }

    public Message(String verseContent, String verseAddres) {
        this.verseContent = verseContent;
        this.verseAddress = verseAddres;
    }

    public String getVerseContent() {
        return verseContent;
    }

    public void setVerseContent(String verseContent) {
        this.verseContent = verseContent;
    }

    public String getVerseAddress() {
        return verseAddress;
    }

    public void setVerseAddress(String verseAddress) {
        this.verseAddress = verseAddress;
    }
}
