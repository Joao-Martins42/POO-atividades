package model;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import com.j256.ormlite.table.TableUtils;
import java.util.List;
import java.util.ArrayList;

public class InimigoRepositorio
{
    private static Database database;
    private static Dao<Inimigo, Integer> dao;
    private List<Inimigo> loadedInimigos;
    private Inimigo loadedInimigo;
    
    public InimigoRepositorio(Database database) {
        InimigoRepositorio.setDatabase(database);
        loadedInimigos = new ArrayList<Inimigo>();
    }
    
    public static void setDatabase(Database database) {
        InimigoRepositorio.database = database;
        try {
            dao = DaoManager.createDao(database.getConnection(), Inimigo.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Inimigo.class);
        }
        catch(SQLException e) {
            System.out.println(e);
        }            
    }
    
    public Inimigo create(Inimigo inimigo) {
        int nrows = 0;
        try {
            nrows = dao.create(inimigo);
            if ( nrows == 0 )
                throw new SQLException("Error: object not saved");
            this.loadedInimigo = inimigo;
            loadedInimigos.add(inimigo);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return inimigo;
    }    

    public void update(Inimigo Inimigo) {
      try{
          dao.update(Inimigo);
      }catch (SQLException e) {
          System.out.println(e);
      }
    }

    public void delete(Inimigo Inimigo) {
      try{
          dao.delete(Inimigo);
      }catch (SQLException e) {
          System.out.println(e);
      }
    }
    
    public Inimigo loadFromId(int id) {
        try {
            this.loadedInimigo = dao.queryForId(id);
            if (this.loadedInimigo != null)
                this.loadedInimigos.add(this.loadedInimigo);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.loadedInimigo;
    }    
    
    public List<Inimigo> loadAll() {
        try {
            this.loadedInimigos =  dao.queryForAll();
            if (this.loadedInimigos.size() != 0)
                this.loadedInimigo = this.loadedInimigos.get(0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.loadedInimigos;
    }
}