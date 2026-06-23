package View;

import javax.swing.*;
import java.awt.*;

import Controller.AlunoController;
import Controller.DisciplinaController;
import Controller.NotaController;
import Controller.TurmaController;

import Repository.AlunoRepository;
import Repository.DisciplinaRepository;
import Repository.NotaRepository;
import Repository.TurmaRepository;

public class TelaRelatorio extends JFrame {

    private JButton btnRelatorioAlunos;
    private JButton btnRelatorioDisciplinas;
    private JButton btnRelatorioTurmas;
    private JButton btnRelatorioNotas;
    private JButton btnLimpar;
    private JButton btnVoltar;

    private JTextArea txtRelatorio;

    private AlunoController alunoController;
    private DisciplinaController disciplinaController;
    private TurmaController turmaController;
    private NotaController notaController;

    public TelaRelatorio() {

        setTitle("Relatórios");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        alunoController =
            new AlunoController(
                new AlunoRepository());
        alunoController.carregarArquivo();

        disciplinaController =
        new DisciplinaController(
                new DisciplinaRepository());
        disciplinaController.carregarArquivo();

        turmaController =
            new TurmaController(
                new TurmaRepository());
        turmaController.carregarArquivo();

        notaController =
            new NotaController(
                new NotaRepository());
        notaController.carregarArquivo();

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

            for(var aluno : alunoController.listarAlunos()) {

                txtRelatorio.append(
                    aluno.getMatricula()
                    + " - "
                    +aluno.getNome()
                    + " - "
                    + aluno.getCurso()
                    + " - "
                    + aluno.getPeriodo()
                    + "\n"
                );

            }

        });

        
        btnRelatorioDisciplinas.addActionListener(e -> {

            txtRelatorio.setText("");
            txtRelatorio.append("RELATÓRIO DE DISCIPLINAS\n\n");

            for(var disciplina :
                disciplinaController.listarDisciplinas()) {

                txtRelatorio.append(
                    disciplina.getCodigo()
                    + " - "
                    + disciplina.getNome()
                    + " - "
                    + disciplina.getCargaHoraria()
                    + "h\n");

            }

        });


        btnRelatorioTurmas.addActionListener(e -> {

            txtRelatorio.setText("");
            txtRelatorio.append("RELATÓRIO DE TURMAS\n\n");

            for(var turma :
                turmaController.listarTurmas()) {

                txtRelatorio.append(
                    turma.getCodigo()
                    + " - "
                    + turma.getDisciplina().getCodigo()
                    + " - "
                    + turma.getQuantidadeAlunos()
                    + " aluno(s)\n");

            }

        });

        btnRelatorioNotas.addActionListener(e -> {

            txtRelatorio.setText("");
            txtRelatorio.append("RELATÓRIO DE NOTAS\n\n");

            for(var nota :
                notaController.listarNotas()) {

                txtRelatorio.append(
                    nota.getMatriculaAluno()
                    + " - "
                    + nota.getCodigoDisciplina()
                    + " - Média: "
                    + nota.calcularMedia()
                    + " - "
                    + nota.getSituacao()
                    + "\n");

            }

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