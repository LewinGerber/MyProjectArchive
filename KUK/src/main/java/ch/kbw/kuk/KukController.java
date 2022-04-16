package ch.kbw.kuk;

import ch.kbw.kuk.pointAlgorithm.PointService;
import ch.kbw.kuk.sequentialAlgorithm.SequentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lewin Gerber
 * @version 26.10.2020
 * kuk
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class KukController {
    @Autowired
    PointService pointService;

    @Autowired
    SequentialService sequentialService;

    @Autowired
    SessionHandler sessionHandler;

    //find a session with id
    @RequestMapping(method = RequestMethod.POST, value = "/session")
    public Session findSession(@RequestBody String id){
        return sessionHandler.getSession(Integer.parseInt(id));
    }

    //gp for "generate points" with specific size
    @RequestMapping(method = RequestMethod.POST, value = "/generatePoints")
    public Session generatePoint(@RequestBody String size) {
        System.out.println("generating points");
        Session session = sessionHandler.addSession();
        session.generateDataPoints(Integer.parseInt(size.replace("=", "")));
        if(session.getPoints() != null)
            return session;
        return null;
    }

    //pdc = point algorithm do calculation
    @RequestMapping(method = RequestMethod.POST, value = "/pdc") //post?
    public Session pdc(@RequestBody int sessionId){
        //pointService.resolve(sessionHandler.getSession(sessionId));
        return sessionHandler.getSession(sessionId);
    }

    //sdc = sequential algorithm do calculation
    @RequestMapping(method = RequestMethod.POST, value = "/sdc")
    public Session sdc(@RequestBody String sessionId) {
        sequentialService.resolve(sessionHandler.getSession(Integer.parseInt(sessionId.replace("=", ""))));
        return sessionHandler.getSession(Integer.parseInt(sessionId.replace("=", "")));
    }
}
