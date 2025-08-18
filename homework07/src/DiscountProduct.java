import java.util.Date;

public class DiscountProduct extends Product{
    private double discount;
    private Date dateEnd;

    public DiscountProduct(String name, int cost, double discount, int days) throws Exception {
        super(name, cost);
        this.discount = discount;
        this.dateEnd = new Date();
        this.dateEnd.setTime(this.dateEnd.getTime()+days*24*60*60*1000);
    }

    public DiscountProduct(String name, int cost) throws Exception {
        super(name, cost);
    }
    @Override
    public int getCost() {
        int res = super.getCost();
        Date nowDate = new Date();
        if (nowDate.before(this.dateEnd))
        {
            res = (int) Math.ceil(res*(1-(this.discount/100)));
        }
        return res;
    }
}
