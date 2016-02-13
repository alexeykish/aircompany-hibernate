/**
 *
 */
package by.pvt.kish.aircompany.services;

import by.pvt.kish.aircompany.utils.HibernateUtil;
import by.pvt.kish.aircompany.utils.ServiceUtils;
import org.hibernate.Transaction;

/**
 * The abstract class represents a implementation of the IService interface
 *
 * @author Kish Alexey
 */
public abstract class BaseService<T> implements IService<T> {

    protected ServiceUtils serviceUtils = new ServiceUtils();
    protected HibernateUtil util = HibernateUtil.getUtil();
    protected Transaction transaction = null;
    public static final String SUCCESSFUL_TRANSACTION = "Successful transaction";
    public static final String TRANSACTION_FAILED = "Transaction failed";

    protected BaseService() {
        super();
    }
}
