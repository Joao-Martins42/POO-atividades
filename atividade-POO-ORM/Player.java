import java.util.Date;
import java.text.SimpleDateFormat;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;

@DatabaseTable(tableName = "player")
public class Player {
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField
    private int hp;
    
    @DatabaseField
    private int dano;
    
    @DatabaseField
    private String nome;
    
    //getters e setters
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getHp(){
        return this.hp;
    }
    
    public void setHp(int hp){
        this.hp = hp;
    }
    
    public int getDano(){
        return this.dano;
    }
    
    public void setDano(int dano){
        this.dano = dano;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
}