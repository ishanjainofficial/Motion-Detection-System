package WebcamAPImain;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;

class DataSender {
    private String serverName;
    private int port;

    DataSender(String serverName, int port) {
        this.serverName = serverName;
        this.port = port;
    }

    void send(BufferedImage image) throws IOException {
        Socket socket = new Socket(serverName, port);
        System.out.println("Sending data.");
        ImageIO.write(image,"JPG", socket.getOutputStream());
        socket.close();
    }
}
