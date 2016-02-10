package by.pvt.kish.aircompany.validators;

import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.pojos.User;
import by.pvt.kish.aircompany.enums.UserStatus;
import by.pvt.kish.aircompany.enums.UserType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Kish Alexey
 */
public class UserValidatorTest {

    private User validUser;
    private UserValidator userValidator = new UserValidator();

    @Before
    public void setUp() throws Exception {
        validUser = new User();
        validUser.setFirstName("Firstname");
        validUser.setLastName("Lastname");
        validUser.setLogin("login");
        validUser.setPassword("1111111");
        validUser.setEmail("email@email.com");
        validUser.setUserType(UserType.DISPATCHER);
        validUser.setStatus(UserStatus.OFFLINE);
    }

    @Test
    public void testValidateData() throws Exception {
        assertNull("User validate failed", userValidator.validate(validUser));
        validUser.setFirstName("FIRSTNAME");
        assertEquals("Validate method failed: firstName is wrong", userValidator.validate(validUser), Message.MALFORMED_FIRSTNAME);
        validUser.setFirstName("firstTname");
        assertEquals("Validate method failed: firstName is wrong", userValidator.validate(validUser), Message.MALFORMED_FIRSTNAME);
        validUser.setFirstName("Firstname");
        validUser.setLastName("LASTNAME");
        assertEquals("Validate method failed: lastName is wrong", userValidator.validate(validUser), Message.MALFORMED_LASTNAME);
        validUser.setLastName("lastName");
        assertEquals("Validate method failed: lastName is wrong", userValidator.validate(validUser), Message.MALFORMED_LASTNAME);
        validUser.setLastName("Lastname");
        validUser.setLogin("testLoginlong");
        assertEquals("Validate method failed: login is to long", userValidator.validate(validUser), Message.MALFORMED_LOGIN);
        validUser.setLogin("te");
        assertEquals("Validate method failed: login is to short", userValidator.validate(validUser), Message.MALFORMED_LOGIN);
        validUser.setLogin("test!login");
        assertEquals("Validate method failed: login is wrong", userValidator.validate(validUser), Message.MALFORMED_LOGIN);
        validUser.setLogin("login");
        validUser.setPassword("passw");
        assertEquals("Validate method failed: password is to short", userValidator.validate(validUser), Message.MALFORMED_PASSWORD);
        validUser.setPassword("passwordpassword");
        assertEquals("Validate method failed: password is to long", userValidator.validate(validUser), Message.MALFORMED_PASSWORD);
        validUser.setPassword("pass.word");
        assertEquals("Validate method failed: password is wrong", userValidator.validate(validUser), Message.MALFORMED_PASSWORD);
        validUser.setPassword("1111111");
        validUser.setEmail("emailemail.com");
        assertEquals("Validate method failed: email is wrong", userValidator.validate(validUser), Message.MALFORMED_EMAIL);
        validUser.setEmail("emai@lemailcom");
        assertEquals("Validate method failed: email is wrong", userValidator.validate(validUser), Message.MALFORMED_EMAIL);
    }

    @Test
    public void testValidateNull() throws Exception {
        validUser.setEmail("email@email.com");
        validUser.setFirstName(null);
        assertEquals("Validate method failed: firstName is null", userValidator.validate(validUser), Message.ERROR_EMPTY);
        validUser.setFirstName("Firstname");
        validUser.setLastName(null);
        assertEquals("Validate method failed: lastName is null", userValidator.validate(validUser), Message.ERROR_EMPTY);
        validUser.setLastName("Lastname");
        validUser.setLogin(null);
        assertEquals("Validate method failed: login is null", userValidator.validate(validUser), Message.ERROR_EMPTY);
        validUser.setLogin("login");
        validUser.setPassword(null);
        assertEquals("Validate method failed: password is null", userValidator.validate(validUser), Message.ERROR_EMPTY);
        validUser.setPassword("1111111");
        validUser.setEmail(null);
        assertEquals("Validate method failed: email is null", userValidator.validate(validUser), Message.ERROR_EMPTY);
        validUser.setEmail("email@email.com");
        validUser.setUserType(null);
        assertEquals("Validate method failed: type is null", userValidator.validate(validUser), Message.ERROR_EMPTY);
        validUser.setUserType(UserType.DISPATCHER);
        validUser.setStatus(null);
        assertEquals("Validate method failed: status is null", userValidator.validate(validUser), Message.ERROR_EMPTY);
        validUser.setStatus(UserStatus.OFFLINE);
    }


}