package View;

import java.awt.*;
import javax.swing.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema Acadêmico");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout(10, 10));
        painel.setBackground(new Color(230, 240, 255));

        JLabel titulo = new JLabel("Sistema Acadêmico", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        painel.add(titulo, BorderLayout.NORTH);

        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(5, 1, 10, 10));
        menu.setBackground(new Color(230, 240, 255));

        JButton btnAlunos = new JButton("Gerenciar Alunos");
        JButton btnDisciplinas = new JButton("Gerenciar Disciplinas");
        JButton btnNotas = new JButton("Lançar Notas");
        JButton btnRelatorios = new JButton("Relatórios");
        JButton btnSair = new JButton("Sair");

        menu.add(btnAlunos);
        menu.add(btnDisciplinas);
        menu.add(btnNotas);
        menu.add(btnRelatorios);
        menu.add(btnSair);

        btnAlunos.setFocusPainted(false);
        btnDisciplinas.setFocusPainted(false);
        btnNotas.setFocusPainted(false);
        btnRelatorios.setFocusPainted(false);
        btnSair.setFocusPainted(false);

        painel.add(menu, BorderLayout.CENTER);

        //ações
        btnAlunos.addActionListener(e -> abrirTela(new TelaAluno()));

        btnDisciplinas.addActionListener(e -> abrirTela (new TelaDisciplina()));

        btnNotas.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Abrir Tela de Notas")
        );

        btnRelatorios.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Abrir Tela de Relatórios")
        );

        btnSair.addActionListener(e -> System.exit(0));

        add(painel);
    }

    private void abrirTela(JFrame novaTela) {
        novaTela.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}