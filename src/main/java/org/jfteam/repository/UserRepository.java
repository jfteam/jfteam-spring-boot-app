package org.jfteam.repository;

import org.jfteam.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-08-16
 * Time: 下午6:21
 */
@Repository
public class UserRepository {

    private static AtomicInteger idGenerator = new AtomicInteger(1);

    private static Map<Integer, User> userStore = new ConcurrentHashMap<>();

    public void save(Mono<User> user) {
        Assert.notNull(user, "user must be not null.");
        user.subscribe(user1 -> {
            if (StringUtils.hasText(user1.getName())) {
                final int userId = idGenerator.get();
                user1.setId(userId);
                userStore.put(userId, user1);
            }
        });
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>(userStore.size());
        if (!CollectionUtils.isEmpty(userStore)) {
            list.addAll(userStore.values());
        }
        return list;
    }
}
