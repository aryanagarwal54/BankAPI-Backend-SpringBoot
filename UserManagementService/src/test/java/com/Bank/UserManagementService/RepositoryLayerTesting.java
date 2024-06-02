package com.Bank.UserManagementService;
import com.Bank.UserManagementService.Model.User;
import com.Bank.UserManagementService.Repository.UserRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryLayerTesting {
    @Autowired
    private UserRepository repo;

    private User u;

    @BeforeEach
    void setUp() {

        u = new User(123, "Rahul", "rahul@itc.com", "85828323", "12345", "12345");
    }

    @AfterEach()
    void tearDown() {
        u = null;
        repo.deleteAll();

    }

    @Test
    @DisplayName("Test case for saving user object")
    public void givenUserToSaveReturnUser() {
        repo.save(u);
        User u1=repo.findById(u.getId());
        assertNotNull(u1);
        assertEquals(u.getId(),u1.getId());
    }
    @Test
    @DisplayName("test case for fetch all the user")
    void retrieveAllTheUsers()
    {
        repo.save(u);
        List<User>usr= repo.findAll();
        assertNotNull(usr);
    }
    @Test
    @DisplayName("test case for find a single user")
    void retrieveASingle()
    {
        repo.save(u);
        User u1=repo.findById(u.getId());
        assertEquals(u1.getId(),u.getId());
    }
    @Test
    @DisplayName("test case for find user through id and password")
    void retrieveAUser()
    {
        repo.save(u);
        User u1=repo.findByIdAndPassword(u.getId(),u.getPassword());
        assertEquals(u1.getId(),u.getId());
        assertEquals(u1.getPassword(),u.getPassword());
    }

}