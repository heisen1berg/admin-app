package Core.DBPackage;

import Core.DataStructures.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//post repository
public interface PostRepo extends JpaRepository<Post, Long> {
}
