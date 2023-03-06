import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "comment", schema = "hibernate")
public class CommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_comment", nullable = false)
    private int idComment;
    @Basic
    @Column(name = "authorName", nullable = true, length = 45)
    private String authorName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_post", nullable = false)
    private PostEntity post;


    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return idComment == that.idComment && Objects.equals(authorName, that.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComment, authorName);
    }

    @Override
    public String toString() {
        return "Коментар: id == " + idComment + " Автор == " + authorName + " ";
    }
}
