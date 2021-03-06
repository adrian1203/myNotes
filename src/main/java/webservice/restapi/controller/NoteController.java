package webservice.restapi.controller;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservice.restapi.exception.RestException;
import webservice.restapi.model.Note;
import webservice.restapi.repository.NoteRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@RestController
public class NoteController {
    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/myNotes")
    public List<Note> getAllNotes(){
        LinkedList<Note> note=new LinkedList<>();
        List<Note>allNote= noteRepository.findAll();
        for (Note x: allNote){
            if(x.getArchived()==false){
                note.add(x);
            }
        }
        return note;
    }
    @PostMapping("/myNotes")
    public void createNote(@Valid @RequestBody Note note){
        noteRepository.save(note);
    }
    @GetMapping("/myNotes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Integer id){
        return noteRepository.findById(id).orElseThrow(()-> new RestException());
    }
    @PutMapping("/myNotes/{id}")
    public void updateNote(@Valid @RequestBody Note note, @PathVariable(value = "id") Integer id){
        Note selectNote=noteRepository.findById(id).orElseThrow(()-> new RestException());
        selectNote.setContent(note.getContent());
        selectNote.setTitle(note.getTitle());
        noteRepository.save(selectNote);
    }
    @DeleteMapping("myNotes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id")Integer id ){
        //Note selectNote=noteRepository.findById(id).orElseThrow(()-> new RestException());
        //noteRepository.delete(selectNote);
        noteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/myAltNotes")
    public List<Note> getAltNotes(){
        LinkedList<Note> altNote=new LinkedList<>();
        List<Note>notes= noteRepository.findAll();
        for (Note x: notes){
            if(x.getModified().getMonth()<(new Timestamp(System.currentTimeMillis())).getMonth() ||x.getModified().getYear()<(new Timestamp(System.currentTimeMillis())).getYear() ){
                altNote.add(x);
            }
        }
        return altNote;
    }
    @PutMapping("/archivedNote")
    public void archivedNote(@Valid @RequestBody Timestamp timestamp){
        List<Note> allNote=noteRepository.findAll();
        for(Note x: allNote){
            if(x.getCreated().before(timestamp)){
                System.out.println("archivizujeeeee");
                x.setArchived(true);
            }
        }
        noteRepository.saveAll(allNote);
    }
    @GetMapping("/myArchivedNote")
    public List<Note> getArchivedNote(){
        LinkedList<Note> archivedNote=new LinkedList<>();
        List<Note>notes= noteRepository.findAll();
        for (Note x: notes){
            if(x.getArchived()==true){
                archivedNote.add(x);
            }
        }
        return archivedNote;
    }

}
