package AbstractAndInterface;

class Tv {
    protected boolean power;
    protected int channel;

    public void power() {
        power = !power;
    }

    public void channelUp() {
        channel++;
    }

    public void channelDown() {
        channel--;
    }
}

class VCR {
    public void play() {
        System.out.println("PLAY");
    }

    public void stop() {
        System.out.println("STOP");
    }
}

interface IVCR {
    public abstract void stop();

    // pulic abstract가 생략되어있다.
    void play();
}

class TVCR extends Tv implements IVCR {
    VCR vcr = new VCR();


    @Override
    public void stop() {
        vcr.stop();
    }

    @Override
    public void play() {
        vcr.play();
    }
}

public class InterfaceTest {
    public static void main(String[] args) {
        TVCR tvcr = new TVCR();
        tvcr.play();
        tvcr.stop();
    }
}
