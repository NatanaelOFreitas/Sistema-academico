package Model;

import java.util.ArrayList;

public class Turma {
    private String codigo;
    private Disciplina disciplina;
    private ArrayList<Aluno> turma;

    public Turma(String codigo, Disciplina disciplina) {
        this.codigo = codigo;
        this.disciplina = disciplina;
        this.turma = new ArrayList<>();
    }


    //getters

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setTurma(ArrayList<Aluno> turma) {
        this.turma = turma;
    }

    public ArrayList<Aluno> getTurma() {
        return turma;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public void setAlunos(ArrayList<Aluno> turma) {
        this.turma = turma;
        ordenarAlunosPorNome();
    }

    public void adicionarAluno(Aluno aluno) {
        turma.add(aluno);
        ordenarAlunosPorNome();
    }

    public void removerAlunoPorNome(String nome) {
        Aluno aluno = buscarAlunoPorNome(nome);

        if (aluno != null) {
            turma.remove(aluno);
        }
    }

    public Aluno buscarAlunoPorNome(String nome) {
        for (Aluno aluno : turma) {
            if (aluno.getNome().equalsIgnoreCase(nome)) {
                return aluno;
            }
        }

        return null;
    }

    public ArrayList<Aluno> buscarAlunosPorParteDoNome(String nome) {
        ArrayList<Aluno> encontrados = new ArrayList<>();

        for (Aluno aluno : turma) {
            if (aluno.getNome().toLowerCase().contains(nome.toLowerCase())) {
                encontrados.add(aluno);
            }
        }

        return encontrados;
    }

    private void ordenarAlunosPorNome() {
        for (int i = 0; i < turma.size() - 1; i++) {
            for (int j = 0; j < turma.size() - 1 - i; j++) {
                Aluno alunoAtual = turma.get(j);
                Aluno proximoAluno = turma.get(j + 1);

                if (alunoAtual.getNome().compareToIgnoreCase(proximoAluno.getNome()) > 0) {
                    turma.set(j, proximoAluno);
                    turma.set(j + 1, alunoAtual);
                }
            }
        }
    }

    @Override
    public String toString() {
        return codigo + ";" + disciplina.getNome() + ";" + getTurma();
    }
}
