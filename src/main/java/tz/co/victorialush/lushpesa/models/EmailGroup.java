package tz.co.victorialush.lushpesa.models;

public class EmailGroup {
    private String from;
    private To []to;
    private String subject;
    private String cc;
    private String body;
    private FileRecords attachment;

    public EmailGroup(EmailGroup email) {
        this.from = email.from;
        this.to = email.to;
        this.subject = email.subject;
        this.cc = email.cc;
        this.body = email.body;
        this.attachment = email.attachment;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public To[] getTo() {
        return to;
    }

    public void setTo(To[] to) {
        this.to = to;
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

    public FileRecords getAttachment() {
        return attachment;
    }

    public void setAttachment(FileRecords attachment) {
        this.attachment = attachment;
    }
}
