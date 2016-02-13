package by.pvt.kish.aircompany.command.plane;

import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Message;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.enums.PlaneStatus;
import by.pvt.kish.aircompany.exceptions.RequestHandlerException;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.PlaneService;
import by.pvt.kish.aircompany.utils.ErrorHandler;
import by.pvt.kish.aircompany.utils.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kish Alexey
 */
public class SetPlaneStatusCommand implements by.pvt.kish.aircompany.command.ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String className = SetPlaneStatusCommand.class.getName();
        try {
            Long id = RequestHandler.getId(request, "pid");
            PlaneService.getInstance().setStatus(id, PlaneStatus.valueOf(RequestHandler.getString(request, "status")));
            request.setAttribute(Attribute.MESSAGE_ATTRIBUTE, Message.SUCCESS_SET_STATUS_PLANE);
            return Page.MAIN;
        } catch (ServiceException e) {
            return ErrorHandler.returnErrorPage(e.getMessage(), className);
        } catch (RequestHandlerException e) {
            return ErrorHandler.returnErrorPage(e.getMessage(), className);
        }
    }
}
