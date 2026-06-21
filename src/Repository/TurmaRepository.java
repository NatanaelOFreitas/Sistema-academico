package Repository;

import Model.Turma;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TurmaRepository {
    private ArrayList<Turma> turmas;

    public TurmaRepository() {
        this.turmas = new ArrayList<>();
    }

    public boolean adicionar(Turma turma) {
        if(buscarPorCodigo(turma.getCodigo()) != null) {
            return false;
        }
        turmas.add(turma);
        return true;
    }

    public ArrayList<Turma> listar() {
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
            return true;
        }
        return false;
    }

    public boolean atualizar(Turma turmaAtualizada) {
        Turma turma = buscarPorCodigo(turmaAtualizada.getCodigo());

        if(turma != null) {
            turma.setDisciplina(turmaAtualizada.getDisciplina());
            turma.setTurma(turmaAtualizada.getTurma());
            return true;
        }

        return false;
    }

    public void salvarArquivo(String caminho) {

        try {

            BufferedWriter bw =
                new BufferedWriter(
                    new FileWriter(caminho));

            for (int i = 0; i < turmas.size(); i++) {

                bw.write(turmas.get(i).toString());
                bw.newLine();

            }

            bw.close();

        } catch (IOException e) {

            System.out.println("Erro ao salvar arquivo");

        }
    }

    public void carregarArquivo(String caminho) {

        try {

            BufferedReader br =
                new BufferedReader(
                    new FileReader(caminho));

            String linha;

            while ((linha = br.readLine()) != null) {

                System.out.println(linha);

            }

            br.close();

        } catch (IOException e) {

            System.out.println("Erro ao carregar arquivo");

        }   
    }
}