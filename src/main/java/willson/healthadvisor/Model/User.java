package willson.healthadvisor.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import willson.healthadvisor.Helping.Activity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer weight;
    private Integer height;
    private Integer age;
    private Boolean sex;
    private Activity activity;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Diet> diets = new ArrayList<>();


    public Diet getDiet(){
        return diets.get(0);
    }

    public User(String name, Integer weight, Integer height, Integer age, Boolean sex, Activity activity) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.sex = sex;
        this.activity = activity;
    }
}
