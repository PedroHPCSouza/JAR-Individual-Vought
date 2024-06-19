public class Suporte extends Funcionario{

    SuporteConexao suporteConexao = new SuporteConexao();

    public Suporte(int id, String nome, String email, String senha) {
        super(id, nome, email, senha);
    }
    public Suporte(){

    }

    public Boolean login(String email, String senha){
        Boolean loginRealizado = false;
        if (senha.length() >= 8) {

            // Conexão com banco da dados para login

            Integer usuarioExistentes = suporteConexao.contarUsuarioExistente(email, senha);

            if (usuarioExistentes == 1) {
                loginRealizado = true;
            } else {
                System.out.println("\nEmail e/ou senha incorretos, tente novamente\n");
            }

        } else {
            System.out.println("\nSenha não cadastrada\n");
        }
        return loginRealizado;
    }

    public Integer buscarEmpresa(String email, String senha){

        Integer idEmpresa = suporteConexao.capturarIdEmpresa(email, senha);
        return idEmpresa;

    }

}
