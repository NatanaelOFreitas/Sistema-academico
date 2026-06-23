package View;

import Controller.NotaController;
import Repository.NotaRepository;
import Model.Nota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TelaNota extends JFrame {

    private JTextField txtMatricula;
    private JTextField txtCodigoDisciplina;
    private JTextField txtNota1;
    private JTextField txtNota2;
    private JTextField txtNota3;

    private JButton btnCadastrar;
    private JButton btnBuscar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnListar;
    private JButton btnVoltar;

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    private NotaController notaController;

    public TelaNota() {

        setTitle("Lançamento de Notas");
        setSize(700,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        notaController = new NotaController(new NotaRepository());

        JPanel painel = new JPanel(new BorderLayout(10,10));
        painel.setBackground(new Color(230,240,255));

        JLabel titulo = new JLabel("Lançamento de Notas",SwingConstants.CENTER);
        titulo.setFont(new Font("Arial",Font.BOLD,24));
        painel.add(titulo,BorderLayout.NORTH);


        JPanel formulario = new JPanel(new GridLayout(5,2,15,15));
        formulario.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        formulario.setBackground(new Color(230,240,255));

        formulario.add(new JLabel("Matrícula do Aluno:"));
        txtMatricula = new JTextField();
        formulario.add(txtMatricula);

        formulario.add(new JLabel("Código da Disciplina:"));
        txtCodigoDisciplina = new JTextField();
        formulario.add(txtCodigoDisciplina);

        formulario.add(new JLabel("Nota 1:"));
        txtNota1 = new JTextField();
        formulario.add(txtNota1);

        formulario.add(new JLabel("Nota 2:"));
        txtNota2 = new JTextField();
        formulario.add(txtNota2);

        formulario.add(new JLabel("Nota 3:"));
        txtNota3 = new JTextField();
        formulario.add(txtNota3);

        painel.add(formulario,BorderLayout.CENTER);


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

        sul.add(botoes,BorderLayout.NORTH);

        modeloTabela = new DefaultTableModel(
                new String[]{
                        "Matrícula",
                        "Disciplina",
                        "Média",
                        "Situação"
                },0);

        tabela = new JTable(modeloTabela);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setPreferredSize(new Dimension(600,180));

        sul.add(scroll,BorderLayout.CENTER);

        painel.add(sul,BorderLayout.SOUTH);

        add(painel);


        btnCadastrar.addActionListener(e->{

            boolean sucesso = notaController.cadastrarNota(
                    txtMatricula.getText(),
                    txtCodigoDisciplina.getText());

            if(sucesso){

                JOptionPane.showMessageDialog(this,
                        "Registro criado.\nAgora clique em Atualizar para lançar as notas.");

            }else{

                JOptionPane.showMessageDialog(this,
                        "Erro ao cadastrar.");

            }

        });


        btnAtualizar.addActionListener(e->{

            try{

                ArrayList<Double> notas = new ArrayList<>();

                if(!txtNota1.getText().isBlank())
                    notas.add(Double.parseDouble(txtNota1.getText()));

                if(!txtNota2.getText().isBlank())
                    notas.add(Double.parseDouble(txtNota2.getText()));

                if(!txtNota3.getText().isBlank())
                    notas.add(Double.parseDouble(txtNota3.getText()));

                boolean sucesso = notaController.atualizarNota(

                        txtMatricula.getText(),
                        txtCodigoDisciplina.getText(),
                        notas

                );

                if(sucesso){

                    JOptionPane.showMessageDialog(this,
                            "Notas atualizadas.");

                    atualizarTabela();

                }else{

                    JOptionPane.showMessageDialog(this,
                            "Erro ao atualizar.");

                }

            }catch(NumberFormatException ex){

                JOptionPane.showMessageDialog(this,
                        "Digite apenas números nas notas.");

            }

        });

        btnBuscar.addActionListener(e->{

            Nota nota = notaController.buscarNota(
                    txtMatricula.getText(),
                    txtCodigoDisciplina.getText());

            if(nota==null){

                JOptionPane.showMessageDialog(this,
                        "Registro não encontrado.");

                return;

            }

            ArrayList<Double> notas = nota.getNotas();

            txtNota1.setText("");
            txtNota2.setText("");
            txtNota3.setText("");

            if(notas.size()>0)
                txtNota1.setText(String.valueOf(notas.get(0)));

            if(notas.size()>1)
                txtNota2.setText(String.valueOf(notas.get(1)));

            if(notas.size()>2)
                txtNota3.setText(String.valueOf(notas.get(2)));

        });

        btnExcluir.addActionListener(e->{

            boolean sucesso = notaController.removerNota(
                    txtMatricula.getText(),
                    txtCodigoDisciplina.getText());

            if(sucesso){

                JOptionPane.showMessageDialog(this,
                        "Registro removido.");

                limparCampos();

                atualizarTabela();

            }else{

                JOptionPane.showMessageDialog(this,
                        "Registro não encontrado.");

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

        for(Nota nota : notaController.listarNotas()){

            modeloTabela.addRow(new Object[]{

                    nota.getMatriculaAluno(),
                    nota.getCodigoDisciplina(),
                    nota.calcularMedia(),
                    nota.getSituacao()

            });

        }

    }

    private void limparCampos(){

        txtMatricula.setText("");
        txtCodigoDisciplina.setText("");
        txtNota1.setText("");
        txtNota2.setText("");
        txtNota3.setText("");

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new TelaNota().setVisible(true));
    }
}