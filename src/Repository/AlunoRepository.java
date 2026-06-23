package Repository;

import Model.Aluno;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AlunoRepository {
    private ArrayList<Aluno> alunos;
    public String caminhoAluno = "src/data/alunos.csv";

    public AlunoRepository() {
        this.alunos = new ArrayList<>();
    }

    public boolean adicionar(Aluno aluno) {
        if(buscarPorMatricula(aluno.getMatricula())!=null) {
            return false;
        }
        alunos.add(aluno);
        salvarArquivo();
        return true;
    }

    public ArrayList<Aluno> listar() {
        carregarArquivo();
        return alunos;
    }

    public ArrayList<Aluno> buscarPorNome(String nome) {
        ArrayList<Aluno> encontrados = new ArrayList<>();
        for(int i=0;i<alunos.size();i++) {
            Aluno aluno = alunos.get(i);
            if(aluno.getNome().toLowerCase().contains(nome.toLowerCase())) {
                encontrados.add(aluno);
            }
        }
        return encontrados;
    }

    public Aluno buscarPorMatricula(String matricula) {
        for(int i=0;i<alunos.size();i++) {
            Aluno aluno = alunos.get(i);
            if(aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }

    public boolean remover(String matricula) {
        Aluno aluno = buscarPorMatricula(matricula);
        if(aluno!=null) {
            alunos.remove(aluno);
            salvarArquivo();
            return true;
        }
        return false;
    }

    public boolean atualizar(Aluno alunoAtualizado) {
        Aluno aluno = buscarPorMatricula(alunoAtualizado.getMatricula());
        if(aluno!=null) {
            aluno.setNome(alunoAtualizado.getNome());
            aluno.setCurso(alunoAtualizado.getCurso());
            aluno.setPeriodo(alunoAtualizado.getPeriodo());
            salvarArquivo();
            return true;
        }
        return false;
    }

    public void salvarArquivo() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoAluno));
            for(int i=0;i<alunos.size();i++) {
                bw.write(alunos.get(i).toString());
                bw.newLine();
            }
            bw.close();
        }
        catch(IOException e) {
            System.out.println("Erro ao salvar arquivo");
        }
    }

    public void carregarArquivo() {
        alunos.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(caminhoAluno));
            String linha;
            while((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Aluno aluno = new Aluno(dados[1],dados[0],dados[2],Integer.parseInt(dados[3]));
                alunos.add(aluno);
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println("Erro ao carregar arquivo");
        }
    }
}
