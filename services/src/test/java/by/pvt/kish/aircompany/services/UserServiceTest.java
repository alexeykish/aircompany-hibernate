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
        assertEquals("Add method failed: wrong firstname", testUser.getFirstName(), addedUser.getFirstName());
        assertEquals("Add method failed: wrong lastname", testUser.getLastName(), addedUser.getLastName());
        assertEquals("Add method failed: wrong login", testUser.getLogin(), addedUser.getLogin());
        assertEquals("Add method failed: wrong email", testUser.getEmail(), addedUser.getEmail());
        assertEquals("Add method failed: wrong usertype", testUser.getUserType(), addedUser.getUserType());
        assertEquals("Add method failed: wrong status", testUser.getStatus(), addedUser.getStatus());
    }

    @Test
    public void testCheckLogin() throws Exception {
        assertFalse("CheckLogin method positive test failed", userService.checkLogin(testUser.getLogin()));
        assertTrue("CheckLogin method negative test failed", userService.checkLogin("wrongLogin"));
    }

    @Test
    public void testGetUser() throws Exception {
        User gettedUser = userService.getUser(testUser.getLogin(),testUser.getPassword());
        assertEquals("Get method failed: wrong firstname", testUser.getFirstName(), gettedUser.getFirstName());
        assertEquals("Get method failed: wrong lastname", testUser.getLastName(), gettedUser.getLastName());
        assertEquals("Get method failed: wrong login", testUser.getLogin(), gettedUser.getLogin());
        assertEquals("Get method failed: wrong email", testUser.getEmail(), gettedUser.getEmail());
        assertEquals("Get method failed: wrong usertype", testUser.getUserType(), gettedUser.getUserType());
        //assertEquals("Get method failed: wrong status", testUser.getStatus(), gettedUser.getStatus());
    }

    @Test
    public void testGetAll() throws Exception {
        User testUser2 = testUser;
        testUser2.setLogin("testLogin2");
        int beforeAddNumber = userService.getAll().size();
        Long getAllId = userService.add(testUser2);
        int afterAddNumber = userService.getAll().size();
        assertEquals("Get all users method failed", beforeAddNumber, afterAddNumber-1);
        userService.delete(getAllId);
    }

    @Test
    public void testDelete() throws Exception {
        userService.delete(id);
        assertNull("Delete user: failed",userService.getById(id));
    }

    @Test
    public void testCheckStatus() throws Exception {
        User offlineUser = userService.getById(id);
        assertFalse("CheckStatus method negative test failed", userService.checkStatus(offlineUser.getUid()));
        userService.setStatus(id, UserStatus.ONLINE);
        User onlineUser = userService.getById(id);
        assertTrue("CheckStatus method positive test failed", userService.checkStatus(onlineUser.getUid()));
        userService.setStatus(id, UserStatus.OFFLINE);
    }

    @Test
    public void testSetStatus() throws Exception {
        userService.setStatus(id, UserStatus.ONLINE);
        assertEquals("Set user status method failed", userService.getById(id).getStatus(), UserStatus.ONLINE);
        userService.setStatus(id, UserStatus.OFFLINE);
    }

    @After
    public void tearDown() throws Exception{
        userService.delete(id);
    }
}