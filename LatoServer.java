/*
Taioli Diego 5B IA
 */
package latoserver;

/**
 *
 * @author Diego
 */
public class LatoServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Server servente = new Server();
        servente.attendi();
        servente.comunica();
    }
    
}
