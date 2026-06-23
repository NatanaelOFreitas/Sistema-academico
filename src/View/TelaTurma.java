package View;

import Controller.TurmaController;
import Repository.TurmaRepository;
import Model.Turma;
import Model.Disciplina;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TelaTurma extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtCodigoDisciplina;

    private JButton btnCadastrar;
    private JButton btnBuscar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnListar;
    private JButton btnVoltar;

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    private TurmaController turmaController;

    public TelaTurma() {

        setTitle("Cadastro de Turmas");
        setSize(650, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        turmaController = new TurmaController(new TurmaRepository());

        JPanel painel = new JPanel(new BorderLayout(10,10));
        painel.setBackground(new Color(230,240,255));

        JLabel titulo = new JLabel("Cadastro de Turmas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD,24));

        painel.add(titulo, BorderLayout.NORTH);


        JPanel formulario = new JPanel(new GridLayout(2,2,15,15));
        formulario.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        formulario.setBackground(new Color(230,240,255));

        formulario.add(new JLabel("Código da Turma:"));
        txtCodigo = new JTextField();
        formulario.add(txtCodigo);

        formulario.add(new JLabel("Código da Disciplina:"));
        txtCodigoDisciplina = new JTextField();
        formulario.add(txtCodigoDisciplina);

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

        modeloTabela = new DefaultTableModel(
                new String[]{"Código", "Disciplina", "Qtd. Alunos"},0);

        tabela = new JTable(modeloTabela);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setPreferredSize(new Dimension(550,180));

        sul.add(scroll,BorderLayout.CENTER);

        painel.add(sul,BorderLayout.SOUTH);

        add(painel);


        btnCadastrar.addActionListener(e->{

            Disciplina disciplina = new Disciplina(
                    txtCodigoDisciplina.getText(),
                    "",
                    0
            );

            boolean sucesso = turmaController.cadastrarTurma(
                    txtCodigo.getText(),
                    disciplina
            );

            if(sucesso){

                JOptionPane.showMessageDialog(this,
                        "Turma cadastrada.");

                limparCampos();

                atualizarTabela();

            }else{

                JOptionPane.showMessageDialog(this,
                        "Erro ao cadastrar.");

            }

        });


        btnBuscar.addActionListener(e->{

            Turma turma = turmaController.buscarTurmaPorCodigo(
                    txtCodigo.getText());

            if(turma == null){

                JOptionPane.showMessageDialog(this,
                        "Turma não encontrada.");

                return;

            }

            txtCodigoDisciplina.setText(
                    turma.getDisciplina().getCodigo()
            );

        });

        btnAtualizar.addActionListener(e->{

            Disciplina disciplina = new Disciplina(
                    txtCodigoDisciplina.getText(),
                    "",
                    0
            );

            boolean sucesso = turmaController.atualizarTurma(
                    txtCodigo.getText(),
                    disciplina,
                    new ArrayList<>()
            );

            if(sucesso){

                JOptionPane.showMessageDialog(this,
                        "Turma atualizada.");

                atualizarTabela();

            }else{

                JOptionPane.showMessageDialog(this,
                        "Erro ao atualizar.");

            }

        });

        btnExcluir.addActionListener(e->{

            boolean sucesso = turmaController.removerTurma(
                    txtCodigo.getText());

            if(sucesso){

                JOptionPane.showMessageDialog(this,
                        "Turma removida.");

                limparCampos();

                atualizarTabela();

            }else{

                JOptionPane.showMessageDialog(this,
                        "Turma não encontrada.");

            }

        });

        btnListar.addActionListener(e->atualizarTabela());

        btnVoltar.addActionListener(e->{

            new TelaPrincipal().setVisible(true);
            dispose();

        });

    }

    private void atualizarTabela(){

        modeloTabela.setRowCount(0);

        for(Turma turma : turmaController.listarTurmas()){

            modeloTabela.addRow(new Object[]{
                    turma.getCodigo(),
                    turma.getDisciplina().getCodigo(),
                    turma.getTurma().size()
            });

        }

    }

    private void limparCampos(){

        txtCodigo.setText("");
        txtCodigoDisciplina.setText("");

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new TelaTurma().setVisible(true));
    }
}