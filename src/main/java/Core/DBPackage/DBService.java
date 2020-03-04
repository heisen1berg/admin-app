package Core.DBPackage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DBService {

    @Autowired
    private Repo repo;

    public List<Post> list() {
        return repo.findAll();
    }
    public boolean save(Post p){
        try {
            repo.save(p);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }
}