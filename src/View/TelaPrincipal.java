package View;

import java.awt.*;
import javax.swing.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema Acadêmico");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout(10, 10));
        painel.setBackground(new Color(230, 240, 255));

        JLabel titulo = new JLabel("Sistema Acadêmico", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        painel.add(titulo, BorderLayout.NORTH);

        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(6, 1, 10, 10));
        menu.setBackground(new Color(230, 240, 255));

        JButton btnAlunos = new JButton("Gerenciar Alunos");
        JButton btnTurmas = new JButton("Gerenciar Turmas");
        JButton btnDisciplinas = new JButton("Gerenciar Disciplinas");
        JButton btnNotas = new JButton("Lançar Notas");
        JButton btnRelatorios = new JButton("Relatórios");
        JButton btnSair = new JButton("Sair");

        menu.add(btnAlunos);
        menu.add(btnDisciplinas);
        menu.add(btnNotas);
        menu.add(btnTurmas);
        menu.add(btnRelatorios);
        menu.add(btnSair);

        btnAlunos.setFocusPainted(false);
        btnDisciplinas.setFocusPainted(false);
        btnNotas.setFocusPainted(false);
        btnTurmas.setFocusPainted(false);
        btnRelatorios.setFocusPainted(false);
        btnSair.setFocusPainted(false);

        painel.add(menu, BorderLayout.CENTER);

        //ações
        btnAlunos.addActionListener(e -> abrirTela(new TelaAluno()));

        btnDisciplinas.addActionListener(e -> abrirTela (new TelaDisciplina()));

        btnNotas.addActionListener(e -> abrirTela (new TelaNota()));

        btnTurmas.addActionListener(e -> abrirTela (new TelaTurma()));

        btnRelatorios.addActionListener(e -> abrirTela (new TelaRelatorio()));

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