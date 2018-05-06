public class Main {
    public static void main(String[] args) {
        Barber barber = new Barber();
        Thread barberThread = new Thread(barber, "Barber");

        Entrance entrance = new Entrance(barberThread);
        Thread entranceThread = new Thread(entrance, "Entrance");

        barber.setThread(barberThread);

        barberThread.start();
        entranceThread.start();
    }
}
