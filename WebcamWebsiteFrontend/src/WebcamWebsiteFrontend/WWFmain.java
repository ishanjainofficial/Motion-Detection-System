package WebcamWebsiteFrontend;

import java.net.*;
import java.io.IOException;
import javax.imageio.*;
import java.awt.image.*;
import java.io.File;

public class WWFmain {
	static ServerSocket serverSocket;
	public static void main(String[] args) throws IOException {
		serverSocket = new ServerSocket(6067);
		int loops = 0;
				
		while (true) {
			Socket socket = serverSocket.accept();
			BufferedImage image = ImageIO.read(ImageIO.createImageInputStream(socket.getInputStream()));
			File f = new File( "/Applications/mamp/htdocs/image.jpg");
			f.createNewFile();
			ImageIO.write(image, "JPG", f);
			loops += 1;
			socket.close();
		}
	}
}
