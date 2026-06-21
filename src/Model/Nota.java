package Model;

import java.util.ArrayList;

public class Nota {
    private String matriculaAluno;
    private String codigoDisciplina;
    private ArrayList<Double> notas;

    public Nota() {
    }

    public Nota(String matriculaAluno, String codigoDisciplina) {
        this.matriculaAluno = matriculaAluno;
        this.codigoDisciplina = codigoDisciplina;
        this.notas = new ArrayList<>();
    }

    public Nota(String matriculaAluno, String codigoDisciplina, ArrayList<Double> notas) {
        this.matriculaAluno = matriculaAluno;
        this.codigoDisciplina = codigoDisciplina;
        this.notas = notas;
    }

    public String getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(String matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public void setCodigoDisciplina(String codigoDisciplina) {
        this.codigoDisciplina = codigoDisciplina;
    }

    public ArrayList<Double> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Double> notas) {
        this.notas = notas;
    }

    public void adicionarNota(double nota) {
        notas.add(nota);
    }

    public double calcularMedia() {
        if (notas.isEmpty()) {
            return 0.0;
        }

        double soma = 0.0;

        for (double nota : notas) {
            soma += nota;
        }

        return soma / notas.size();
    }

    public String getSituacao() {
        double media = calcularMedia();

        if (media >= 6.0) {
            return "Aprovado";
        } else {
            return "Reprovado";
        }
    }

    @Override
    public String toString() {
        return matriculaAluno + ";" + codigoDisciplina + ";" + notas;
    }
}
