package willson.healthadvisor.Model;

import lombok.NoArgsConstructor;
import willson.healthadvisor.Helping.DietCounter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer normUsualKcal;
    private Integer normToLoseKcal;
    private Integer consumed;
    private Integer leftToConsume;

    private Integer waterToDrink;
    private Integer waterDrunk;
    private Integer waterLeftToDrink;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private User user;

    @Transient
    DietCounter dietCounter = new DietCounter();

    public Diet(Integer id,Integer normUsualKcal, Integer normToLoseKcal, User user) {
        this.id =id;
        this.normUsualKcal = normUsualKcal;
        this.normToLoseKcal = normToLoseKcal;

    }

    public Integer getLeftToConsume() {
        return leftToConsume;
    }

    public void setLeftToConsume(Integer leftToConsume) {
        this.leftToConsume = leftToConsume;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNormUsualKcal() {
        return normUsualKcal;
    }

    public void setNormUsualKcal(Integer normUsualKcal) {
        this.normUsualKcal = dietCounter.caculateNormUsual(getUser());
    }

    public Integer getNormToLoseKcal() {
        return normToLoseKcal;
    }

    public void setNormToLoseKcal(Integer normToLoseKcal) {
        this.normToLoseKcal = dietCounter.calclateNormToLose(getUser());
    }

    public Integer getConsumed() {
        return consumed;
    }

    public void setConsumed(Integer consumed) {
        this.consumed = consumed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getWaterToDrink() {
        return waterToDrink;
    }

    public void setWaterToDrink(Integer waterToDrink) {
        this.waterToDrink = dietCounter.calculateWaterToConsume(getUser());
    }

    public Integer getWaterDrunk() {
        return waterDrunk;
    }

    public void setWaterDrunk(Integer waterDrunk) {
        this.waterDrunk = waterDrunk;
    }

    public Integer getWaterLeftToDrink() {
        return waterLeftToDrink;
    }

    public void setWaterLeftToDrink(Integer waterLeftToDrink) {
        this.waterLeftToDrink = waterLeftToDrink;
    }

    @Override
    public String toString() {
        return "Diet{" +
                "id=" + id +
                ", normUsualKcal=" + normUsualKcal +
                ", normToLoseKcal=" + normToLoseKcal +
                ", consumed=" + consumed +
                ", user=" + user +
                ", dietCounter=" + dietCounter +
                '}';
    }

}
