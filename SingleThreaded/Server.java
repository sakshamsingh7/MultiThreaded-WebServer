import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run() throws IOException{
        int port = 8010;// where server will listen client request
        // pre defined class to make server
        try (ServerSocket socket = new ServerSocket(port)) {
            // the server will wait 10 sec and close after 10 sec if no req matches
            socket.setSoTimeout(10000);
            while (true) {
                try {
                    System.out.println("Server is Listening on port " + port);
                    // if got client req we accept
                    Socket acceptedConnection = socket.accept();
                    System.out.println("Connection accepted from Client" + acceptedConnection.getRemoteSocketAddress());// to
                                                                                                                        // print
                                                                                                                        // client
                                                                                                                        // req
                                                                                                                        // address

                    // what server sends to client converting the bytes into readable form

                    PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());
                    // what server receives from  client converting the   readable form to bytes form.
                    // to understand
                    // as buffer class wants reader not stream so we used InputStreamReader here
                    BufferedReader fromClient = new BufferedReader(
                            new InputStreamReader(acceptedConnection.getInputStream()));

                    toClient.println("Hello from the server");
                    toClient.close();
                    fromClient.close();
                    acceptedConnection.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();// prints all details of code run so far
        }
    }
}