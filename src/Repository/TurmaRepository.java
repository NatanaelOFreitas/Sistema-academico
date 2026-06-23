package Repository;

import Model.Aluno;
import Model.Turma;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TurmaRepository {
    private ArrayList<Turma> turmas;
    public String caminhoTurma = "src/data/turmas.csv";

    public TurmaRepository() {
        this.turmas = new ArrayList<>();
    }

    public boolean adicionar(Turma turma) {
        if(buscarPorCodigo(turma.getCodigo()) != null) {
            return false;
        }
        turmas.add(turma);
        salvarArquivo();
        return true;
    }

    public ArrayList<Turma> listar() {
        carregarArquivo();
        return turmas;
    }

    public Turma buscarPorCodigo(String codigo) {
        for(int i=0;i<turmas.size();i++) {
            Turma turma = turmas.get(i);
            if(turma.getCodigo().equals(codigo)) {
                return turma;
            }
        }
        return null;
    }

    public boolean remover(String codigo) {
        Turma turma = buscarPorCodigo(codigo);
        if(turma!=null) {
            turmas.remove(turma);
            salvarArquivo();
            return true;
        }
        return false;
    }

    public boolean atualizar(Turma turmaAtualizada) {
        Turma turma = buscarPorCodigo(turmaAtualizada.getCodigo());

        if(turma != null) {
            turma.setDisciplina(turmaAtualizada.getDisciplina());
            turma.setTurma(turmaAtualizada.getTurma());
            turma.setQuantidadeAlunos(turmaAtualizada.getQuantidadeAlunos());
            salvarArquivo();
            return true;
        }

        return false;
    }

    public void salvarArquivo() {

        try {

            BufferedWriter bw =
                new BufferedWriter(
                    new FileWriter(caminhoTurma));

            for (int i = 0; i < turmas.size(); i++) {

                bw.write(turmas.get(i).toString());
                bw.newLine();

            }

            bw.close();

        } catch (IOException e) {

            System.out.println("Erro ao salvar arquivo");

        }
    }

    public void carregarArquivo() {
        turmas.clear();

        try {

            BufferedReader br =
                new BufferedReader(
                    new FileReader(caminhoTurma));

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] dados = linha.split(";");
                ArrayList<Aluno> turma = new ArrayList<>();
                String alunos = "";
                for(int i = 5; i < dados.length;i++){
                    alunos += dados[i];
                    if(i < dados.length - 1) {
                        alunos += ";";
                    }
                }

                alunos = alunos.replace("[", "").replace("]", "").trim();

                if(!alunos.isEmpty()) {
                    String[] listaAlunos = alunos.split(";");
                    for(int i = 0;i + 3 < listaAlunos.length;i += 4){
                        Aluno aluno = new Aluno(listaAlunos[i + 1], listaAlunos[i], listaAlunos[i + 2], Integer.parseInt(listaAlunos[i + 3]));
                        turma.add(aluno);
                    }
                }

                Turma turmaCarregada = new Turma(dados[0], new Model.Disciplina(dados[1], dados[2], Integer.parseInt(dados[3])));
                turmaCarregada.setQuantidadeAlunos(Integer.parseInt(dados[4]));
                turmaCarregada.setTurma(turma);
                turmas.add(turmaCarregada);
            }

            br.close();

        } catch (IOException e) {

            System.out.println("Erro ao carregar arquivo");

        }   
    }
}
