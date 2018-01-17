package aveh.database;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class DatabaseConnector {
    public static PartnerConnection connection;

    public static boolean connect(String username, String password, String token) {// default authendpoint https://test.salesforce.com/services/Soap/c/27.0
        return connect(username, password, token, null);
    }

    public static boolean connect(String username, String password, String token, String authEndPoint) {
        boolean success = false;

        String fullPassword = password + token;

        ConnectorConfig config = new ConnectorConfig();
        config.setUsername(username);
        config.setPassword(fullPassword);

        if (authEndPoint != null && !authEndPoint.isEmpty()) {
            config.setAuthEndpoint(authEndPoint);
        }

        try {
            connection = Connector.newConnection(config);
            System.out.println("Connection successful!");
            success = true;
        } catch (ConnectionException e1) {
            e1.printStackTrace();
        }

        return success;
    }

    public static void disconnect() {
        try {
            connection.logout();
            System.out.println("Disconnected!");
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
    }
}
