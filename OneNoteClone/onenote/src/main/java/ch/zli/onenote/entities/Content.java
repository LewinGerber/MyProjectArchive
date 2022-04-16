package ch.zli.onenote.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "edited_date")
    @Nullable
    private Date editedDate;

    @OneToOne//(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private User user;

    @OneToMany(mappedBy = "content",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    private List<Text> text;

    // GETTER & SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(Date editedDate) {
        this.editedDate = editedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Text> getText() {
        return text;
    }

    public void setText(List<Text> text) {
        this.text = text;
    }
}
