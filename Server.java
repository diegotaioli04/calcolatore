/*
Taioli Diego 5B IA
 */
package latoserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Diego
 */
public class Server {
    ServerSocket server = null;
    Socket client = null;
    String richiesta = null; //ricevuta dal client
    String risposta = null; //inviata al client
    BufferedReader ServerInput;
    DataOutputStream ServerOutput;
    
    public Server(){}

    public Socket attendi()
    {
        try{
           
            System.out.println(" - SERVER partito in esecuzione ...");
            server = new ServerSocket(6789);
            client = server.accept();
            server.close();
            ServerInput = new BufferedReader(new InputStreamReader (client.getInputStream()));
            ServerOutput = new DataOutputStream(client.getOutputStream());      
        
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server! ");
            System.exit(1);
        }
        return client;
    }
    
    public void comunica(){
        try{
            int operatore = 0, var1 = 0, var2 = 0;
            String guida = " - ricevuto il numero dal client : ";
            var1 = input(guida);
            guida = " - ricevuto l'operatore dal client : ";
            operatore = input(guida);
            switch(operatore){
                case 1:
                    var2 = input(guida);
                    risposta = Integer.toString(somma(var1, var2));
                    break;
                case 2:
                    var2 = input(guida);           
                    risposta = Integer.toString(differenza(var1, var2));
                    break;
                case 3:
                    var2 = input(guida);
                    risposta = Integer.toString(prodotto(var1, var2));
                    break;
                case 4:
                    do{
                        var2 = input(guida);
                    }while(var2 == 0);
                    risposta = Integer.toString(quoziente(var1, var2));
                    break;
            }
            System.out.println(" - invio la stringa modificata al client. ");
            ServerOutput.writeBytes(risposta+'\n');
            
            System.out.println(" - SERVER: fine ");
            client.close();
            
        }catch(Exception e)
        {
            
        }
    }
    
    public int input (String s){
        int i = 0;
        try{
            richiesta = ServerInput.readLine();
            i = parseInt(richiesta);
            System.out.println(s + richiesta);
            return i;
        }
        catch(Exception e){}
        return i;
    }
    
    public int somma(int x, int y){
        x = x + y;
        return x;
    }
    
    public int differenza(int x, int y){
        x = x - y;
        return x;
    }
    
    public int prodotto(int x, int y){
        x = x * y;
        return x;
    }
    
    public int quoziente(int x, int y){
        x = x / y;
        return x;
    }
    
    
    
}
