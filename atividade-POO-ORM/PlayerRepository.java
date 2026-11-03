import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import com.j256.ormlite.table.TableUtils;
import java.util.List;
import java.util.ArrayList;

public class PlayerRepository
{
    private static Database database;
    private static Dao<Player, Integer> dao;
    private List<Player> loadedPlayers;
    private Player loadedPlayer; 
    
    public PlayerRepository(Database database) {
        PlayerRepository.setDatabase(database);
        loadedPlayers = new ArrayList<Player>();
    }
    
    public static void setDatabase(Database database) {
        PlayerRepository.database = database;
        try {
            dao = DaoManager.createDao(database.getConnection(), Player.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Player.class);
        }
        catch(SQLException e) {
            System.out.println(e);
        }            
    }
    
    public Player create(Player player) {
        int nrows = 0;
        try {
            nrows = dao.create(player);
            if ( nrows == 0 )
                throw new SQLException("Error: object not saved");
            this.loadedPlayer = player;
            loadedPlayers.add(player);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return player;
    }    

    public void update(Player player) {
      try{
          dao.update(player);
      }catch (SQLException e) {
          System.out.println(e);
      }
    }

    public void delete(Player player) {
      try{
          dao.delete(player);
      }catch (SQLException e) {
          System.out.println(e);
      }
    }
    
    public Player loadFromId(int id) {
        try {
            this.loadedPlayer = dao.queryForId(id);
            if (this.loadedPlayer != null)
                this.loadedPlayers.add(this.loadedPlayer);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.loadedPlayer;
    }    
    
    public List<Player> loadAll() {
        try {
            this.loadedPlayers =  dao.queryForAll();
            if (this.loadedPlayers.size() != 0)
                this.loadedPlayer = this.loadedPlayers.get(0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.loadedPlayers;
    }

    // getters and setters ommited...        
}