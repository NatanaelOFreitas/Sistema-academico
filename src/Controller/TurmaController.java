package Controller;

import Model.Turma;
import Model.Aluno;
import Model.Disciplina;
import Repository.TurmaRepository;
import java.util.ArrayList;

public class TurmaController {

    private TurmaRepository repository;

    public TurmaController(TurmaRepository repository) {
        this.repository = repository;
    }

    public boolean cadastrarTurma(
            String codigo,
            Disciplina disciplina) {

        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }

        if (disciplina == null) {
            return false;
        }

        Turma turma = new Turma(
                codigo,
                disciplina
        );

        return repository.adicionar(turma);
    }

    public ArrayList<Turma> listarTurmas() {
        return repository.listar();
    }

    public Turma buscarTurmaPorCodigo(String codigo) {

        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }

        return repository.buscarPorCodigo(codigo);
    }

    public boolean removerTurma(String codigo) {

        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }

        return repository.remover(codigo);
    }

    public boolean atualizarTurma(
            String codigo,
            Disciplina disciplina,
            ArrayList<Aluno> alunos) {

        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }

        if (disciplina == null) {
            return false;
        }

        Turma turma = new Turma(
                codigo,
                disciplina
        );

        turma.setTurma(alunos);

        return repository.atualizar(turma);
    }

    public boolean adicionarAlunoNaTurma(
            String codigoTurma,
            Aluno aluno) {

        Turma turma = repository.buscarPorCodigo(codigoTurma);

        if (turma == null || aluno == null) {
            return false;
        }

        turma.adicionarAluno(aluno);

        return true;
    }

    public boolean removerAlunoDaTurma(
            String codigoTurma,
            String nomeAluno) {

        Turma turma = repository.buscarPorCodigo(codigoTurma);

        if (turma == null) {
            return false;
        }

        turma.removerAlunoPorNome(nomeAluno);

        return true;
    }

    public Aluno buscarAlunoNaTurma(
            String codigoTurma,
            String nomeAluno) {

        Turma turma = repository.buscarPorCodigo(codigoTurma);

        if (turma == null) {
            return null;
        }

        return turma.buscarAlunoPorNome(nomeAluno);
    }

    public ArrayList<Aluno> buscarAlunosPorParteDoNome(
            String codigoTurma,
            String nome) {

        Turma turma = repository.buscarPorCodigo(codigoTurma);

        if (turma == null) {
            return new ArrayList<>();
        }

        return turma.buscarAlunosPorParteDoNome(nome);
    }

    public void salvarDados(String caminho) {
        repository.salvarArquivo(caminho);
    }

    public void carregarDados(String caminho) {
        repository.carregarArquivo(caminho);
    }
}