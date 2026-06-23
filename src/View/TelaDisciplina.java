package View;

import javax.swing.*;
import java.awt.*;
import Controller.*;
import Repository.*;

public class TelaDisciplina extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtCargaHoraria;
    private JButton btnCadastrar;

    private DisciplinaController disciplinaController;

    public TelaDisciplina (){
        setTitle("Cadastro de Disciplinas");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        DisciplinaRepository repository = new DisciplinaRepository();
        disciplinaController = new DisciplinaController(repository);

        JPanel painel = new JPanel(new GridLayout(5, 2, 25, 25));
        painel.setBackground(new Color(230, 240, 255));

        painel.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        painel.add(txtCodigo);

        painel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painel.add(txtNome);

        painel.add(new JLabel("Carga Horária:"));
        txtCargaHoraria = new JTextField();
        painel.add(txtCargaHoraria);

        btnCadastrar = new JButton("Cadastrar");
        painel.add(new JLabel());
        painel.add(btnCadastrar);

        add(painel);

        btnCadastrar.addActionListener(e -> {

            try {
                String codigo = txtCodigo.getText();
                String nome = txtNome.getText();
                int cargaHoraria = Integer.parseInt(txtCargaHoraria.getText());

                boolean sucesso = disciplinaController.cadastrarDisciplina(
                        codigo,
                        nome,
                        cargaHoraria
                );

                if (sucesso) {
                    JOptionPane.showMessageDialog(this,
                            "Disciplina cadastrada com sucesso!\nNome: " + nome);

                    txtCodigo.setText("");
                    txtNome.setText("");
                    txtCargaHoraria.setText("");

                } else {
                    JOptionPane.showMessageDialog(this,
                            "Erro: disciplina já existe ou dados inválidos!");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Carga Horária deve ser um número!");
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}
