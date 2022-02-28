package assessment.Ques7;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import assessment.Ques7.CustomIdGenerator;
@Entity
@Table
public class Products {
    @Id
    @GeneratedValue(generator = "custom-generator" )
    @GenericGenerator(strategy = "assessment.Ques7.CustomIdGenerator", name = "custom-generator")
    private int id;
    @Column
    private String name;
    @Column
    private int amount;

    public Products() {
    }

    public Products(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
