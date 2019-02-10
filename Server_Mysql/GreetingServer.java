import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.*;
import java.sql.*;


import javax.imageio.ImageIO;


public class GreetingServer extends Thread {
    Integer i = 0;
    private static final String url = "jdbc:mysql://localhost/WebCamImages";

    private static final String user = "adi";

    private static final String password = "adityapc";
    private ServerSocket serverSocket;
    private static Statement stmnt = null;

    private static String webSiteAddress = "192.168.34.186";
    private static Integer webSitePort = 55555;


    Connection con = DriverManager.getConnection(url, user, password);
    Socket server;

    public GreetingServer(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(180000);
    }

    public void run() {

        while(true) {

            try {
                server = serverSocket.accept();

                con.setAutoCommit(false);
                Class.forName("com.mysql.jdbc.Driver");


                BufferedImage img=ImageIO.read(ImageIO.createImageInputStream(server.getInputStream()));
                File file = new File("/home/aditya/pic/img.jpg");
                ImageIO.write(img, "jpg", file);

                System.out.println("Finished Image to File.");

                String INSERT_PICTURE = "INSERT INTO pic(idpic, img) VALUES (?, ?)";

                try (FileInputStream fis = new FileInputStream(file);
                     PreparedStatement ps = con.prepareStatement(INSERT_PICTURE)) {
                    ps.setString(1, i.toString());
                    ps.setBinaryStream(2, fis, (int) file.length());
                    ps.executeUpdate();
                    con.commit();
                }


                Socket socket = new Socket(webSiteAddress, webSitePort);
//                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//                dos.writeBytes("Yo is this shit working");


                ImageIO.write(img, "JPG", socket.getOutputStream());
                System.out.println("Sent image to website");

            } catch(SocketTimeoutException st) {
                System.out.println("Socket timed out!");
                break;

            } catch(IOException e) {
                e.printStackTrace();
                break;

            } catch(Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public static void main(String [] args)throws Exception {
        Thread t = new GreetingServer(6066);
        t.start();
    }

    public static void postRequest() throws Exception {
        URL url = new URL("https://www.example.com/login");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection)con;
        http.setRequestMethod("POST"); // PUT is another valid option
        http.setDoOutput(true);
    }
}
