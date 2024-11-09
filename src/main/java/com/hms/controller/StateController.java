package com.hms.controller;

import com.hms.entity.State;
import com.hms.service.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/state")
public class StateController {
    private StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @PostMapping("/addState")
    public ResponseEntity<?> addState(
            @RequestBody State state
            ){
        State addedState = stateService.addNewState(state);
        return new ResponseEntity<>(addedState, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<State>> getStateList(){
        List<State> stateList = stateService.getStateList();
        return new ResponseEntity<>(stateList, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteState(
            @RequestParam long id
    ){
        stateService.deleteState(id);
        return new ResponseEntity<>("State deleted", HttpStatus.OK);
    }
}
