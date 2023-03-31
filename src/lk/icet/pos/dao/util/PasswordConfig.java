package lk.icet.pos.dao.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordConfig {
    public static String encryptPassword(String rowPassword){
        return BCrypt.hashpw(rowPassword,BCrypt.gensalt());
    }

}
