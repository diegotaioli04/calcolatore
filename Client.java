/*
Taioli Diego 5B IA
 */
package latoclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Diego
 */
public class Client {
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket miosocket;
    BufferedReader tastiera;
    String richiesta;
    String risposta;
    DataOutputStream ClientOutput;
    BufferedReader ClientInput;
    
    public Socket connetti(){
        System.out.print("2 CLIENT partito in esecuzione ... "+'\n');
        
        try
        {      
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            miosocket = new Socket(nomeServer, portaServer);
            ClientOutput = new DataOutputStream(miosocket.getOutputStream());
            ClientInput = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));            
        }
        catch(UnknownHostException e){
            System.err.print("Host sconosciuto"); 
        }
            
        catch(Exception e){
            System.out.print(e.getMessage());
            System.out.print("Errore durante la connessione! ");
            System.exit(1);
        }
        return miosocket;
    }
    
    public void comunica(){
        
        try
        {       
            String MyString = " inserisci il primo numero da trasmettere al server"+'\n';
            String buffer = " ... invio il numero al server e attendo ... " + '\n';
            String dividendo = " ";
                output(MyString, buffer);
            ClientOutput.writeBytes(richiesta+'\n');
           
            MyString = " ... scegli il tipo di operazione "+'\n'
                    + "1 - ADDIZIONE " + '\n'
                    + "2 - SOTTRAZIONE " + '\n'
                    + "3 - MOLTIPLICAZIONE " + '\n'
                    + "4 - DIVISIONE " + '\n';
            do{
                output(MyString, buffer);
            }while((Integer.parseInt(richiesta)<1)||(Integer.parseInt(richiesta)>4));
            ClientOutput.writeBytes(richiesta+'\n');
            dividendo = richiesta;
            
            MyString = " inserisci il secondo numero da trasmettere al server "+'\n';
            if(Integer.parseInt(dividendo) == 4){
                MyString = MyString + " ricorda che non si può dividere per zero " + '\n';
                do{
                    output(MyString, buffer);               
                }while(Integer.parseInt(richiesta) == 0);
            }
            else{          
                output(MyString, buffer);
            }
            ClientOutput.writeBytes(richiesta+'\n');
            
            //leggo la risposta dal server
            risposta = ClientInput.readLine(); // riceve il risultato
            System.out.print(" il risultato è "+'\n'+risposta +'\n');
            //chiudo la connessione
            System.out.print(" CLIENT: termina elaborazione e chiude connessione ");
            miosocket.close();
        }
        catch(Exception e )
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server|");
            System.exit(1);
        }
    }
    
    public void output(String s1, String s2){
        try{
            System.out.print(s1 + '\n');
            do{           
                richiesta = tastiera.readLine();
            }while(TryParse(richiesta) == false);
            //spedisce al server          
            System.out.print(s2 + '\n');
            //ClientOutput.writeBytes(richiesta+'\n');
        }
        catch(Exception e ){}
        
    }
    
    public boolean TryParse(String reader){
        //controlla se l'input da tastiera è un intero
        try { 
             Integer.parseInt(reader); 
             return true; // se è intero
          } catch (NumberFormatException e) { 
              System.out.println("Non è un intero");
             return false; // se non è intero
          } 
    }
    
}
