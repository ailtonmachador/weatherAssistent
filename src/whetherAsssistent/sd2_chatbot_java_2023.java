package whetherAsssistent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONObject;

public class sd2_chatbot_java_2023 extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new sd2_chatbot_java_2023();
            }
        });
    }

    private static final long serialVersionUID = 1L;
    private JTextArea textArea = new JTextArea();
    private JTextField textField = new JTextField();
    private JButton button = new JButton();
    private JLabel label = new JLabel();
    private final List<String> userInput = new ArrayList<>();
    protected int step;

    public sd2_chatbot_java_2023() {
        setTitle("Weather Assistant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Color.darkGray);

        textArea.setSize(750, 510);
        textArea.setLocation(1, 1);
        textArea.setBackground(Color.darkGray);

        textField.setSize(725, 20);
        textField.setLocation(1, 530);

        label.setText("SEND");

        button.add(label);
        button.setSize(100, 20);
        button.setLocation(725, 530);

        add(textArea);
        add(textField);
        add(button);

        textArea.setForeground(Color.WHITE);
        textArea.append("Hi, I'm Alice," + "\nI like to drink Guinness and play rugby... "
                + "oh yeah, sometimes I do an extra job as a weather assistant." + "\nI can check the weather and \n"
                + "give you advice about what to do" + "\nand make sure you won't forget it. "
                + "What is the country you want to know about?\n\n");

        textField.setText("");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();
                textField.setText("");
                String country, city;

                if (step == 0) {
                    textArea.append("Weather Assistant: What is the city you want to know about?\n");
                    step++;
                    userInput.add(inputText);
                } else if (step == 1) {
                    textArea.append("\nYou: " + inputText + "\n");
                    textArea.append("Weather Assistant: Weather information\n\n");
                    step++;
                    userInput.add(inputText);
                    country = userInput.get(0);
                    city = userInput.get(1);
                    String weatherInfoResult = formatCity(country, city);
                    textArea.append(weatherInfoResult);
                    label.setText("CLOSE");
                } else {
                    textArea.append("\nYou: " + inputText + "\n");
                    textArea.append("Weather Assistant: Conversation completed. Thank you!\n");
                    System.exit(0);
                }
            }
        });

        Voice playIntroductionVoice = new Voice();
        playIntroductionVoice.introduction();

        setVisible(true);
    }

    private String formatCity(String country, String city) {
        String formattedCountry = country.trim();
        String formattedCity = city.trim();
        if (formattedCity.contains(" ") || formattedCountry.contains(" ")) {
            String cityFormatted = formattedCity.replace(" ", "%20");
            String countryFormatted = formattedCountry.replace(" ", "%20");
            return weatherApi(countryFormatted, cityFormatted);
        } else {
            return weatherApi(formattedCountry, formattedCity);
        }
    }

    private static String weatherApi(String country, String city) {
        GeoLocation geo = new GeoLocation();
        WeatherApiInfo weatherInfo = new WeatherApiInfo();
        Voice sendTemperatureForAliceVoice = new Voice();
        String json = geo.FindLatLog(country, city);

        try {
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String lat = jsonObject.getString("lat");
            String lon = jsonObject.getString("lon");

            weatherInfo.FindWeatherInfo(lat, lon);
            double temp = weatherInfo.getTemp();

            sendTemperatureForAliceVoice.speakTemperature(temp);

            if (temp < -5) {
                return "It is way too cold, do not go out. I recommend you stay in and have a warm drink.\n\n"
                        + weatherInfo.toString();
            }
            if (temp < 8) {
                return "The weather is really cold. Make sure to carry a jacket and have some hot cocoa!\n\n"
                        + weatherInfo.toString();
            } else if (temp > 8 && temp < 14) {
                return "The weather is a bit chilly. Consider wearing a sweater and enjoy a nice cup of coffee.\n\n "
                        + weatherInfo.toString();
            } else if (temp > 14 && temp < 20) {
                return "The weather is not cold. Wear something light and enjoy the day. It's perfect for a beach visit!\n "
                        + weatherInfo.toString();
            } else if (temp > 20 && temp < 40) {
                return "It is a great day to go to the beach, have a cold drink, and enjoy the day with your friends.\n "
                        + weatherInfo.toString();
            } else if (temp > 41) {
                return "The weather is extremely hot. Find a way to cool off! Having a very cold drink would be a good idea.\n\n";
            }
        } catch (Exception e) {
            return "Error: Country or city not found. Close the application and try again (press anything to continue).\n\n";
        }
        return json;
    }
}
