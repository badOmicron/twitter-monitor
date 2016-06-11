package mx.bhit.omicron.app.restful.model;

import java.io.Serializable;
import java.util.Date;

public class TwitterMessage implements Serializable {
    public TwitterMessage() {
        // TODO Auto-generated constructor stub
    }

    public TwitterMessage(long idItem, Date timeStamp, String message, String autor, String link) {
        super();
        this.idItem = idItem;
        this.timeStamp = timeStamp;
        this.message = message;
        this.autor = autor;
        this.link = link;
    }

    /**
     *
     */
    private static final long serialVersionUID = 5556133760694766494L;

    private long idItem;
    private Date timeStamp;
    private String message;
    private String autor;
    private String link;

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TwitterMessage [idItem=")
            .append(idItem)
            .append(", timeStamp=")
            .append(timeStamp)
            .append(", message=")
            .append(message)
            .append(", autor=")
            .append(autor)
            .append(", link=")
            .append(link)
            .append("]");
        return builder.toString();
    }

}
