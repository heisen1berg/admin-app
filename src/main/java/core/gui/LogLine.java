package core.gui;

public class LogLine {
    public static final int TYPE_COMMENT_RECEIVED=0;
    public static final int TYPE_BAN_CREATED=1;
    public static final int TYPE_ERROR=2;
    public static final int TYPE_NEW_SUBSCRIPTION=3;
    public static final int TYPE_SUBSCRIPTION_INACTIVE=4;

    private String text;
    private int type;

    public LogLine(String text, int type){
        this.text=text;
        this.type=type;
    }
    public int getType(){return type;}
    public String getText(){return text;}
}
