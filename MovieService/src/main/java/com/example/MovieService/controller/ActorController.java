package com.example.MovieService.controller;

import com.example.MovieService.dto.ActorDto;
import com.example.MovieService.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    public List<ActorDto> getActors() {
        return actorService.getActors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getActorById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(actorService.getActorById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> addActor(@RequestBody ActorDto actorDto) {
        try{
            actorService.addActor(actorDto);
            return ResponseEntity.ok("Actor added successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateActor(@PathVariable Integer id, @RequestBody ActorDto actorDto) {
        try{
            actorService.updateActor(id, actorDto);
            return ResponseEntity.ok("Actor updated successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActor(@PathVariable Integer id) {
        try{
            actorService.deleteActor(id);
            return ResponseEntity.ok("Actor deleted successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
