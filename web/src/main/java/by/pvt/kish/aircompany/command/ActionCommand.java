package by.pvt.kish.aircompany.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kish Alexey
 */
public interface ActionCommand {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
