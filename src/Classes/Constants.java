package Classes;
public class Constants {
    // public static final class Flavors {
    //     public static final String MARGUERITA = "Marguerita";
    //     public static final String QUATRO_QUEIJOS = "QuatroQueijos";
    //     public static final String FRANGO_CATUPIRY = "FrangoCatupiry";
    //     public static final String PEPPERONI = "Pepperoni";
    //     public static final String PORTUGUESA = "Portuguesa";
    //     public static final String CALABRESA_ESPECIAL = "CalabresaEspecial";
    // }

    public static final class Flavors {
        public static final int MARGUERITA = 1;
        public static final int QUATRO_QUEIJOS = 2;
        public static final int FRANGO_CATUPIRY = 3;
        public static final int PEPPERONI = 4;
        public static final int PORTUGUESA = 5;
        public static final int CALABRESA_ESPECIAL = 6;
    }

    public static final class Status {
        public static final int FINISHED = 3;
        public static final int CONFIRMED = 1;
        public static final int OUT_FOR_DELIVERY = 2;
    }

    public static final class Payment {
        public static final int CONFIRMED = 1;
        public static final int PENDING = 2;
    }

    public static final class Sizes {
        public static final String SMALL = "P";
        public static final String MEDIUM = "M";
        public static final String LARGE = "G";
    }

    public static final class Extensions {
        public static final String TXT = ".txt";
        public static final String CSV = ".csv";
    }

    public static final class Paths {
        public static final String TXT = System.getProperty("user.dir") + "\\ArquivosTxt\\";
        public static final String CSV = System.getProperty("user.dir") + "\\ArquivosCsv\\";
    }
}
