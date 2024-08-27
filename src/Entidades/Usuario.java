package Entidades;

public class Usuario {
    private String id;
    private String nome;
    private String idade;

    public Usuario(String idade, String nome) {
        this.idade = idade;
        this.nome = nome;
    }
    public String getNome(){return nome;}
    public String getIdade(){return idade;}
    public String getId(){return id;}
    public void setIdade(String idade) {this.idade = idade;}
    public void setNome(String nome){this.nome = nome;}
}
