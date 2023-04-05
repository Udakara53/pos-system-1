package lk.icet.pos.bo;

import lk.icet.pos.bo.custom.impl.*;
import lk.icet.pos.dao.custom.impl.CustomerDaoImpl;
import lk.icet.pos.dao.custom.impl.ItemDaoImpl;
import lk.icet.pos.dao.custom.impl.UserDaoImpl;
import lk.icet.pos.enums.BOType;
import lk.icet.pos.enums.DaoType;

public class BoFactory {

    private static BoFactory boFactory;

    private BoFactory(){

    }
    public static BoFactory getInstance(){
        return boFactory==null?(boFactory=new BoFactory()):boFactory;
    }
    public <T>T getBo(BOType type){
        switch(type){
            case CUSTOMER:return (T) new CustomerBoImpl();
            case USER:return (T) new UserBoImpl();
            case ITEM:return (T) new ItemBoImpl();
            case ORDER:return (T) new OrderBoImpl();
            case ORDER_DETAILS:return (T) new OrderDetailsBoImpl();
            default:return null;
        }
    }
}
