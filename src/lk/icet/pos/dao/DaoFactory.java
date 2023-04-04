package lk.icet.pos.dao;

import lk.icet.pos.dao.custom.impl.CustomerDaoImpl;
import lk.icet.pos.dao.custom.impl.ItemDaoImpl;
import lk.icet.pos.dao.custom.impl.UserDaoImpl;
import lk.icet.pos.enums.DaoType;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory(){

    }
    public static DaoFactory getInstance(){
        return daoFactory==null?(daoFactory=new DaoFactory()):daoFactory;
    }
    public <T>T getDao(DaoType daoType){
        switch(daoType){
            case CUSTOMER:return (T) new CustomerDaoImpl();
            case USER:return (T) new UserDaoImpl();
            case ITEM:return (T) new ItemDaoImpl();
            default:return null;
        }
    }
}
