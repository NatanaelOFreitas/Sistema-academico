package Repository;

import Model.Disciplina;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DisciplinaRepository {
    private ArrayList<Disciplina> disciplinas;
    public String caminhoDisciplina = "src/data/disciplinas.csv";

    public DisciplinaRepository() {
        this.disciplinas = new ArrayList<>();
    }

    public boolean adicionar(Disciplina disciplina) {
        if(buscarPorCodigo(disciplina.getCodigo()) != null) {
            return false;
        }
        disciplinas.add(disciplina);
        salvarArquivo(caminhoDisciplina);
        return true;
    }

    public ArrayList<Disciplina> listar() {
        return disciplinas;
    }

    public Disciplina buscarPorCodigo(String codigo) {
        for(int i=0;i<disciplinas.size();i++) {
            Disciplina disciplina = disciplinas.get(i);
            if(disciplina.getCodigo().equals(codigo)) {
                return disciplina;
            }
        }
        return null;
    }

    public ArrayList<Disciplina> buscarPorNome(String nome) {
        ArrayList<Disciplina> encontradas = new ArrayList<>();
        for(int i=0;i<disciplinas.size();i++) {
            Disciplina disciplina = disciplinas.get(i);
            if(disciplina.getNome().toLowerCase().contains(nome.toLowerCase())) {
                encontradas.add(disciplina);
            }
        }
        return encontradas;
    }

    public boolean remover(String codigo) {
        Disciplina disciplina = buscarPorCodigo(codigo);
        if(disciplina!=null) {
            disciplinas.remove(disciplina);
            salvarArquivo(caminhoDisciplina);
            return true;
        }
        return false;
    }

    public boolean atualizar(Disciplina disciplinaAtualizada) {
        Disciplina disciplina = buscarPorCodigo(disciplinaAtualizada.getCodigo());
        if(disciplina!=null) {
            disciplina.setNome(disciplinaAtualizada.getNome());
            disciplina.setCargaHoraria(disciplinaAtualizada.getCargaHoraria());
            salvarArquivo(caminhoDisciplina);
            return true;
        }
        return false;
    }

    public void salvarArquivo(String caminho) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));
            for(int i=0;i<disciplinas.size();i++) {
                bw.write(disciplinas.get(i).toString());
                bw.newLine();
            }
            bw.close();
        }
        catch(IOException e) {
            System.out.println("Erro ao salvar arquivo");
        }
    }

    public void carregarArquivo(String caminho) {
        disciplinas.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(caminho));
            String linha;
            while((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Disciplina disciplina = new Disciplina(dados[0],dados[1],Integer.parseInt(dados[2]));
                disciplinas.add(disciplina);
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println("Erro ao carregar arquivo");
        }
    }
}
