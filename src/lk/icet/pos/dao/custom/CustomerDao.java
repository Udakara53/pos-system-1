package lk.icet.pos.dao.custom;

import lk.icet.pos.dao.CrudDao;
import lk.icet.pos.entity.Customer;

import java.sql.SQLException;
import java.util.List;


public interface CustomerDao extends CrudDao<Customer,String> {
    public List<String> loadCustomerIds() throws SQLException, ClassNotFoundException;
}
