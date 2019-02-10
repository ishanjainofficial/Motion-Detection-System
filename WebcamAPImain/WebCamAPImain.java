package WebcamAPImain;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class WebCamAPImain {
    private static final int SKIP_TICKS = 2000;

    public static void main(String[] args) throws IOException, InterruptedException {
        WebCam webcam = new WebCam();

        DataSender sender = new DataSender("192.168.34.151", 6066);

        long last_tick = System.currentTimeMillis();

        while (true) {
            TimeUnit.MILLISECONDS.sleep(SKIP_TICKS - (System.currentTimeMillis() - last_tick));
            last_tick = System.currentTimeMillis();
            sender.send(webcam.get());
        }
    }
}
