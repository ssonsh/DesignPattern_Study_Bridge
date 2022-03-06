public abstract class Car {
    private Power power;

    public Car(Power power) {
        this.power = power;
    }

    public void drive(){
        power.powerUp();
    }
    public void stop(){
        power.powerDown();
    }
}
