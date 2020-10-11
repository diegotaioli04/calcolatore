/*
Taioli Diego 5B IA
 */
package latoclient;

/**
 *
 * @author Diego
 */
public class LatoClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Client cliente = new Client();
        cliente.connetti();
        cliente.comunica();
    }
    
}
