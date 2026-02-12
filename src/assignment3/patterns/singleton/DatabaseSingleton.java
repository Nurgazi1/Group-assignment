package assignment3.patterns.singleton;


import assignment3.Data.repositories.db.PostgresDatabase;

public class DatabaseSingleton {

    private static PostgresDatabase instance;

    private DatabaseSingleton() {
    }

    public static PostgresDatabase getInstance() {
        if (instance == null) {
            instance = new PostgresDatabase();
        }
        return instance;
    }
}
//e