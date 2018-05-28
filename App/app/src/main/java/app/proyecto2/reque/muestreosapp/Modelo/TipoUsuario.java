package app.proyecto2.reque.muestreosapp.Modelo;

public enum TipoUsuario {
    NoAutentificado,
    Analista,
    Administrador;

    public static TipoUsuario fromInteger(int x) {
        switch(x) {
            case 0:
                return TipoUsuario.NoAutentificado;
            case 1:
                return TipoUsuario.Analista;
            case 2:
                return TipoUsuario.Administrador;
        }
        return null;
    }
    
}
