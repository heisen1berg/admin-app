package Core.CachePackage;

public class Key {
    private Class cl;
    private Long key;
    public Key(Long key, Class cl){
        this.cl=cl;
        this.key=key;
    }
    public Long getKey(){
        return key;
    }
    public String getClassString(){
        String s=cl.toString();
        return s.substring(s.lastIndexOf(".")+1,s.length());
    }
}
