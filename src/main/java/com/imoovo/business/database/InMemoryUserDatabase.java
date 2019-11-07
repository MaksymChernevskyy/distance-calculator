package com.imoovo.business.database;

import static java.util.Objects.isNull;

import com.imoovo.business.entity.FindUserException;
import com.imoovo.business.entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserDatabase implements UserDatabase {
  private static Map<Long, User> users = new HashMap<>();
  private static AtomicLong latestUserId = new AtomicLong(0);

  @Override
  public User save(User user) {
    if (isNull(user)) {
      throw new DatabaseOperationException("User cannot be null");
    }
    if (isUserExist(user.getId())) {
      throw new DatabaseOperationException(String.format("User with id %d already exist", user.getId()));
    }
    Long id = latestUserId.incrementAndGet();
    user.setId(id);
    users.put(id, user);
    return user;
  }

  @Override
  public User findById(Long id) {
    if (isNull(id)) {
      throw new FindUserException("Name cannot be null");
    }
    if (!isUserExist(id)) {
      throw new FindUserException(String.format("Not found user with id: %d", id));
    }
    return users.get(id);
  }

  public List<User> findAll() {
      return new ArrayList<>(users.values());
  }

  @Override
  public Boolean isUserExist(Long id) {
    return users.containsKey(id);
  }
}
