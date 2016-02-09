package by.pvt.kish.aircompany.controller;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.command.CommandFactory;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Kish Alexey
 */
public class Controller extends HttpServlet {
    private static Logger logger = Logger.getLogger(Controller.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandFactory factory = new CommandFactory();
        String action = request.getParameter("command");
        ActionCommand command = factory.defineCommand(action);
        logger.debug("call " + command.getClass().getSimpleName());
        String page = command.execute(request, response);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            logger.error("empty page at Controller");
        }
    }
}
