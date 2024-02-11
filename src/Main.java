import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Client client1 = new Client("Vasia");
        Client client2 = new Client("Kolia");
        Client client3 = new Client("Fedia");
        Server server = new Server();
        server.addClient(client1);
        server.addClient(client2);
        server.addClient(client3);
        ServerWindow serverWindow = new ServerWindow(server);
        for (Client client: server.clientList)
        {
            ClientWindow clientWindow = new ClientWindow(client, serverWindow, server);
        }
    }
}