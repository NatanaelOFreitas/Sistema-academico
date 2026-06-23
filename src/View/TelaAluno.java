package View;

import javax.swing.*;
import java.awt.*;
import Controller.*;
import Repository.*;

public class TelaAluno extends JFrame {

    private JTextField txtMatricula;
    private JTextField txtNome;
    private JTextField txtCurso;
    private JTextField txtPeriodo;
    private JButton btnCadastrar;

    private AlunoController alunoController;

    public TelaAluno() {
        setTitle("Cadastro de Aluno");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        AlunoRepository repository = new AlunoRepository();
        alunoController = new AlunoController(repository);

        JPanel painel = new JPanel(new GridLayout(5, 2, 25, 25));
        painel.setBackground(new Color(230, 240, 255));

        painel.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        painel.add(txtMatricula);

        painel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painel.add(txtNome);

        painel.add(new JLabel("Curso:"));
        txtCurso = new JTextField();
        painel.add(txtCurso);

        painel.add(new JLabel("Período:"));
        txtPeriodo = new JTextField();
        painel.add(txtPeriodo);

        btnCadastrar = new JButton("Cadastrar");
        painel.add(new JLabel());
        painel.add(btnCadastrar);

        add(painel);

        btnCadastrar.addActionListener(e -> {

            try {
                String matricula = txtMatricula.getText();
                String nome = txtNome.getText();
                String curso = txtCurso.getText();
                int periodo = Integer.parseInt(txtPeriodo.getText());

                boolean sucesso = alunoController.cadastrarAluno(
                        nome,
                        matricula,
                        curso,
                        periodo
                );

                if (sucesso) {
                    JOptionPane.showMessageDialog(this,
                            "Aluno cadastrado com sucesso!\nNome: " + nome);

                    txtMatricula.setText("");
                    txtNome.setText("");
                    txtCurso.setText("");
                    txtPeriodo.setText("");

                } else {
                    JOptionPane.showMessageDialog(this,
                            "Erro: matrícula já existe ou dados inválidos!");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Período deve ser um número!");
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}
