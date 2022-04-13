package server;

import java.io.*;
import java.net.Socket;

class ServerConnectClient {
    public Socket server_socket;
    public BufferedReader read_password_card_fromClient;
    public BufferedReader read_num_Switch_Case;
    public BufferedReader read_QTY_Money_from_Client;
    public PrintWriter QTY_Money_for_Client_send;
    public PrintWriter send_toClient;
    public String getSwitchCase;
    public PrintWriter send_Client_his_Money;
    public String new_read_QTY_Money_from_Client;
    public static int money = 2000;

    //this Object get parameter type Socket Object socket (addr, port, localport)
    public ServerConnectClient(Socket socket) throws IOException {
        this.server_socket = socket;
    }

    //this method send from Server input stream to Client
    public void sendtoClient() throws IOException {
        send_toClient = new PrintWriter(server_socket.getOutputStream(), true);
        send_toClient.println("~ Server - You have money of your account: " + money + "$");
        System.out.println("Answer to Client send");
    }

    //this method get and read from Client output stream
    public void readfromClient() throws IOException {
        read_password_card_fromClient = new BufferedReader(new InputStreamReader(server_socket.getInputStream()));
        String getNumCard = read_password_card_fromClient.readLine();

        System.out.println("~Client - Number card of user: " + getNumCard);
        if (getNumCard.equals("q")) {
            System.out.println("Client is out");
        }
    }

    public void readfromClientSwitchCase() throws IOException {
        readfromClient();
        sendtoClient();
        read_num_Switch_Case = new BufferedReader(new InputStreamReader(server_socket.getInputStream()));
        getSwitchCase = read_num_Switch_Case.readLine();
        System.out.println("~Switch Case from Client: " + getSwitchCase);
        switch (getSwitchCase) {
            case "1":
                System.out.println("Case 1 from Client. Do send to Client check of his money: " + money);
                break;
            case "2":
                System.out.println("Case 2 from Client. Give out money of this Client");
                System.out.println("Case are choose");
                read_Client_toCalculation();
                sendtoClientwithCalculation();
                break;
        }
    }

    /*public void Manipulation_with_getSwitchCase(String getSwitch) {
        String manipulation_getSwitch = getSwitch;
        switch (manipulation_getSwitch){
            case "1":
                System.out.println("Case 1 from Client. Do send to Client his money" + money);
                break;
            case "2":
                System.out.println("Case 2 from Client. Do make request for pay");
                break;
        }
    }*/

    public void read_Client_toCalculation() throws IOException{
        read_QTY_Money_from_Client = new BufferedReader(new InputStreamReader(server_socket.getInputStream()));
        new_read_QTY_Money_from_Client = read_QTY_Money_from_Client.readLine();
        System.out.println("QTY for withdraw: " + new_read_QTY_Money_from_Client);
    }

    public void sendtoClientwithCalculation() throws IOException {

        int moneyWithdraw = Integer.parseInt(new_read_QTY_Money_from_Client);
        int get_Money_minus_Money_of_need_to_Client = money - moneyWithdraw;
        money = get_Money_minus_Money_of_need_to_Client;
        String sendMoneyMinusWithdraw = Integer.toString(get_Money_minus_Money_of_need_to_Client);
        send_Client_his_Money = new PrintWriter(server_socket.getOutputStream(), true);
        send_Client_his_Money.println("~ Server - You withdraw money: " + new_read_QTY_Money_from_Client);
        System.out.println("~ The rest of money: " + sendMoneyMinusWithdraw + " $");
        System.out.println("Answer to Client send");
    }
}
