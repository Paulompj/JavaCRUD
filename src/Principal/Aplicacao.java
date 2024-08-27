package Principal;

import DAO.Functions;
import DAO.UsuarioDAO;

import java.util.Locale;
import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner reader = new Scanner(System.in);
        int choice;
        do{
            System.out.println("\t1 - Create\n\t2 - Uptade\n\t3 - Read\n\t4 - Delete\n\t5 - Exit");
            System.out.print("Escolha: ");
            choice = reader.nextInt();
            reader.nextLine();
            switch (choice){
                case 1:
                    UsuarioDAO.cadastrarUsuario(Functions.registerUser());
                    break;
                case 2:
                    UsuarioDAO.atualizarRegistro(Functions.registerId(),Functions.registerUser());
                    break;
                case 3:
                    UsuarioDAO.listarPessoas();
                    break;
                case 4:
                    UsuarioDAO.deletarUsuario(Functions.registerId());
            }
        }while (choice != 5);
    }
}
