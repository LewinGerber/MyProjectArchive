package ch.zli.onenote.entities.repositories;

import ch.zli.onenote.entities.Content;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends CrudRepository<Content, Integer> {
    @Query("SELECT c FROM Content c WHERE c.user.id=:id")
    public List<Content> findByUserId(@Param("id") int id);
}
