package be.abis.broodjeorder.controller;

import be.abis.broodjeorder.exceptions.PersonNotFoundException;
import be.abis.broodjeorder.exceptions.SessionNotFoundException;
import be.abis.broodjeorder.model.Person;
import be.abis.broodjeorder.model.Session;
import be.abis.broodjeorder.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "sessions")
public class SessionController {

    @Autowired
    SessionService sessionService;

    @GetMapping(path ="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends Object> getTodaysSessions() {

        try {
            List<Session> sessionList = sessionService.getTodaysSessions();
            return new ResponseEntity<>(sessionList, HttpStatus.OK);
        } catch (SessionNotFoundException e) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ApiError error = new ApiError("Session not found", status.value(), e.getMessage());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
            return new ResponseEntity<ApiError>(error, responseHeaders, status);
        }
    }

}
