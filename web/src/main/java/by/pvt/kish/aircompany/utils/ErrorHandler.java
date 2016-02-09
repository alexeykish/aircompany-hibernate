package by.pvt.kish.aircompany.utils;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kish Alexey
 */
public class ErrorHandler {

    private static Logger logger = Logger.getLogger(ErrorHandler.class.getName());

    public static String returnValidateErrorPage(HttpServletRequest request, String validateResult, String className) {
        request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, validateResult);
        logger.error(className + ": " + validateResult);
        return Page.ERROR;
    }

    public static String returnLoginErrorPage(HttpServletRequest request, String error, String className) {
        request.setAttribute(Attribute.LOGIN_MESSAGE_ATTRIBUTE, error);
        logger.error(className + ": " + error);
        return Page.INDEX;
    }

    public static String returnErrorPage(String error, String className) {
        logger.error(className + ": " + error);
        return Page.ERROR;
    }
}
