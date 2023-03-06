import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.*;

public class Main {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        Transaction transaction = session.beginTransaction();

        PostEntity post = new PostEntity();
        post.setTitle("About us");

        CommentEntity comment_1 = new CommentEntity();
        comment_1.setAuthorName("John");
        comment_1.setPost(post);

        CommentEntity comment_2 = new CommentEntity();
        comment_2.setAuthorName("Mary");
        comment_2.setPost(post);

        CommentEntity comment_3 = new CommentEntity();
        comment_3.setAuthorName("Mark");
        comment_3.setPost(post);

        CommentEntity comment_4 = new CommentEntity();
        comment_4.setAuthorName("Peter");
        comment_4.setPost(post);

        post.setComments(new HashSet<>(Arrays.asList(comment_1,comment_2,comment_3,comment_4)));

        session.persist(post);
        transaction.commit();

        PostEntity postDB = (PostEntity) session.get(PostEntity.class, 11);
        System.out.println(postDB + " --> " + postDB.getComments());

        CommentEntity commentDB = (CommentEntity) session.get(CommentEntity.class,2);
        System.out.println(commentDB + "---->" + commentDB.getPost());

        session.close();
    }
}
