package codes.matthewp.desertedcore.database;

import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String url;

    private String database;

    private String userName;

    private String password;

    private int port;

    private HikariDataSource dataSource;

    private List<DatabaseAccess> activeDatabaseAccessors;

    public Database(FileConfiguration config) {
        loadValues(config);
        activeDatabaseAccessors = new ArrayList<>();
        loadDataSource();
    }

    private void loadValues(FileConfiguration configuration) {
        this.url = configuration.getString("url");
        this.port = configuration.getInt("port");
        this.database = configuration.getString("database");
        this.userName = configuration.getString("username");
        this.password = configuration.getString("password");
    }

    private void loadDataSource() {
        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://" + url + ":" + String.valueOf(port) + "/" + database);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
    }

    public void disconnect() {
        dataSource.close();
    }

    public Connection getConnection(DatabaseAccess access) throws SQLException {
        if (!activeDatabaseAccessors.contains(access)) {
            activeDatabaseAccessors.add(access);
        }
        if (!access.hasLoaded) {
            access.loadTables();
        }
        return dataSource.getConnection();
    }

}