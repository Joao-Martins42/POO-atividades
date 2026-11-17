package model;

import java.util.Date;
import java.text.SimpleDateFormat;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;

@DatabaseTable(tableName="inimigo")
public class Inimigo {
    
    @DatabaseField(generatedId = true)
    private int id;
        
    @DatabaseField(dataType=DataType.STRING)    
    private String nome;
    
    @DatabaseField(dataType=DataType.INTEGER)        
    private int vida;
    
    @DatabaseField(dataType=DataType.INTEGER)        
    private int dano;
    

//Start GetterSetterExtension Source Code

    /**GET Method Propertie id*/
    public int getId(){
        return this.id;
    }//end method getId

    /**SET Method Propertie id*/
    public void setId(int id){
        this.id = id;
    }//end method setId

    /**GET Method Propertie nome*/
    public String getNome(){
        return this.nome;
    }//end method getNome

    /**SET Method Propertie nome*/
    public void setNome(String nome){
        this.nome = nome;
    }//end method setNome

    /**GET Method Propertie vida*/
    public int getVida(){
        return this.vida;
    }//end method getVida

    /**SET Method Propertie vida*/
    public void setVida(int vida){
        this.vida = vida;
    }//end method setVida

    /**GET Method Propertie dano*/
    public int getDano(){
        return this.dano;
    }//end method getDano

    /**SET Method Propertie dano*/
    public void setDano(int dano){
        this.dano = dano;
    }//end method setDano

//End GetterSetterExtension Source Code


}//End class