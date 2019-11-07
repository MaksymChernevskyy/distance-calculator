package com.imoovo.business.database;

import com.imoovo.business.entity.User;
import java.util.List;

public interface UserDatabase {
  User save(User user);

  User findById(Long id);

  List<User> findAll();

  Boolean isUserExist(Long id);
}
