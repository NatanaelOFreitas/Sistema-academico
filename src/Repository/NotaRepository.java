package Repository;

import Model.Nota;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NotaRepository {
    private ArrayList<Nota> notas;
    public String caminhoNota = "src/data/notas.csv";

    public NotaRepository() {
        this.notas = new ArrayList<>();
    }

    public boolean adicionar(Nota nota) {
        if(buscar(nota.getMatriculaAluno(),nota.getCodigoDisciplina()) != null) {
            return false;
        }
        notas.add(nota);
        salvarArquivo();
        return true;
    }

    public ArrayList<Nota> listar() {
        carregarArquivo();
        return notas;
    }

    public Nota buscar(String matriculaAluno,String codigoDisciplina) {
        for(int i=0;i<notas.size();i++) {
            Nota nota = notas.get(i);
            if(nota.getMatriculaAluno().equals(matriculaAluno) && nota.getCodigoDisciplina().equals(codigoDisciplina)) {
                return nota;
            }
        }
        return null;
    }

    public ArrayList<Nota> buscarPorAluno(String matriculaAluno) {
        ArrayList<Nota> encontradas = new ArrayList<>();
        for(int i=0;i<notas.size();i++) {
            Nota nota = notas.get(i);
            if(nota.getMatriculaAluno().equals(matriculaAluno)) {
                encontradas.add(nota);
            }
        }
        return encontradas;
    }

    public ArrayList<Nota> buscarPorDisciplina(String codigoDisciplina) {
        ArrayList<Nota> encontradas = new ArrayList<>();
        for(int i=0;i<notas.size();i++) {
            Nota nota = notas.get(i);
            if(nota.getCodigoDisciplina().equals(codigoDisciplina)) {
                encontradas.add(nota);
            }
        }
        return encontradas;
    }

    public boolean remover(String matriculaAluno,String codigoDisciplina) {
        Nota nota = buscar(matriculaAluno,codigoDisciplina);
        if(nota != null) {
            notas.remove(nota);
            salvarArquivo();
            return true;
        }
        return false;
    }

    public boolean atualizar(Nota notaAtualizada) {
        Nota nota = buscar(notaAtualizada.getMatriculaAluno(),notaAtualizada.getCodigoDisciplina());
        if(nota != null) {
            nota.setNotas(notaAtualizada.getNotas());
            salvarArquivo();
            return true;
        }
        return false;
    }

    public void salvarArquivo() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoNota));
            for(int i=0;i<notas.size();i++) {
                bw.write(notas.get(i).toString());
                bw.newLine();
            }
            bw.close();
        }
        catch(IOException e) {
            System.out.println("Erro ao salvar arquivo");
        }
    }

    public void carregarArquivo() {
        notas.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(caminhoNota));
            String linha;
            while((linha = br.readLine()) != null) {
                if(linha.trim().isEmpty()) {
                    continue;
                }

                String[] dados = linha.split(";");
                if(dados.length < 2) {
                    continue;
                }

                ArrayList<Double> listaNotas = new ArrayList<>();

                for(int i=2;i<dados.length;i++) {
                    adicionarNotasDoCampo(listaNotas,dados[i]);
                }

                Nota nota = new Nota(dados[0],dados[1],listaNotas);
                notas.add(nota);
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println("Erro ao carregar arquivo");
        }
    }

    private void adicionarNotasDoCampo(ArrayList<Double> listaNotas,String campo) {
        String valor = campo.replace("[","").replace("]","").trim();

        if(valor.isEmpty()) {
            return;
        }

        String[] partes = valor.split(",");

        for(int i=0;i<partes.length;i++) {
            String nota = partes[i].trim();

            if(!nota.isEmpty()) {
                try {
                    listaNotas.add(Double.parseDouble(nota));
                }
                catch(NumberFormatException e) {
                    System.out.println("Nota invalida ignorada: " + nota);
                }
            }
        }
    }
}
