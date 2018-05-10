package Logic;

public class Functions {

    public String transformPrice(double price){
        String result;
        int iPrice = (int) Math.round(price);
        if(iPrice/1000000 >= 1){
            result = String.valueOf(iPrice/1000000)+"M";
        }else if(iPrice/1000 >= 1) {
            result = String.valueOf(iPrice / 1000) + "K";
        }else{
            result = String.valueOf(iPrice);
        }

        return result;
    }

}
