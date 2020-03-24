package Core.CachePackage;

public class Key extends Object {
    private long id;
    private Class cl;

    public Key(long id, Class cl){
        this.id=id;
        this.cl=cl;
    }
    @Override
    public boolean equals(Object obj){
        try {
            Key key = (Key) obj;
            return id==key.id && cl.toString().equals(key.cl.toString());
        }
        catch(Exception e){
            return false;
        }
    }
}
