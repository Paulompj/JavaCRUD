package DAO;

import Conexao.Conexao;
import Entidades.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    public static void cadastrarUsuario(Usuario usuario){
        String sql = "INSERT INTO pessoas(nome,idade) VALUES(?,?)";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1,usuario.getNome());
            ps.setString(2,usuario.getIdade());
            ps.execute();
            System.out.println("Cadastrado Com Sucesso!!");
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void deletarUsuario(int id){
        String sqlTeste = "DELETE FROM enderecos WHERE id = ?";
        String sql = "DELETE FROM pessoas WHERE id = ?";
        PreparedStatement ps = null;
        try{
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, String.valueOf(id));
            ps.execute();
            int rowsAffected = ps.executeUpdate(); // Verifica quantas linhas foram afetadas
            if (rowsAffected > 0) {
                System.out.println("Registro deletado com sucesso!");
            } else {
                System.out.println("Nenhum registro encontrado com o ID: " + id);
            }
            ps.close();
        } catch (SQLException e) {
            String sqlState = e.getSQLState();
            int errorCode = e.getErrorCode();
            String errorMessage = e.getMessage();
            if ("23000".equals(sqlState)) { // Violação de chave estrangeira
                System.err.println("Erro: Não foi possível deletar o usuário porque ele está relacionado a outros registros.");
            } else if (errorCode == 1062) { // Erro de chave duplicada
                System.err.println("Erro: Chave duplicada, o ID fornecido já existe.");
            } else if ("42S02".equals(sqlState)) { // Tabela não encontrada
                System.err.println("Erro: A tabela especificada não existe.");
            } else {
                System.err.println("Erro: " + errorMessage + " (SQLState: " + sqlState + ", ErrorCode: " + errorCode + ")");
            }
        }
    }
    public static void listarPessoas() {
        String sql = "SELECT * FROM pessoas";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            rs = ps.executeQuery(); // Executa a consulta e retorna os resultados

            // Itera sobre o ResultSet para processar os dados
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                // Adicione mais colunas conforme necessário

                System.out.println("ID: " + id + ", Nome: " + nome+", Idade: "+idade);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar pessoas: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
    public static void atualizarRegistro(int id, Usuario usuario){
        String sql = "UPDATE pessoas SET nome = ?, idade = ? WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getIdade());
            ps.setString(3, String.valueOf(id)); // Usa o ID para localizar o registro a ser atualizado
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registro atualizado com sucesso!");
            } else {
                System.out.println("Nenhum registro encontrado com o ID: " + usuario.getId());
            }
            ps.close();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pessoa: " + e.getMessage());
        }
    }

}
