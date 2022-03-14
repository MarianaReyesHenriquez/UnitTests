package notes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "notes")
@Entity
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private String temp;
    
    public Nota() {}

    public Nota(String text, String temp) {
        this.text = text;
        this.temp = temp;
    }

    public Long getId() {
        return this.id;
      }

    public String getText() {
        return this.text;
    }

    public String getTemp(){
        return this.temp;
    }

    public void setId(Long id) {
        this.id = id;
      }

    public void setText(String text){
        this.text = text;
    }

    public void setTemp(String temp){
        this.temp = temp;
    }
}
