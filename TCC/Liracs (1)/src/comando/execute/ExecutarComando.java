/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comando.execute;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author aluno
 */
public class ExecutarComando {

    private ExecutarComando() {
    }

    public static void executarSite(String comando) {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI(comando);
            desktop.browse(uri);
        } catch (IOException | URISyntaxException ioe) {
            System.err.println("Erro no comando!" + "\n" + ioe);
        }

    }

    public static void abrirArquivo(String comando) {
        Runtime rt = Runtime.getRuntime();
        try {
            Process pr = rt.exec(comando);
        } catch (IOException ex) {
            System.err.println("Erro no comando!" + "\n" + ex);
        }
    }
}
