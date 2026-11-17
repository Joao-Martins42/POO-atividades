package view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Inimigo {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty nome;
    private SimpleIntegerProperty vida;
    private SimpleIntegerProperty dano;
    
    public Inimigo(int id, String nome, 
                    int vida, int dano) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.vida = new SimpleIntegerProperty(vida);
        this.dano = new SimpleIntegerProperty(dano);        
    }
    
//Start GetterSetterExtension Source Code

    /**GET Method Propertie id*/
    public int getId(){
        return this.id.get();
    }//end method getId

    /**SET Method Propertie id*/
    public void setId(int id){
        this.id.set(id);
    }//end method setId

    /**GET Method Propertie nome*/
    public String getNome(){
        return this.nome.get();
    }//end method getNome

    /**SET Method Propertie nome*/
    public void setNome(String nome){
        this.nome.set(nome);
    }//end method setNome

    /**GET Method Propertie vida*/
    public int getVida(){
        return this.vida.get();
    }//end method getVida
    /**SET Method Propertie vida*/
    public void setVida(int vida){
        this.vida.set(vida);
    }//end method setVida
    
    /**GET Method Propertie dano*/
    public int getDano(){
        return this.dano.get();
    }//end method getDano
    /**SET Method Propertie dano*/
    public void setDano(int dano){
        this.dano.set(dano);
    }//end method setDano

//End GetterSetterExtension Source Code


}//End class