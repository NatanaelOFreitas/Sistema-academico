package View;

import javax.swing.*;
import java.awt.*;

public class TelaRelatorio extends JFrame {

    private JButton btnRelatorioAlunos;
    private JButton btnRelatorioDisciplinas;
    private JButton btnRelatorioTurmas;
    private JButton btnRelatorioNotas;
    private JButton btnLimpar;
    private JButton btnVoltar;

    private JTextArea txtRelatorio;

    public TelaRelatorio() {

        setTitle("Relatórios");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10,10));
        painel.setBackground(new Color(230,240,255));

        JLabel titulo = new JLabel("Relatórios do Sistema Acadêmico",
                SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD,24));

        painel.add(titulo, BorderLayout.NORTH);

        JPanel botoes = new JPanel(new GridLayout(2,3,10,10));
        botoes.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        botoes.setBackground(new Color(230,240,255));

        btnRelatorioAlunos = new JButton("Alunos");
        btnRelatorioDisciplinas = new JButton("Disciplinas");
        btnRelatorioTurmas = new JButton("Turmas");
        btnRelatorioNotas = new JButton("Notas");
        btnLimpar = new JButton("Limpar");
        btnVoltar = new JButton("Voltar");

        botoes.add(btnRelatorioAlunos);
        botoes.add(btnRelatorioDisciplinas);
        botoes.add(btnRelatorioTurmas);
        botoes.add(btnRelatorioNotas);
        botoes.add(btnLimpar);
        botoes.add(btnVoltar);

        painel.add(botoes, BorderLayout.CENTER);

        txtRelatorio = new JTextArea();
        txtRelatorio.setEditable(false);
        txtRelatorio.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(txtRelatorio);
        scroll.setPreferredSize(new Dimension(650,250));

        painel.add(scroll, BorderLayout.SOUTH);

        add(painel);


        btnRelatorioAlunos.addActionListener(e -> {

            txtRelatorio.setText("");
            txtRelatorio.append("RELATÓRIO DE ALUNOS\n\n");
            txtRelatorio.append("Implementar usando AlunoController.\n");

        });

        btnRelatorioDisciplinas.addActionListener(e -> {

            txtRelatorio.setText("");
            txtRelatorio.append("RELATÓRIO DE DISCIPLINAS\n\n");
            txtRelatorio.append("Implementar usando DisciplinaController.\n");

        });

        btnRelatorioTurmas.addActionListener(e -> {

            txtRelatorio.setText("");
            txtRelatorio.append("RELATÓRIO DE TURMAS\n\n");
            txtRelatorio.append("Implementar usando TurmaController.\n");

        });

        btnRelatorioNotas.addActionListener(e -> {

            txtRelatorio.setText("");
            txtRelatorio.append("RELATÓRIO DE NOTAS\n\n");
            txtRelatorio.append("Implementar usando NotaController.\n");

        });

        btnLimpar.addActionListener(e ->
                txtRelatorio.setText(""));

        btnVoltar.addActionListener(e -> {

            new TelaPrincipal().setVisible(true);
            dispose();

        });

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new TelaRelatorio().setVisible(true));

    }

}