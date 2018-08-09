package codes.matthewp.desertedcore.database;

/**
 * The recommended way to access the database
 */
public class DatabaseAccess {

    public boolean hasLoaded = false;

    public Database db;

    public DatabaseAccess(Database db) {
        this.db = db;
    }

    public void loadTables() {

    }

    public boolean hasLoaded() {
        return hasLoaded;
    }

    public void setHasLoaded(boolean hasLoaded) {
        this.hasLoaded = hasLoaded;
    }
}
