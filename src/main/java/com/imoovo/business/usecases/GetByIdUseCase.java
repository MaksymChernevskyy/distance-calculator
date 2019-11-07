package com.imoovo.business.usecases;

import com.imoovo.business.database.UserDatabase;
import com.imoovo.business.entity.User;
import org.springframework.stereotype.Component;

@Component
public class GetByIdUseCase {
  private UserDatabase userDatabase;
  private Long id;

  public GetByIdUseCase withUserDatabase(UserDatabase userDatabase) {
    this.userDatabase = userDatabase;
    return this;
  }

  public GetByIdUseCase forUserId(Long id) {
    this.id = id;
    return this;
  }

  public User run() {
    return userDatabase.findById(id);
  }
}
