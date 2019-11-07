package com.imoovo.business.service;

import com.imoovo.business.database.CalculationHistoryDatabase;
import com.imoovo.business.database.InMemoryUserDatabase;
import com.imoovo.business.entity.User;
import com.imoovo.business.usecases.CalculateDistanceUseCase;
import com.imoovo.business.usecases.FindDistanceByIdUseCase;
import com.imoovo.business.usecases.GetAllCalculatedDistancesUseCase;
import com.imoovo.business.usecases.GetAllUseCase;
import com.imoovo.business.usecases.GetByIdUseCase;
import com.imoovo.business.usecases.SaveUserUseCase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private GetAllUseCase getAllUseCase;
  private GetByIdUseCase getByIdUseCase;
  private SaveUserUseCase saveUserUseCase;
  private InMemoryUserDatabase inMemoryUserDatabase;

  @Autowired
  public UserService(GetAllUseCase findAllUseCase,
                     GetByIdUseCase fIndByIdUseCase,
                     SaveUserUseCase saveUserUseCase,
                     InMemoryUserDatabase inMemoryUserDatabase) {
    this.getAllUseCase = findAllUseCase;
    this.getByIdUseCase = fIndByIdUseCase;
    this.saveUserUseCase = saveUserUseCase;
    this.inMemoryUserDatabase = inMemoryUserDatabase;
  }

  public User saveUser(User user) {
    return saveUserUseCase
        .withUserDatabase(inMemoryUserDatabase)
        .forUser(user)
        .run();
  }

  public User getUserById(Long id) {
    return getByIdUseCase
        .withUserDatabase(inMemoryUserDatabase)
        .forUserId(id)
        .run();
  }

  public List<User> getAllUsers() {
    return getAllUseCase
        .withUserDatabase(inMemoryUserDatabase)
        .run();
  }
}
