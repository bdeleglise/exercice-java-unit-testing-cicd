package com.aitesting.crm;

import com.unitTesting.crm.domain.User;
import com.unitTesting.crm.exception.NotAllowedException;
import com.unitTesting.crm.exception.PhoneNumberExistsException;
import com.unitTesting.crm.exception.UserInvalidInfoException;
import com.unitTesting.crm.repository.UserRepository;
import com.unitTesting.crm.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserServiceTest {

  private UserRepository userRepository;
  private UserService userService;

  @BeforeEach
  public void setUp() {
    userRepository =  Mockito.mock(UserRepository.class);
    userService = new UserService(userRepository);
  }

  @Test
  public void getUserName_WhenUserExists_ReturnsUserName() {
    // Arrange
    Long userId = 1L;
    User user = new User(userId, "John Doe", "0606060606", "user");
    Mockito.when(userRepository.findById(userId)).thenReturn(user);

    // Act
    String result = userService.getUserName(userId);

    // Assert
    Assertions.assertEquals("John Doe", result);
  }

  @Test
  public void getUserName_WhenUserDoesNotExist_ReturnsNotFoundMessage() {
    // Arrange
    Long userId = 2L;
    Mockito.when(userRepository.findById(userId)).thenReturn(null);

    // Act
    String result = userService.getUserName(userId);

    // Assert
    Assertions.assertEquals("User not found", result);
  }

  @Test
  public void createNewUser_WhenUserIsValid_SavesUser() {
    // Arrange
    User newUser = new User(null, "John Doe", "1234567890", "user");
    Mockito.when(userRepository.findByPhoneNumber(newUser.phoneNumber)).thenReturn(null);

    // Act
    userService.createNewUser(newUser);

    // Assert
    Mockito.verify(userRepository).save(newUser);

  }

  @Test
  public void createNewUser_WhenInfoIsMissing_ThrowsUserInvalidInfoException() {
    // Arrange
    User invalidUser = new User(null, null, null, null);

    // Act & Assert
    UserInvalidInfoException thrown = Assertions.assertThrows(UserInvalidInfoException.class,
            () -> userService.createNewUser(invalidUser));

    Assertions.assertEquals("User information is missing", thrown.getMessage());
  }

  @Test
  public void createNewUser_WhenPhoneNumberExists_ThrowsPhoneNumberExistsException() {
    // Arrange
    User existingUser = new User(null, "Jane Doe", "1234567890", "user");

    User newUserWithExistingPhone = new User(null, "John Smith", "1234567890", "user"); // Same phone number
    Mockito.when(userRepository.findByPhoneNumber("1234567890")).thenReturn(existingUser);

    // Act & Assert
    PhoneNumberExistsException thrown = Assertions.assertThrows(PhoneNumberExistsException.class,
            () -> userService.createNewUser(newUserWithExistingPhone));

    Assertions.assertEquals("This phone number is already in use", thrown.getMessage());
  }

  //========================= Deuxième partie : Delete a User  ============================

  @Test
  public void deleteUser_WhenUserDoesNotExist_DoesNotCallDelete() {
    // Arrange
    Long userId = 1L;
    Mockito.when(userRepository.findById(userId)).thenReturn(null); // User does not exist

    // Act
    userService.deleteUser(userId);

    // Assert
    Mockito.verify(userRepository, Mockito.never()).deleteUser(userId); // Ensure deleteUser is never called
  }

  @Test
  public void deleteUser_WhenUserIsAdmin_ThrowsNotAllowedException() {
    // Arrange
    Long adminUserId = 2L;
    User adminUser = new User(adminUserId, "Admin User", "admin@example.com", "admin");
    Mockito.when(userRepository.findById(adminUserId)).thenReturn(adminUser); // Return an admin user

    // Act & Assert
    NotAllowedException thrown = Assertions.assertThrows(NotAllowedException.class,
            () -> userService.deleteUser(adminUserId));

    Assertions.assertEquals("You cannot delete an admin user", thrown.getMessage());
    Mockito.verify(userRepository, Mockito.never()).deleteUser(adminUserId); // Ensure deleteUser is never called
  }

  @Test
  public void deleteUser_WhenUserIsRegular_CallsDelete() {
    // Arrange
    Long userId = 3L;
    User regularUser = new User(userId, "Regular User", "user@example.com", "user");
    Mockito.when(userRepository.findById(userId)).thenReturn(regularUser); // Return a regular user

    // Act
    userService.deleteUser(userId);

    // Assert
    Mockito.verify(userRepository).deleteUser(userId); // Ensure deleteUser is called
  }

}
