package com.imoovo.business.usecases;

import com.imoovo.business.database.UserDatabase;
import com.imoovo.business.entity.User;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GetAllUseCase {
  private UserDatabase userDatabase;

  public GetAllUseCase withUserDatabase(UserDatabase userDatabase) {
    this.userDatabase = userDatabase;
    return this;
  }

  public List<User> run() {
    return userDatabase.findAll();
  }
}
