/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processos;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JFrame;

/**
 *
 * @author Aluno
 */
public class Processos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       InterfaceGráfica ig=new InterfaceGráfica();
       ig.getFramePrincipal().setVisible(true);
       ig.getFramePrincipal().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       ig.getFramePrincipal().setSize(500, 400);
    }

}
