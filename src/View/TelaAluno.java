package View;

import Controller.AlunoController;
import Repository.AlunoRepository;

import javax.swing.*;
import java.awt.*;

public class TelaAluno extends JFrame {

    private JTextField txtMatricula;
    private JTextField txtNome;
    private JTextField txtCurso;
    private JTextField txtPeriodo;

    private JButton btnCadastrar;
    private JButton btnBuscar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnListar;
    private JButton btnVoltar;

    private JTextArea txtResultado;

    private AlunoController alunoController;

    public TelaAluno() {

        setTitle("Gerenciar Alunos");
        setSize(600, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        alunoController = new AlunoController(new AlunoRepository());

        JPanel painel = new JPanel(new BorderLayout(10,10));
        painel.setBackground(new Color(230,240,255));

        JLabel titulo = new JLabel("Gerenciar Alunos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        painel.add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(4,2,10,10));
        formulario.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        formulario.setBackground(new Color(230,240,255));

        formulario.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        formulario.add(txtMatricula);

        formulario.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        formulario.add(txtNome);

        formulario.add(new JLabel("Curso:"));
        txtCurso = new JTextField();
        formulario.add(txtCurso);

        formulario.add(new JLabel("Período:"));
        txtPeriodo = new JTextField();
        formulario.add(txtPeriodo);

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

        txtResultado = new JTextArea(10,40);
        txtResultado.setEditable(false);

        JScrollPane scroll = new JScrollPane(txtResultado);

        sul.add(scroll, BorderLayout.CENTER);

        painel.add(sul, BorderLayout.SOUTH);

        add(painel);


        btnCadastrar.addActionListener(e -> {

            try{

                boolean sucesso = alunoController.cadastrarAluno(

                        txtNome.getText(),
                        txtMatricula.getText(),
                        txtCurso.getText(),
                        Integer.parseInt(txtPeriodo.getText())

                );

                if(sucesso){

                    JOptionPane.showMessageDialog(this,
                            "Aluno cadastrado!");

                    limparCampos();

                }else{

                    JOptionPane.showMessageDialog(this,
                            "Erro ao cadastrar!");

                }

            }catch(NumberFormatException ex){

                JOptionPane.showMessageDialog(this,
                        "Período inválido.");

            }

        });


        btnListar.addActionListener(e -> {

            txtResultado.setText("");

            for(var aluno : alunoController.listarAlunos()){

                txtResultado.append(
                        aluno.getMatricula()+" - "
                                + aluno.getNome()+" - "
                                + aluno.getCurso()+" - "
                                + aluno.getPeriodo()+"\n"
                );

            }

        });


        btnBuscar.addActionListener(e -> {

            var aluno = alunoController.buscarAlunoPorMatricula(
                    txtMatricula.getText());

            if(aluno == null){

                JOptionPane.showMessageDialog(this,
                        "Aluno não encontrado.");

                return;

            }

            txtNome.setText(aluno.getNome());
            txtCurso.setText(aluno.getCurso());
            txtPeriodo.setText(String.valueOf(aluno.getPeriodo()));

        });


        btnAtualizar.addActionListener(e -> {

            try{

                boolean sucesso = alunoController.atualizarAluno(

                        txtNome.getText(),
                        txtMatricula.getText(),
                        txtCurso.getText(),
                        Integer.parseInt(txtPeriodo.getText())

                );

                JOptionPane.showMessageDialog(this,

                        sucesso ?

                                "Aluno atualizado!"

                                :

                                "Não foi possível atualizar."

                );

            }catch(NumberFormatException ex){

                JOptionPane.showMessageDialog(this,
                        "Período inválido.");

            }

        });


        btnExcluir.addActionListener(e -> {

            boolean sucesso = alunoController.removerAluno(
                    txtMatricula.getText());

            if(sucesso){

                JOptionPane.showMessageDialog(this,
                        "Aluno removido.");

                limparCampos();

            }else{

                JOptionPane.showMessageDialog(this,
                        "Aluno não encontrado.");

            }

        });


        btnVoltar.addActionListener(e -> {

            new TelaPrincipal().setVisible(true);

            dispose();

        });

    }

    private void limparCampos(){

        txtMatricula.setText("");
        txtNome.setText("");
        txtCurso.setText("");
        txtPeriodo.setText("");

    }

}
