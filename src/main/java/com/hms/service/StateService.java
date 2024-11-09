package com.hms.service;

import com.hms.entity.Country;
import com.hms.entity.State;
import com.hms.repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {
    private StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public State addNewState(State state) {
        State newState = stateRepository.save(state);
        return newState;
    }

    public List<State> getStateList() {
        List<State> stateList = stateRepository.findAll();
        return stateList;
    }

    public void deleteState(long id) {
        stateRepository.deleteById(id);
    }
}
