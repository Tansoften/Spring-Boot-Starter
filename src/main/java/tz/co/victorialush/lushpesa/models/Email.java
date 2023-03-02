package tz.co.victorialush.lushpesa.models;

public class Email {
    private String from;
    private String address;
    private String subject;
    private String cc;
    private String body;
    private String attachment;

    public Email(Email email) {
        this.from = email.from;
        this.address = email.address;
        this.subject = email.subject;
        this.cc = email.cc;
        this.body = email.body;
        this.attachment = email.attachment;
    }

    public Email(){}

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

}
