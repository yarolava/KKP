package tcp_metro;

import tcp_metro.calc.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Client {
    private int port = -1;
    private String server;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public Client(String server, int port){
        this.port = port;
        this.server = server;
        try{
            socket = new Socket();
            socket.connect(new InetSocketAddress(server, port), 1000);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("=== Вы успешно подключились к серверу! ===");
        } catch (SocketTimeoutException e){
            System.out.println("Время ожидания подключения к серверу вышло.");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Ошибка подключения к серверу.");
            System.exit(0);
        }
    }

    public void applyOperation(CardOperation operation){
        try {
            out.writeObject(operation);
            out.flush();
            System.out.println("@ |Ответ сервера|\n" + in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Сервер не отвечает.");
            System.exit(0);
        }
    }

    private void printMenu(){
        System.out.println("================= Меню ===================");
        System.out.println("|| 1) Получить информацию по карте     ||");
        System.out.println("|| 2) Пополнить счет                   ||");
        System.out.println("|| 3) Зарегестрировать новую карту     ||");
        System.out.println("|| 4) Узнать остаток средств на карте  ||");
        System.out.println("|| 5) Заплатить за проезд (2000$)      ||");
        System.out.println("|| 6) Удалить карту                    ||");
        System.out.println("|| 7) Выход                            ||");
        System.out.println("==========================================");
        System.out.print("  Сделайте Ваш выбор: ");
    }

    public void startInteraction(){
        Scanner in = new Scanner(System.in);
        String serialNumber;
        CardOperation cardOperation = null;
        int choice = -1;
        while (choice != 7){
            printMenu();
            choice = in.nextInt();

            switch (choice){
                case 1:
                    serialNumber = readCardSerialNumber(in);
                    cardOperation = new GetCardInfoOperation(serialNumber);
                    break;

                case 2:
                    serialNumber = readCardSerialNumber(in);
                    double money = readMoney(in);
                    cardOperation = new AddMoneyOperation(serialNumber, money);
                    break;

                case 3:
                    MetroCard metroCard = readCard(in);
                    cardOperation = new AddMetroCardOperation(metroCard);
                    break;

                case 4:
                    serialNumber = readCardSerialNumber(in);
                    cardOperation = new ShowBalanceOperation(serialNumber);
                    break;

                case 5:
                    serialNumber = readCardSerialNumber(in);
                    cardOperation = new PayMoneyOperation(serialNumber, 2000);
                    break;

                case 6:
                    serialNumber = readCardSerialNumber(in);
                    cardOperation = new RemoveCardOperation(serialNumber);
                    break;

                case 7:
                    cardOperation = new StopOperation();
                    in.close();
                    break;

                default:
                    System.out.println("Ошибка ввода. Повторите запрос.");
                    cardOperation = null;
                    break;
            }

            if (cardOperation != null)
                applyOperation(cardOperation);
        }
    }

    private String readCardSerialNumber(Scanner in){
        System.out.print("Введите серийный номер карты: ");
        return in.next();
    }

    private double readMoney(Scanner in){
        System.out.print("Внесите купюру: ");
        return in.nextInt();
    }

    private User readUser(Scanner in){
        System.out.print("Введите имя: ");
        String name = in.next();

        System.out.print("Введите фамилию: ");
        String surname = in.next();

        System.out.print("Укажите стать: ");
        String sex = in.next();

        System.out.print("Введите дату рождения: ");
        String birthday = in.next();

        return new User(name, surname, sex, birthday);
    }

    private MetroCard readCard(Scanner in){
        User user = readUser(in);
        System.out.print("Введите учебное заведение: ");
        String university = in.next();
        String serialNumber = readCardSerialNumber(in);

        return new MetroCard(serialNumber, user, university);
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 7891);
        client.startInteraction();
    }
}