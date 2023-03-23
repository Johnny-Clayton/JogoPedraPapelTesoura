package jogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoPedraPapelTesoura extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
    private JLabel lblJogador, vtrJogador;
    private JLabel lblMaquina, vtrMaquina;
    private JButton btnPedra;
    private JButton btnPapel;
    private JButton btnTesoura;
    private JButton btnParar; 
    private String[] opcoes = {"Pedra", "Papel", "Tesoura"};
    private int opcaoMaquina;
    private int vitoriasJogador = 0; 
    private int vitoriasMaquina = 0; 

    public JogoPedraPapelTesoura() {
        super("Jogo Pedra, Papel e Tesoura");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getWidth()) / 2;
        int y = (dim.height - getHeight()) / 2;
        setLocation(x, y);

        lblJogador = new JLabel("Jogador: ");
        add(lblJogador);
        
        vtrJogador = new JLabel("Vitórias do Jogador: ");
        add(vtrJogador);

        lblMaquina = new JLabel("Máquina: ");
        add(lblMaquina);
        
        vtrMaquina = new JLabel("Vitórias da Máquina: ");
        add(vtrMaquina);

        btnPedra = new JButton("Pedra");
        btnPedra.addActionListener(this);
        add(btnPedra);

        btnPapel = new JButton("Papel");
        btnPapel.addActionListener(this);
        add(btnPapel);

        btnTesoura = new JButton("Tesoura");
        btnTesoura.addActionListener(this);
        add(btnTesoura);

        btnParar = new JButton("Parar");
        btnParar.addActionListener(this);
        add(btnParar);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String escolhaJogador = e.getActionCommand();
        if (escolhaJogador.equals("Parar")) {
            JOptionPane.showMessageDialog(this, "Você venceu " + vitoriasJogador + " rodada(s).");
            
            if(vitoriasJogador > vitoriasMaquina) {
            	JOptionPane.showMessageDialog(this, "Você Venceu a Máquina");
            } else if (vitoriasJogador < vitoriasMaquina) {
            	JOptionPane.showMessageDialog(this, "Você Perdeu para Máquina");
            } else {
            	JOptionPane.showMessageDialog(this, "Você Empatou com Máquina");
            }
            
            dispose();
            return;
        }

        lblJogador.setText("Jogador: " + escolhaJogador);
        vtrJogador.setText("Jogador: " + vitoriasJogador);

        opcaoMaquina = (int) (Math.random() * 3);
        lblMaquina.setText("Máquina: " + opcoes[opcaoMaquina]);
        vtrMaquina.setText("Jogador: " + vitoriasMaquina);

        int resultado = verificaResultado(escolhaJogador, opcoes[opcaoMaquina]);
        
        switch (resultado) {
		case 0: 
			JOptionPane.showMessageDialog(this, "Empate!"); break;
		case 1:
			vitoriasJogador++; 
            JOptionPane.showMessageDialog(this, "Jogador venceu!");
			break;
		case 2:
			vitoriasMaquina++;
			JOptionPane.showMessageDialog(this, "Máquina venceu!");
			break;
		default:
			break;
		}
    }

    private int verificaResultado(String escolhaJogador, String escolhaMaquina) {
        if (escolhaJogador.equals(escolhaMaquina)) {
            return 0; // empate
        } else if (escolhaJogador.equals("Pedra") && escolhaMaquina.equals("Tesoura")
                || escolhaJogador.equals("Papel") && escolhaMaquina.equals("Pedra")
                || escolhaJogador.equals("Tesoura") && escolhaMaquina.equals("Papel")) {
            return 1; // jogador vence
        } else {
            return 2; // máquina vence
        }
    }

    public static void main(String[] args) {
        new JogoPedraPapelTesoura();
    }
}

