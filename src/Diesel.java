public class Diesel implements Power{

    @Override
    public void powerUp() {
        System.out.println("engine power up");
    }

    @Override
    public void powerDown() {
        System.out.println("engine power down");
    }
}
