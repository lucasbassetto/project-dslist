package com.project.dslist.controllers;

import com.project.dslist.dto.GameDTO;
import com.project.dslist.dto.GameListDTO;
import com.project.dslist.dto.GameMinDTO;
import com.project.dslist.dto.ReplacementDTO;
import com.project.dslist.services.GameListService;
import com.project.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

    @Autowired
    private GameListService gameListService;

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameListDTO>> findAll() {
        List<GameListDTO> list = gameListService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{listId}/games")
    public ResponseEntity<List<GameMinDTO>> findByList(@PathVariable Long listId) {
        var result = gameService.findByList(listId);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(value = "/{listId}/replacement")
    public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
        gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
    }
}
