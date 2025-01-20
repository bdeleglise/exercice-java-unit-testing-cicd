package com.unitTesting.crm.service;

import com.unitTesting.crm.domain.User;
import com.unitTesting.crm.exception.PhoneNumberExistsException;
import com.unitTesting.crm.exception.UserInvalidInfoException;
import com.unitTesting.crm.repository.UserRepository;

public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public String getUserName(Long id) {
    User user = userRepository.findById(id);
    return user != null ? user.name : "User not found";
  }

  public void createNewUser(User user) {
    if (user.name == null || user.phoneNumber == null || user.role == null) {
      throw new UserInvalidInfoException("User information is missing");
    }

    User userWithPhoneNumber = userRepository.findByPhoneNumber(user.phoneNumber);
    if (userWithPhoneNumber != null) {
      throw new PhoneNumberExistsException("This phone number is already in use");
    }
    userRepository.save(user);
  }

  public void deleteUser(Long id) {
    // TODO :: Implémenter cette méthode
    // Si l'utilisateur n'existe pas, alors rien ne se passe
    // si l'utilisateur existe mais a le rôle admin, alors renvoyer une exception NotAllowedException
  }

  // push on test branch
}
