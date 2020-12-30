package dao.singleton;

import model.User;

public class UserSingleton {

    private static User instance;

    private UserSingleton() {}

    public synchronized static User getInstance() {
        if(instance == null) {
            instance = new User();
        }
        return instance;
    }

}
