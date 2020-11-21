package repository;

import java.sql.SQLException;

public interface GenericRepository {
    void create();
    void read();
    void update();
    void delete();
}
