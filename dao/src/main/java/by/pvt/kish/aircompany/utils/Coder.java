package by.pvt.kish.aircompany.utils;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Provides encoding string in accordance with a specified algorithm ALGORITHM
 *
 * @author Kish Alexey
 */
public class Coder {

    private static Logger logger = Logger.getLogger(Coder.class.getName());

    /**
     * Returns a string containing the hash code of the string generated by the specified algorithm
     *
     * @param password - encoded string
     * @return - hash code format 032x
     */
    public static String getHashCode(String password) {
        String ALGORITHM = "MD5";
        try {
            MessageDigest dig = MessageDigest.getInstance(ALGORITHM);
            int POSITIVE = 1;
            BigInteger hash = new BigInteger(POSITIVE, dig.digest(password.getBytes()));
            return String.format("%032x", hash);

        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithm " + ALGORITHM + "; " + e);
        }
        throw new RuntimeException("Exception at Coder: NoSuchAlgorithm");
    }
}
