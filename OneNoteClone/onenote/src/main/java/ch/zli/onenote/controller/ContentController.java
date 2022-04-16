package ch.zli.onenote.controller;

import ch.zli.onenote.entities.Content;
import ch.zli.onenote.entities.ContentData;
import ch.zli.onenote.service.ContentServiceImpl;
import ch.zli.onenote.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/content")
public class ContentController {

    /*
        This controller is part of the used MVC-pattern.
        It only distributes data from the view to the service.
    */

    @Autowired
    private ContentServiceImpl contentService;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity getContentByUserId(HttpServletRequest request) {
        // get the id of the user from the token
        String token = request.getHeader("Authorization");
        if (token != null) {
            int id = tokenService.getIdWithToken(token);
            Content content = contentService.getContentByUserId(id);
            if (content != null)
                return ResponseEntity.status(HttpStatus.OK).body(content);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity saveContent(@RequestBody ContentData contentData) {
        Content savedContent = contentService.editContent(contentData);
        if (savedContent!= null)
            return ResponseEntity.status(HttpStatus.OK).body(savedContent);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity resetContent(@PathVariable("id") int id) {
        boolean deleteStatus = contentService.resetContent(id);
        if (deleteStatus)
            return ResponseEntity.status(HttpStatus.OK).body(deleteStatus);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
