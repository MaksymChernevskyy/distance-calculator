package com.imoovo.business.usecases;

import com.imoovo.business.database.UserDatabase;
import com.imoovo.business.entity.User;
import org.springframework.stereotype.Component;

@Component
public class SaveUserUseCase {
  private UserDatabase userDatabase;
  private User user;

  public SaveUserUseCase withUserDatabase(UserDatabase userDatabase) {
    this.userDatabase = userDatabase;
    return this;
  }

  public SaveUserUseCase forUser(User user) {
    this.user = user;
    return this;
  }

  public User run() {
    User user = createUser();
    return user;
  }

  private User createUser() {
    return userDatabase.save(user);
  }
}
