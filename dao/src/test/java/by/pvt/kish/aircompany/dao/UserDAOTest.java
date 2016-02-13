package by.pvt.kish.aircompany.dao;

import by.pvt.kish.aircompany.dao.impl.UserDAO;
import by.pvt.kish.aircompany.enums.UserStatus;
import by.pvt.kish.aircompany.enums.UserType;
import by.pvt.kish.aircompany.pojos.User;
import by.pvt.kish.aircompany.utils.Coder;
import by.pvt.kish.aircompany.utils.HibernateUtil;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Kish Alexey
 */
public class UserDAOTest {
    private UserDAO userDao;
    private Long id;
    private User testUser;
    private HibernateUtil util = HibernateUtil.getUtil();
    private Transaction transaction;

    @Before
    public void setUp() throws Exception {
        userDao = UserDAO.getInstance();
        testUser = new User();
        testUser.setFirstName("testFirstName");
        testUser.setLastName("testLastName");
        testUser.setLogin("testLogin" + Math.random());
        testUser.setPassword(Coder.getHashCode("testPassword"));
        testUser.setEmail("test@test.com");
        testUser.setUserType(UserType.DISPATCHER);

        transaction = util.getSession().beginTransaction();
        id = userDao.add(testUser);
    }

    @Test
    public void testAdd() throws Exception {
        User addedUser = userDao.getById(id);
        assertEquals("Add method failed: wrong user", testUser, addedUser);
        userDao.delete(id);
    }

    @Test
    public void testCheckLogin() throws Exception {
        assertFalse("CheckLogin method positive test failed", userDao.checkLogin(testUser.getLogin()));
        assertTrue("CheckLogin method negative test failed", userDao.checkLogin("wrongLogin"));
        userDao.delete(id);
    }

    @Test
    public void testGetUser() throws Exception {
        User receivedUser = userDao.getUser(testUser.getLogin(), testUser.getPassword());
        assertEquals("Get method failed: wrong user", testUser, receivedUser);
        User wrongReceivedUser = userDao.getUser("wrongLogin", "wrongPassword");
        assertNull("Get method failed: wrong user", wrongReceivedUser);
        userDao.delete(id);
    }

    @Test
    public void testGetAll() throws Exception {
        int count = userDao.getAll().size();
        int countFact = userDao.getCount();
        assertEquals("Get all method failed", count, countFact);
        userDao.delete(id);

    }

    @Test
    public void testDelete() throws Exception {
        userDao.delete(id);
        assertNull("Delete user: failed", userDao.getById(id));
    }

    @Test
    public void testSetStatus() throws Exception {
        User prepareToUpdateStatusUser = userDao.getById(id);
        userDao.setStatus(id, UserStatus.ONLINE);
        User updatedStatusUser = userDao.getById(id);
        assertEquals("Set user status method failed", prepareToUpdateStatusUser.getStatus(), updatedStatusUser.getStatus());
        userDao.delete(id);
    }

    @After
    public void tearDown() throws Exception {
        transaction.commit();
    }


}