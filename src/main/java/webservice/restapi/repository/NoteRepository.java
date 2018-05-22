package webservice.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webservice.restapi.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer>{


}
