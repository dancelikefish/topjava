package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> userRepository = new ConcurrentHashMap<>();
    private AtomicInteger userIdCounter = new AtomicInteger(0);

    {
        save(new User("user1", "test1@gmail.com", "123", Role.ROLE_USER));
        save(new User("user2", "test2@gmail.com", "123", Role.ROLE_USER));
    }
    @Override
    public User save(User user) {
        log.info("save user {}", user);
        if (user.isNew()) {
            user.setId(userIdCounter.incrementAndGet());
            userRepository.put(user.getId(), user);
            return user;
        }
        return userRepository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete user {}", id);
        return userRepository.entrySet().removeIf(entry -> entry.getKey() == id);
    }

    @Override
    public User get(int id) {
        log.info("get user {}", id);
        return userRepository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll users");
        List<User> users = new ArrayList<>(userRepository.values());
        users.sort((o1, o2) -> {
            if (o1.getName().equals(o2.getName())) {
                return o1.getEmail().compareTo(o2.getEmail());
            } else
                return o1.getName().compareTo(o2.getName());
        });
        return users;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail user{}", email);
        return userRepository.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }
}
