package willson.healthadvisor.Helping;

public enum Activity {
    MINIMUM(1.2),
    TRIPLE(1.375),
    FIFTH(1.4625),
    INTENSIVEFIFTH(1.550),
    ACTIVE(1.6375),
    ACTIVEDOUBLE(1.725),
    INTENSIVE(1.9);

    private Double strength;
    Activity(double strenght){
        this.strength = strenght;
    }

    public Double getStrength() {
        return strength;
    }

    public void setStrength(Double strength) {
        this.strength = strength;
    }
}
