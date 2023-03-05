package guchi.the.hasky.echo.multiplyecho;


import java.io.*;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(4999)) {

            System.out.println("Server star:");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {

                    try (
                          BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                          BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                        System.out.println("Client connected");
//                            String request = reader.readLine();
//                            String response = "echo " + request;
//                            System.out.println(response);
//                            writer.write(response);
//                            writer.newLine();
//                            writer.flush();

                        try {
                            String message = reader.readLine();
                            writer.write("Echo: " + message + "\n");
                            writer.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}