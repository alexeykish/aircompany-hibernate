package by.pvt.kish.aircompany.command.plane;

import by.pvt.kish.aircompany.command.ActionCommand;
import by.pvt.kish.aircompany.constants.Attribute;
import by.pvt.kish.aircompany.constants.Page;
import by.pvt.kish.aircompany.pojos.Plane;
import by.pvt.kish.aircompany.exceptions.ServiceException;
import by.pvt.kish.aircompany.services.impl.PlaneService;
import by.pvt.kish.aircompany.utils.ErrorHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Kish Alexey
 */
public class GetAllPlanesCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String className = GetAllPlanesCommand.class.getName();
        try {
            List<Plane> planes = PlaneService.getInstance().getAll();
            request.setAttribute(Attribute.PLANES_ATTRIBUTE, planes);
        } catch (ServiceException e) {
            return ErrorHandler.returnErrorPage(e.getMessage(), className);
        }
        return Page.PLANES;
    }
}
