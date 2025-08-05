import java.util.Random;

public class TV {
private String model;
private String diagonal;
private String technology;
private boolean vipysk;

    public String getDiagonal() {
        return diagonal;
    }

    public String getModel() {
        return model;
    }

    public String getTechnology() {
        return technology;
    }

    public boolean isVipysk() {
        return vipysk;
    }

    public TV(String model, String technology, String diagonal, boolean vipysk) {
        this.model = model;
        this.technology = technology;
        this.diagonal = diagonal;
        this.vipysk = vipysk;
    }

    @Override
    public String toString() {
        return "TV{" +
                "model='" + model + '\'' +
                ", diagonal='" + diagonal + '\'' +
                ", technology='" + technology + '\'' +
                ", vipysk=" + vipysk +
                '}';
    }

    public TV() {
        String[] vseModeli ={"LG", "Samsung","Haier"};
        String[] vseTecnology ={"OLED", "IPS","VA"};
        String[] vseDiagonal ={"32", "43","55","65","75"};
        Random random = new Random();
        int novayaModel = random.nextInt(vseModeli.length);
        int novayaTecnology = random.nextInt(vseTecnology.length);
        int novayaDiagonal = random.nextInt(vseDiagonal.length);
        int novVipysk = random.nextInt(2);
        this.model = vseModeli[novayaModel];
        this.technology = vseTecnology[novayaTecnology];
        this.diagonal = vseDiagonal[novayaDiagonal];
        this.vipysk = (novVipysk==1);
    }
}
