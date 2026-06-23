package Controller;

import Model.Aluno;
import Repository.AlunoRepository;
import java.util.ArrayList;

    public class AlunoController {
        private AlunoRepository repository;

        public AlunoController(AlunoRepository repository) {
            this.repository = repository;
        }
    
        public boolean cadastrarAluno(
            String nome,
            String matricula,
            String curso,
            int periodo) {

            if (matricula == null || matricula.trim().isEmpty()) {
                return false;
            }
            if (nome == null || nome.trim().isEmpty()) {
                return false;
            }

            if (periodo <= 0) {
                return false;
            }

            Aluno aluno = new Aluno(
                nome,
                matricula,
                curso,
                periodo
            );

            return repository.adicionar(aluno);
        }

        public ArrayList<Aluno> listarAlunos() {
            return repository.listar();
        }

        public Aluno buscarAlunoPorMatricula(String matricula) {

            if (matricula == null || matricula.trim().isEmpty()) {
                return null;
            }

            return repository.buscarPorMatricula(matricula);
        }

        public ArrayList<Aluno> buscarAlunoPorNome(String nome) {

            if (nome == null || nome.trim().isEmpty()) {
                return new ArrayList<>();
            }

            return repository.buscarPorNome(nome);
        }   

        public boolean removerAluno(String matricula) {

            if (matricula == null || matricula.trim().isEmpty()) {
                return false;
            }

            return repository.remover(matricula);
        }

        public boolean atualizarAluno(
            String nome,
            String matricula,
            String curso,
            int periodo) {

            if (matricula == null || matricula.trim().isEmpty()) {
                return false;
            }

            if (nome == null || nome.trim().isEmpty()) {
                return false;
            }

            if (periodo <= 0) {
                return false;
            }

            Aluno aluno = new Aluno(
                nome,
                matricula,
                curso,
                periodo
            );

            return repository.atualizar(aluno);
        }

        public void carregarArquivo(){
            repository.carregarArquivo();
        }
    }