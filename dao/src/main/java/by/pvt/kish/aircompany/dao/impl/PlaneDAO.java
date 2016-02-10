package by.pvt.kish.aircompany.dao.impl;

import by.pvt.kish.aircompany.dao.BaseDAO;
import by.pvt.kish.aircompany.dao.IPlaneDAO;
import by.pvt.kish.aircompany.enums.PlaneStatus;
import by.pvt.kish.aircompany.exceptions.DaoException;
import by.pvt.kish.aircompany.pojos.Plane;
import by.pvt.kish.aircompany.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * This class represents a concrete implementation of the IDAO interface for plane model.
 *
 * @author Kish Alexey
 */
public class PlaneDAO extends BaseDAO<Plane> implements IPlaneDAO{

    private static final String HQL_UPDATE_PLANE_STATUS = "update FROM Plane P set P.status =:status where P.pid =:id";

    private static final String UPDATE_PLANE_STATUS_FAIL = "Updating plane status failed";


    private static PlaneDAO instance;
    private HibernateUtil util = HibernateUtil.getUtil();

    private PlaneDAO() {
        super(Plane.class);
    }

    /**
     * Returns an synchronized instance of a PlaneDAO, if the instance does not exist yet - create a new
     *
     * @return - a instance of a PlaneDAO
     */
    public synchronized static PlaneDAO getInstance() {
        if (instance == null) {
            instance = new PlaneDAO();
        }
        return instance;
    }

    /**
     * Update particular plane status int the DB matching the given ID
     *
     * @param id - The ID of the flight
     * @throws DaoException If something fails at DB level
     */
    @Override
    public void setStatus(Long id, PlaneStatus status) throws DaoException {
        try {
            Session session = util.getSession();
            Query query = session.createQuery(HQL_UPDATE_PLANE_STATUS);
            query.setParameter("status",status);
            query.setParameter("id",id);
            query.executeUpdate();
        } catch (HibernateException e) {
            throw new DaoException(UPDATE_PLANE_STATUS_FAIL);
        }
    }
}
