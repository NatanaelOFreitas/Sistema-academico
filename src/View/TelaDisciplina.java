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
    private JButton btnBuscar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnListar;
    private JButton btnVoltar;

    private JTextArea txtResultado;

    private DisciplinaController disciplinaController;

    public TelaDisciplina() {

        setTitle("Cadastro de Disciplinas");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        DisciplinaRepository repository = new DisciplinaRepository();
        disciplinaController = new DisciplinaController(repository);
        disciplinaController.carregarArquivo();

        JPanel painel = new JPanel(new BorderLayout(10,10));
        painel.setBackground(new Color(230,240,255));

        JLabel titulo = new JLabel("Cadastro de Disciplinas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD,24));

        painel.add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(3,2,15,15));
        formulario.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        formulario.setBackground(new Color(230,240,255));

        formulario.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        formulario.add(txtCodigo);

        formulario.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        formulario.add(txtNome);

        formulario.add(new JLabel("Carga Horária:"));
        txtCargaHoraria = new JTextField();
        formulario.add(txtCargaHoraria);

        painel.add(formulario, BorderLayout.CENTER);

        JPanel botoes = new JPanel(new GridLayout(2,3,10,10));
        botoes.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
        botoes.setBackground(new Color(230,240,255));

        btnCadastrar = new JButton("Cadastrar");
        btnBuscar = new JButton("Buscar");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");
        btnListar = new JButton("Listar");
        btnVoltar = new JButton("Voltar");

        botoes.add(btnCadastrar);
        botoes.add(btnBuscar);
        botoes.add(btnAtualizar);
        botoes.add(btnExcluir);
        botoes.add(btnListar);
        botoes.add(btnVoltar);

        JPanel sul = new JPanel(new BorderLayout());
        sul.setBackground(new Color(230,240,255));

        sul.add(botoes, BorderLayout.NORTH);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);

        JScrollPane scroll = new JScrollPane(txtResultado);

        sul.add(scroll, BorderLayout.CENTER);

        painel.add(sul, BorderLayout.SOUTH);

        add(painel);


        btnCadastrar.addActionListener(e -> {

            try {

                boolean sucesso = disciplinaController.cadastrarDisciplina(
                        txtCodigo.getText(),
                        txtNome.getText(),
                        Integer.parseInt(txtCargaHoraria.getText())
                );

                if(sucesso){

                    JOptionPane.showMessageDialog(this,
                            "Disciplina cadastrada com sucesso!");

                    limparCampos();

                }else{

                    JOptionPane.showMessageDialog(this,
                            "Erro ao cadastrar.");

                }

            }catch(NumberFormatException ex){

                JOptionPane.showMessageDialog(this,
                        "Carga Horária deve ser um número!");

            }

        });

        btnBuscar.addActionListener(e->{

            var disciplina = disciplinaController.buscarDisciplinaPorCodigo(
                    txtCodigo.getText());

            if(disciplina == null){

                JOptionPane.showMessageDialog(this,
                        "Disciplina não encontrada.");

                return;

            }

            txtNome.setText(disciplina.getNome());
            txtCargaHoraria.setText(String.valueOf(disciplina.getCargaHoraria()));

        });


        btnAtualizar.addActionListener(e->{

            try{

                boolean sucesso = disciplinaController.atualizarDisciplina(
                        txtCodigo.getText(),
                        txtNome.getText(),
                        Integer.parseInt(txtCargaHoraria.getText())
                );

                if(sucesso){

                    JOptionPane.showMessageDialog(this,
                            "Disciplina atualizada.");

                }else{

                    JOptionPane.showMessageDialog(this,
                            "Não foi possível atualizar.");

                }

            }catch(NumberFormatException ex){

                JOptionPane.showMessageDialog(this,
                        "Carga Horária inválida.");

            }

        });


        btnExcluir.addActionListener(e->{

            boolean sucesso = disciplinaController.removerDisciplina(
                    txtCodigo.getText());

            if(sucesso){

                JOptionPane.showMessageDialog(this,
                        "Disciplina removida.");

                limparCampos();

            }else{

                JOptionPane.showMessageDialog(this,
                        "Disciplina não encontrada.");

            }

        });

        btnListar.addActionListener(e->{

            txtResultado.setText("");

            for(var disciplina : disciplinaController.listarDisciplinas()){

                txtResultado.append(
                        disciplina.getCodigo() + " - "
                                + disciplina.getNome() + " - "
                                + disciplina.getCargaHoraria() + " horas\n"
                );

            }

        });


        btnVoltar.addActionListener(e->{

            new TelaPrincipal().setVisible(true);
            dispose();

        });

    }

    private void limparCampos(){

        txtCodigo.setText("");
        txtNome.setText("");
        txtCargaHoraria.setText("");

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaDisciplina().setVisible(true);
        });
    }

}
