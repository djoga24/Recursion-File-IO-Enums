import java.util.Scanner;

public class WeatherEnum {

    enum WeatherType {
        RAIN("a rain jacket or an umbrella"),
        SNOW("a heavy coat, gloves, and a hat"),
        SUN("sunglasses and sunscreen"),
        WIND("a scarf"),
        WARM("shorts and a t-shirt"),
        COLD("a warm coat and boots");

        private final String suggestion;

        WeatherType(String s) {
            suggestion = s;
        }

        public String getSuggestion() {
            return suggestion;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the weather type from the user
        WeatherType choice= null;
        boolean validWeatherType = false;

        while (!validWeatherType) {

            System.out.print("What is the state of the weather today? ");
            String input = scanner.nextLine().toUpperCase();
            try {
                choice = WeatherType.valueOf(input);
                validWeatherType = true;

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid weather type. Please enter a valid weather type.");
            }
        }

        // Suggest clothing or accessories based on the weather type
        String answer = choice.getSuggestion();
        System.out.printf("You should probably wear %s today!\n", answer);
    }
}