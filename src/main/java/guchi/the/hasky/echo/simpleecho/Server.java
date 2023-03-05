package guchi.the.hasky.echo.simpleecho;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        BufferedReader in;
        BufferedWriter out;
        ServerSocket server;
        Socket client;
        try {
            server = new ServerSocket(3000);
            client = server.accept();
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Client connected.");

        while (true) {
            String message = null;
            try {
                message = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert message != null;
            if (message.equalsIgnoreCase("exit")) {
                System.out.println("Client disconnected.");

                try {
                    client.close();
                    in.close();
                    out.close();
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                try {
                    out.write("Echo: " + message + "\n");
                    out.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

