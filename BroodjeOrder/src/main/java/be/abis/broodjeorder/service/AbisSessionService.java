package be.abis.broodjeorder.service;


import be.abis.broodjeorder.exceptions.SessionNotFoundException;
import be.abis.broodjeorder.model.Session;
import be.abis.broodjeorder.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbisSessionService implements SessionService {

    @Autowired
    SessionRepository sessionRepository;

    public AbisSessionService() {
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAllSessions();
    }

    @Override
    public List<Session> getTodaysSessions() throws SessionNotFoundException {
        return sessionRepository.findSessionsOfToday();

    }
}
