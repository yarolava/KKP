package tcp_metro;

import tcp_metro.calc.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread{
    private String appender = "# |Сообщение от обработчика клиентов| ";
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean isWork;
    private MetroCardBank cardBank;
    private Socket socket;
    private Thread t;

    public ClientHandler(MetroCardBank cardBank, Socket socket) {
        this.cardBank = cardBank;
        this.socket = socket;
        this.isWork = true;
        try{
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        t = new Thread(this);
    }

    @Override
    public void run(){
        //synchronized (cardBank){
            System.out.println(appender + "Обслуживание клиента " + getSocketInfo() + " начато.");

            while (isWork){
                Object operation;
                try {
                    operation = in.readObject();
                    processOperation(operation);
                    System.out.println("\n" + appender + "\n====== Состояние банка карт: =============");
                    System.out.println(cardBank);
                    System.out.println("==========================================\n");
                } catch (ClassNotFoundException | IOException e) {
                    isWork = false;
                }
            }
            try{
                System.out.println(appender + "Обслуживание клиента" + getSocketInfo() + " закончено");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        //}
    }

    public String getSocketInfo(){
        return  socket.getInetAddress().getCanonicalHostName() + " на порту " + socket.getPort();
    }

    private void processOperation(Object operation) throws IOException, ClassNotFoundException {
        if (operation instanceof StopOperation)
            finish();
        else if(operation instanceof AddMetroCardOperation)
            addCard(operation);
        else if (operation instanceof AddMoneyOperation)
            addMoney(operation);
        else if (operation instanceof PayMoneyOperation)
            payMoney(operation);
        else if (operation instanceof RemoveCardOperation)
            removeCard(operation);
        else if (operation instanceof ShowBalanceOperation)
            showBalance(operation);
        else if (operation instanceof GetCardInfoOperation)
            getCardInfo(operation);
        else
            logError();

    }

    private void finish() throws IOException {
        isWork = false;
        out.writeObject("Конец обслуживания " + getSocketInfo());
        out.flush();
    }

    private void addCard(Object operation) throws IOException, ClassNotFoundException{
        AddMetroCardOperation addMetroCardOperation = (AddMetroCardOperation)operation;
        cardBank.addCard(addMetroCardOperation.getMetroCard());
        out.writeObject("Карта добавена");
        out.flush();
    }

    private void addMoney(Object operation) throws IOException {
        AddMoneyOperation addMoneyOperation = (AddMoneyOperation) operation;
        boolean isAdded = cardBank.addMoney(addMoneyOperation.getSerialNumber(), addMoneyOperation.getMoney());

        if (isAdded)
            out.writeObject("Средства добавлены");
        else
            out.writeObject("Невозможно добавить средства, карточки не существует");
        out.flush();
    }

    private void payMoney(Object operation) throws IOException {
        PayMoneyOperation payMoneyOperation = (PayMoneyOperation)operation;
        boolean isPayd = cardBank.payMoney(payMoneyOperation.getSerialNumber(), payMoneyOperation.getMoney());
        if (isPayd)
            out.writeObject("Проезд оплачен!");
        else
            out.writeObject("Недостаточно средств!");
        out.flush();
    }

    private void removeCard(Object operation) throws IOException {
        RemoveCardOperation removeCardOperation = (RemoveCardOperation) operation;
        boolean isRemove = cardBank.removeCard(removeCardOperation.getSerialNumber());
        if (isRemove)
            out.writeObject("Карточка удалена!");
        else
            out.writeObject("Карточки не существует!");
        out.flush();
    }

    private void showBalance(Object operation) throws IOException {
        ShowBalanceOperation showBalanceOperation = (ShowBalanceOperation) operation;
        double balance = cardBank.getBalance(showBalanceOperation.getSerialNumber());

        if (balance == -1)
            out.writeObject("Карточки не сущнествует!");
        else{
            out.writeObject("Карта: " + showBalanceOperation.getSerialNumber() + " balance " + balance);
        }
        out.flush();
    }

    private void logError() throws IOException {
        out.writeObject("Неудачная операция");
        out.flush();
    }

    private void getCardInfo(Object operation) throws IOException {
        GetCardInfoOperation cardInfoOperation = (GetCardInfoOperation) operation;
        String info = cardBank.getCardInfo(cardInfoOperation.getSerialNumber());

        if (info == null)
            out.writeObject("Карты с таким номером не существует");
        else
            out.writeObject(info);
        out.flush();
    }

    public void startService() {
        t.start();
    }
}