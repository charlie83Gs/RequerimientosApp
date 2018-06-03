package app.proyecto2.reque.muestreosapp.Modelo;

public enum TipoTarea {
    Productiva,
    NoProductiva,
    Colaborativa;

    public static TipoTarea fromInteger(int x) {
        switch(x) {
            case 1:
                return TipoTarea.Productiva;
            case 2:
                return TipoTarea.NoProductiva;
            case 3:
                return TipoTarea.Colaborativa;
        }
        return null;
    }
}
