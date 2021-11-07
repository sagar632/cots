package model;

public class read_article {

    private String title;
    private String publisher;

    private String content;
    private String date;

    public read_article(String title, String publisher, String content, String date) {
        this.title = title;
        this.publisher = publisher;
        this.content = content;
        this.date = date;
    }

    public read_article() {
    }

    public String getDate() {
        return date;
    }



 public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }






    public String getContent() {
        return content;
    }



}
