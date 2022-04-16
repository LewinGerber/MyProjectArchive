package ch.zli.onenote.service;

import ch.zli.onenote.entities.Content;
import ch.zli.onenote.entities.ContentData;
import ch.zli.onenote.entities.User;

import java.util.List;

public interface ContentService {
    // get the content by id
    public Content getContentByUserId(int id);

    // edit the content
    public Content editContent(ContentData contentData);

    // reset the content (content can't be deleted)
    public boolean resetContent(int id);

    // add content
    public void addContent(Content content);
}
