package guchi.the.hasky.echo.simpleecho;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        BufferedReader in = null;
        BufferedWriter out = null;
        Socket client = null;
        try {
            client = new Socket("127.0.0.1", 4999);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String serverResponse = "";
            System.out.println("Input message: ");
            try {
                String message = reader.readLine();
                assert out != null;
                out.write(message + "\n");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert in != null;
                serverResponse = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (serverResponse == null) {
                System.out.println("Session closed.");
                try {
                    in.close();
                    client.close();
                    assert out != null;
                    out.close();
                    reader.close();
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println(" >>> " + serverResponse);
            }
        }
    }
}
