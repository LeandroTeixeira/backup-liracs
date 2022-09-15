/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processos;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Aluno
 */
public class InterfaceGráfica {

    JFrame framePrincipal;
    JPanel painelBotoes, painelEventos;
    JButton[] botão = new JButton[10];
    String[] sbotão;
    JButton[] comandos = new JButton[2];
    String[] scomandos;
    JButton definirAcao;
    JLabel error;

    public InterfaceGráfica() {
        framePrincipal = new JFrame("TCC");

        configurarBotões();
        configurarComando();
        configurarEventos();

        framePrincipal.setLayout(new GridLayout(0, 1));

    }

    private void configurarBotões() {
        sbotão = new String[botão.length];
        painelBotoes = new JPanel();
        int cont = 0;
        for (int i = 0; i < botão.length; i++) {
            botão[cont] = new JButton("botão " + cont);
            painelBotoes.add(botão[cont++]);
        }
        definirAcao = new JButton("Definir comando");
        painelBotoes.add(definirAcao);
        framePrincipal.add(painelBotoes);
    }

    private void configurarComando() {
        error = new JLabel();
        error.setForeground(Color.RED);
        painelEventos = new JPanel();
        scomandos = new String[comandos.length];
        comandos[0] = new JButton("Arquivo");
        comandos[1] = new JButton("Site");

        painelEventos.add(comandos[0]);
        painelEventos.add(comandos[1]);
        comandos[0].setEnabled(false);
        comandos[1].setEnabled(false);
        painelEventos.add(error);
        framePrincipal.add(painelEventos);
    }

    public JFrame getFramePrincipal() {
        return framePrincipal;
    }

    public void setFramePrincipal(JFrame framePrincipal) {
        this.framePrincipal = framePrincipal;
    }

    private void teste() throws IOException {
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        Desktop desktop = null;
        desktop = Desktop.getDesktop();
        URI uri = null;
        try {
            uri = new URI("www.fb.com");
            desktop.browse(uri);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (URISyntaxException use) {
            use.printStackTrace();
        }
    }

    private void configurarEventos() {
        Evento ev = new Evento();
        definirAcao.addActionListener(ev);
        for (int i = 0; i < botão.length; i++) {
            if (botão[i] == null) {
                break;
            }
            botão[i].addActionListener(ev);
        }
        for (int i = 0; i < comandos.length; i++) {
            if (comandos[i] == null) {
                break;
            }
            comandos[i].addActionListener(ev);
        }
    }

    private void habdesab() {
        for (int i = 0; i < comandos.length; i++) 
            comandos[i].setEnabled(!comandos[i].isEnabled());
        
        for(int i=0;i<botão.length;i++)
            botão[i].setEnabled(!botão[i].isEnabled());
    }

    private void runCommand(String s) {
        if (s != null) {
            error.setText("");
            String comando = s;
            if (comando.substring(0, 4).equals("UR -")) {
                comando = comando.substring(4);
                Desktop desktop = Desktop.getDesktop();
                try {
                    URI uri = new URI(comando);
                    desktop.browse(uri);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (URISyntaxException use) {
                    use.printStackTrace();
                }
            }
            if (comando.substring(0, 4).equals("rt -")) {
                comando = comando.substring(4);
                Runtime rt = Runtime.getRuntime();
                try {
                    Process pr = rt.exec(comando);
                } catch (IOException ex) {
                    Logger.getLogger(InterfaceGráfica.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            error.setText("Comando não configurado!");
        }
    }

    private class Evento implements ActionListener {

        String comando = null;

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == definirAcao) {
                error.setText("");
                habdesab();
            }
            if (e.getSource() == comandos[0]) {
                comando = "rt -" + getArquivo();
                habdesab();
            }
            if (e.getSource() == comandos[1]) {
                comando = "UR -" + JOptionPane.showInputDialog("Insira o site que deseja acessar(não se esqueça do www)");
                habdesab();
            }

            for (int i = 0; i < botão.length; i++) {
                if (e.getSource() == botão[i]) {
                    if (comando!=null) {
                        sbotão[i] = comando;
                        if (sbotão[i].substring(0, 4).equals("UR -")) {
                            botão[i].setText(comando.substring(sbotão[i].indexOf(".") + 1));
                        }
                        if (sbotão[i].subSequence(0, 4).equals("rt -")) {
                            botão[i].setText(comando.substring(sbotão[i].lastIndexOf("\\") + 1, comando.lastIndexOf(".")));
                        }
                        comando=null;
                    } else {
                        runCommand(sbotão[i]);
                    }

                }
            }
        }

        private String getArquivo() {
            String Caminho = null;
            JFileChooser file = new JFileChooser();
            int retorno = file.showOpenDialog(null);
            if (retorno == JFileChooser.APPROVE_OPTION) {
                Caminho = file.getSelectedFile().getAbsolutePath();
            }
            return Caminho;
        }
    }
}
