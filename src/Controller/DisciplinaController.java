package Controller;

import Model.Disciplina;
import Repository.DisciplinaRepository;
import java.util.ArrayList;

public class DisciplinaController {

    private DisciplinaRepository repository;

    public DisciplinaController(DisciplinaRepository repository) {
        this.repository = repository;
    }

    public boolean cadastrarDisciplina(
            String codigo,
            String nome,
            int cargaHoraria) {

        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }

        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }

        if (cargaHoraria <= 0) {
            return false;
        }

        Disciplina disciplina = new Disciplina(
                codigo,
                nome,
                cargaHoraria
        );

        return repository.adicionar(disciplina);
    }

    public ArrayList<Disciplina> listarDisciplinas() {
        return repository.listar();
    }

    public Disciplina buscarDisciplinaPorCodigo(String codigo) {

        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }

        return repository.buscarPorCodigo(codigo);
    }

    public ArrayList<Disciplina> buscarDisciplinaPorNome(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return repository.buscarPorNome(nome);
    }

    public boolean removerDisciplina(String codigo) {

        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }

        return repository.remover(codigo);
    }

    public boolean atualizarDisciplina(
            String codigo,
            String nome,
            int cargaHoraria) {

        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }

        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }

        if (cargaHoraria <= 0) {
            return false;
        }

        Disciplina disciplina = new Disciplina(
                codigo,
                nome,
                cargaHoraria
        );

        return repository.atualizar(disciplina);
    }

    public void salvarDados(String caminho) {
        repository.salvarArquivo(caminho);
    }

    public void carregarDados(String caminho) {
        repository.carregarArquivo(caminho);
    }
}