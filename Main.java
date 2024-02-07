import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /*creando una representación de arreglo de bytes para la dirección IP
        del servidor echo de internet*/
        byte[] byteIP = new byte[]{127, 0, 0, 1};
        InetAddress ip = null;
        Socket sock;
        BufferedReader sockInput;
        PrintWriter sockOutput;
        //variable para el número de puerto del servidor
        int port = 1050;

        try {
            /* obteniendo un nuevo objeto de tipo InetAddress
            para la representación de la IP*/
            ip = InetAddress.getByAddress(byteIP);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        try {
            /* creando el socket a partir de la dirección
            ip (InetAddress) y numero de puerto*/
            sock = new Socket(ip, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            //Leer lo que viene de consola
            sockInput = new BufferedReader(
                    new InputStreamReader(sock.getInputStream())
            );
            //Lo que se manda al servidor                     no olvidar segundo argumento (true)
            sockOutput = new PrintWriter(sock.getOutputStream(),true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scanner consola = new Scanner(System.in);
        String lectura;

        while (true) {
            lectura = consola.nextLine();
            sockOutput.println(lectura);
            try {
                System.out.println(sockInput.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
