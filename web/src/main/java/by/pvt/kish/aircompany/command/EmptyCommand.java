package by.pvt.kish.aircompany.command;

import by.pvt.kish.aircompany.constants.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * В случае ошибки или прямого обращения к контроллеру переадресация на страницу ввода логина
 *
 * @author  Kish Alexey
 */
public class EmptyCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return Page.INDEX;
    }
}
