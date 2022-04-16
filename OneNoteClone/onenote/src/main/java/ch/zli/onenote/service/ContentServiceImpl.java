package ch.zli.onenote.service;

import ch.zli.onenote.entities.Content;
import ch.zli.onenote.entities.ContentData;
import ch.zli.onenote.entities.Text;
import ch.zli.onenote.entities.User;
import ch.zli.onenote.entities.repositories.ContentRepository;
import ch.zli.onenote.entities.repositories.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentServiceImpl implements ContentService{

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private TextRepository textRepository;

    @Override
    public Content getContentByUserId(int id) {
        List<Content> content = contentRepository.findByUserId(id);
        if (content.size() > 0)
            return content.get(0);
        return null;
    }

    @Override
    public Content editContent(ContentData contentData) {
        Optional<Content> contentOpt = contentRepository.findById(contentData.getId());
        if(contentOpt.isPresent()) {
            Content editedContent = contentOpt.get();
            if (editedContent != null) {
                // swap text of content
                List<Text> textList = editedContent.getText();
                if (textList.size() > 0 ) {
                    Text editedText = editedContent.getText().get(0);
                    editedText.setText(contentData.getText());
                    textRepository.save(editedText);
                    // save the content with the new text
                    editedContent = contentRepository.save(editedContent);
                    return editedContent;
                } else {
                    Text newText = new Text();
                    newText.setContent(editedContent);
                    newText.setText(contentData.getText());
                    textRepository.save(newText);
                }
            }
        }
        return null;
    }

    @Override
    public boolean resetContent(int id) {
        // get the old content
        Optional<Content> oldContent = contentRepository.findById(id);
        if (!oldContent.isPresent() || oldContent == null )
            return false;
        // get the old user
        User user = oldContent.get().getUser();
        // return if the user can't be found
        if (user == null) return false;
        // remove old content
        contentRepository.delete(oldContent.get());
        // create new empty content object
        Content newContent = new Content();
        // set the same user
        newContent.setUser(user);
        // save new content
        contentRepository.save(newContent);
        return true;
    }

    @Override
    public void addContent(Content content) {
        contentRepository.save(content);
    }

}
