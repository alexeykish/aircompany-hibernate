package by.pvt.kish.aircompany.command.employee;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.exceptions.RequestHandlerException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.EmployeeService;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import by.pvt.kish.aircompany.utils.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kish Alexey
 */
public class SetEmployeeStatusCommand implements by.pvt.kish.aircompany.command.ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String className = SetEmployeeStatusCommand.class.getName();
        try {
            Long id = RequestHandler.getId(request, "eid");
            EmployeeService.getInstance().setStatus(id, RequestHandler.getString(request, "status"));

            request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_SET_STATUS_EMPLOYEE);

            return Page.MAIN;
        } catch (ServiceException e) {
            return ErrorHandler.returnErrorPage(e.getMessage(), className);
        } catch (RequestHandlerException e) {
            return ErrorHandler.returnErrorPage(e.getMessage(), className);
        }
    }
}
