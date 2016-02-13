package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.pojos.User;
import by.pvt.kish.aircompany.enums.UserStatus;
import by.pvt.kish.aircompany.enums.UserType;
import by.pvt.kish.aircompany.services.impl.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Kish Alexey
 */
public class UserServiceTest {

    private UserService userService = UserService.getInstance();
    private Long id;
    private User testUser;


    @Before
    public void setUp() throws Exception {
        testUser = new User();
        testUser.setFirstName("testfirstname");
        testUser.setLastName("testlastname");
        testUser.setLogin("testLogin");
        testUser.setPassword("testPassword");
        testUser.setEmail("test@test.com");
        testUser.setUserType(UserType.DISPATCHER);
        testUser.setStatus(UserStatus.OFFLINE);
        id = userService.add(testUser);
    }

    @Test
    public void testAdd() throws Exception {
        User addedUser = userService.getById(id);
        testUser.setUid(id);
        assertEquals("Add method failed: wrong firstname", testUser, addedUser);
        userService.delete(id);
    }

    @Test
    public void testCheckLogin() throws Exception {
        assertFalse("CheckLogin method positive test failed", userService.checkLogin(testUser.getLogin()));
        assertTrue("CheckLogin method negative test failed", userService.checkLogin("wrongLogin"));
        userService.delete(id);
    }

    @Test
    public void testGetUser() throws Exception {
        User executedUser = userService.getUser(testUser.getLogin(),testUser.getPassword());
        testUser.setUid(id);
        assertEquals("Get method failed: wrong user", testUser, executedUser);
        userService.delete(id);
    }

    @Test
    public void testDelete() throws Exception {
        userService.delete(id);
        assertNull("Delete user: failed",userService.getById(id));
    }

    @Test
    public void testSetStatus() throws Exception {
        User prepareToUpdateStatusUser = userService.getById(id);
        userService.setStatus(id, UserStatus.ONLINE);
        User updatedStatusUser = userService.getById(id);
        assertEquals("Set user status method failed", prepareToUpdateStatusUser.getStatus(), updatedStatusUser.getStatus());
        userService.delete(id);
    }

    @After
    public void tearDown() throws Exception{
    }
}