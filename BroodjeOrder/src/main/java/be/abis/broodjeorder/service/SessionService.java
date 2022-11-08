package be.abis.broodjeorder.service;

import be.abis.broodjeorder.exceptions.SessionNotFoundException;
import be.abis.broodjeorder.model.Session;

import java.util.List;

public interface SessionService {

    List<Session> getAllSessions();
    List<Session> getTodaysSessions() throws SessionNotFoundException;
}
