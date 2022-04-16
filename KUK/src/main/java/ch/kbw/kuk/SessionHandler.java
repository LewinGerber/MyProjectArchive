package ch.kbw.kuk;

import org.springframework.stereotype.Service;
import java.util.ArrayList;

/**
 * @author Lewin Gerber
 * @version 12.11.2020
 * kuk
 */

@Service
public class SessionHandler {
    private int publicId = 0;
    private ArrayList<Session> sessions;

    public SessionHandler() {
        this.sessions = new ArrayList<Session>();
    }

    public Session addSession(){
        publicId += 1;
        Session session = new Session(publicId);
        sessions.add(session);
        return session;
    }

    //GETTER & SETTER
    public Session getSession(Session session) {
        for(Session s : this.sessions) {
            if(s.getId() == session.getId())
                return s;
        }
        return null;
    }

    public Session getSession(int id) {
        for(Session s : this.sessions) {
            if(s.getId() == id)
                return s;
        }
        return null;
    }

    public int getPublicId() {
        return publicId;
    }

    public void setPublicId(int publicId) {
        this.publicId = publicId;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }
}
