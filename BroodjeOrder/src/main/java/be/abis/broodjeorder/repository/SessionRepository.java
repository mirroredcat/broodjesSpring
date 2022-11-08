package be.abis.broodjeorder.repository;

import be.abis.broodjeorder.exceptions.SessionNotFoundException;
import be.abis.broodjeorder.model.Session;

import java.util.List;

public interface SessionRepository {
    List<Session> findAllSessions();
    List<Session> findSessionsOfToday() throws SessionNotFoundException;
}
