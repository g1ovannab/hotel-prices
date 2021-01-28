import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws ParseException {
        Scanner scan = new Scanner(System.in);

        //Array que guarda os três hoteis;
        Hotel[] hotels = new Hotel[3];

        //Primeiro Hotel - Lakewood
        Rate rateRegularHotel1 = new Rate(110, 90);
        Rate rateRewardsHotel1 = new Rate(80, 80);
        Hotel hotel1 = new Hotel("Lakewood", rateRegularHotel1, rateRewardsHotel1, (byte) 3);

        //Segundo Hotel - Bridgewood
        Rate rateRegularHotel2 = new Rate(160, 60);
        Rate rateRewardsHotel2 = new Rate(110, 80);
        Hotel hotel2 = new Hotel("Bridgewood", rateRegularHotel2, rateRewardsHotel2, (byte) 4);

        //Terçeiro Hotel - Ridgewood
        Rate rateRegularHotel3 = new Rate(220, 150);
        Rate rateRewardsHotel3 = new Rate(100, 40);
        Hotel hotel3 = new Hotel("Ridgewood", rateRegularHotel3, rateRewardsHotel3, (byte) 5);

        hotels[0] = hotel1;
        hotels[1] = hotel2;
        hotels[2] = hotel3;


        byte op = -1;
        String type = "";
        String[] dates;
        do {
            int[] prices = new int[3];
            Client client = new Client(prices);

            boolean repeat = false;
            do{
                System.out.println("\nInforme o tipo do cliente (Regular ou Rewards): ");
                type = scan.nextLine().toLowerCase();

                if (type.equals("regular") || type.equals("rewards")){
                    client.setType(type);
                    repeat = false;
                } else{
                    System.out.println("\nDigitação incorreta. Tente novamente.");
                    repeat = true;
                }
            } while (repeat == true);
            

            System.out.println("\nInforme as datas de estadia no hotel (dd/mm/aaaa separadas por vírgula - ,): ");
            dates = scan.nextLine().split(",");
            client.setDates(dates);

            ArrayList<Date> formatedDatesArray = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            for (byte i = 0; i < dates.length; i++){
                Date formatedDate = (Date) format.parse(dates[i]);
                formatedDatesArray.add(formatedDate);
            }

            byte semana = 0, fimdesemana = 0;
            System.out.println("\nSerão: ");

            if (client.getTypeClient().equals("regular")){
                for (byte i = 0; i < formatedDatesArray.size(); i++){
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(formatedDatesArray.get(i));
                    byte dayOfWeek = (byte) cal.get(Calendar.DAY_OF_WEEK);
        
                    if (dayOfWeek >= 2 && dayOfWeek <= 6){
                        prices[0] += hotels[0].getRateRegular().getRateWeek();
                        prices[1] += hotels[1].getRateRegular().getRateWeek();
                        prices[2] += hotels[2].getRateRegular().getRateWeek();
                        semana++;
                    } else if (dayOfWeek == 1 || dayOfWeek == 7){
                        prices[0] += hotels[0].getRateRegular().getRateWeekend();
                        prices[1] += hotels[1].getRateRegular().getRateWeekend();
                        prices[2] += hotels[2].getRateRegular().getRateWeekend();
                        fimdesemana++;
                    }
                }

                System.out.println(semana + " dias de semana e " + fimdesemana + " dias de fim de semana.");

                for (int i = 0; i < prices.length; i++)
                    System.out.println("\nPreço do hotel " + hotels[i].getName() + ": R$" + prices[i] + ",00.");

                getCheapestHotel(prices, hotels);

                
            } else if (client.getTypeClient().equals("rewards")){
                for (byte i = 0; i < formatedDatesArray.size(); i++){
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(formatedDatesArray.get(i));
                    byte dayOfWeek = (byte) cal.get(Calendar.DAY_OF_WEEK);
        
                    if (dayOfWeek >= 2 && dayOfWeek <= 6){
                        prices[0] += hotels[0].getRateRewards().getRateWeek();
                        prices[1] += hotels[1].getRateRewards().getRateWeek();
                        prices[2] += hotels[2].getRateRewards().getRateWeek();
                        semana++;
                    } else if (dayOfWeek == 1 || dayOfWeek == 7){
                        prices[0] += hotels[0].getRateRewards().getRateWeekend();
                        prices[1] += hotels[1].getRateRewards().getRateWeekend();
                        prices[2] += hotels[2].getRateRewards().getRateWeekend();
                        fimdesemana++;
                    }
                    
                }

                System.out.println(semana + " dias de semana e " + fimdesemana + " dias de fim de semana.");

                for (int i = 0; i < prices.length; i++)
                    System.out.println("\nPreço do hotel " + hotels[i].getName() + ": R$" + prices[i] + ",00.");

                getCheapestHotel(prices, hotels);

            } else{
                System.out.println("\n\nNão foi possível determinar o preço da estadia!");
            }

            
            String answer = "";
            repeat = false;

            System.out.println("\n\nDeseja ohar mais algum cliente? ");
            answer = scan.nextLine().toLowerCase();

            if (answer.equals("não") || answer.equals("nao") || answer.equals("n")){
                op = 0;
            } 

        } while (op != 0);

        scan.close();
    }

    public static void getCheapestHotel(int[] prices, Hotel[] hotels){
        int min = prices[0];
        int index = 0;
        for (int i = 0; i < prices.length - 1; i++){
            if (min > prices[i+1]){
                min = prices[i+1];
                index = i + 1;
            } else if (min == prices[i+1]){
                if (hotels[i].getStars() > hotels[i+1].getStars()){
                    min = prices[i];
                    index = i;
                } else if (hotels[i].getStars() < hotels[i+1].getStars()){
                    min = prices[i+1];
                    index = i + 1;
                }
            }
        }

        System.out.println("\n\nO hotel mais barato é o " + hotels[index].getName() + " com o preço de R$" + prices[index] + ",00.");
    }
}
