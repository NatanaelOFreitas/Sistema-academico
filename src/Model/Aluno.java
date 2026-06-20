package Model;

import java.util.ArrayList;

public class Aluno {
    private String nome;
    private String matricula;
    private String curso;
    private int periodo;

    public Aluno(){

    }

    public Aluno(String nome, String matricula, String curso, int periodo){
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.periodo = periodo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return matricula + ";" + nome + ";" + curso + ";" + periodo;
    }
}
