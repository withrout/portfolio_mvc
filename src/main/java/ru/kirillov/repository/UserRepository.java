package ru.kirillov.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.kirillov.entity.User;
import ru.kirillov.response.UserResponse;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final SessionFactory sessionFactory;

    public User createUser(User user) {
        var session = sessionFactory.getCurrentSession();
        session.save("user", user);
        session.flush();
        return user;
    }

    public UserResponse getUserById(long id) {
        var session = sessionFactory.getCurrentSession();
        var user = session.get(User.class, id);
        session.flush();
        return new UserResponse(user.getId(), user.getName());
    }
}
