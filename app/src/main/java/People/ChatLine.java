package People;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatLine {
    private int id;
    private String name;
    private String lineText;
    private Date date;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLineText() {
        return lineText;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLineText(String lineText) {
        this.lineText = lineText;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
