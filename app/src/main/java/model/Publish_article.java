package model;

public class Publish_article {

    private String id;
    private String title;
    private String content;

private String publisher;
private String date;
private String time;

  /*  public Publish_article(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }*/

    public Publish_article() {
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Publish_article(String id, String title, String content, String publisher, String date, String time) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
