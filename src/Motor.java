public class Motor implements Power{
    @Override
    public void powerUp() {
        System.out.println("motor power up");
    }

    @Override
    public void powerDown() {
        System.out.println("motor power down");
    }
}
