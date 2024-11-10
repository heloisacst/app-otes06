package utils;

public class Validador {

  // Validação de email usando expressão regular
        public static boolean validarEmail(String email) {
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$"; // Regex para validar o formato de email
            return email != null && email.matches(regex);
        }

        // Validação de CRM (4 a 6 dígitos numéricos)
        public static boolean validarCRM(String crm) {
            String regex = "^[0-9]{4,6}$"; // CRM deve ter entre 4 e 6 dígitos
            return crm != null && crm.matches(regex);
        }
}
