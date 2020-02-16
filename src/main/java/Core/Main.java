package Core;

import javax.persistence.*;
import java.util.List;

public class Main {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("Core");
    public static void main(String[] args){
        getSubs();
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void getSubs() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        String strQuery = "SELECT c FROM Table_Subs c WHERE c.id IS NOT NULL";
        TypedQuery<Table_Subs> tq = em.createQuery(strQuery, Table_Subs.class);
        List<Table_Subs> subs;
        try {
            subs = tq.getResultList();
            subs.forEach(sub->System.out.println(sub.getserverid()));
        }
        catch(NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }
}