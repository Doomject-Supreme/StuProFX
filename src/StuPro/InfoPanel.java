package StuPro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hdm.shared.Toolbox;
import javaFXProject.Interface;

public class InfoPanel extends JPanel {
	static JLabel temperatureLabel;
	static JLabel betterTemperatureLabel;
	static JLabel cyclistsLabel;
	static City stuttgart;

	static String startDay;
	static String startMonth;
	static String startYear;

	static String endDay;
	static String endMonth;
	static String endYear;

	//static int cyclist;
	static double betterWeatherData;
	public static String foodsharingCount = "42";
	public static int cyclistCount = 42;

	public static double updateWeatherData() {
		// System.out.println("getting weather data");

		String html = Toolbox
				.downloadIntoString("https://www.dwd.de/DE/wetter/wetter_weltweit/europa/wetterwerte/_node.html");
		// System.out.println("data loaded from DWD");

		stuttgart.temperature = Toolbox.randomDouble(-20, +40);

		Pattern regex = Pattern.compile("(?s)" + stuttgart.name + "(.*?<td>){3}(.*?)</td>");
		Matcher matcher = regex.matcher(html);
		if (matcher.find()) {
			stuttgart.temperature = Double.parseDouble(matcher.group(2));
		}
		// System.out.println("T Stuttgart: " + stuttgart.temperature);
		return stuttgart.temperature;
	}

	public static void betterWeatherData() throws IOException {

		String temp = "";

		String html = Toolbox.downloadIntoString(
				"https://weather.com/weather/today/l/28a0c2648f540ed36e48fe165453ea4d18a489e9e2989e2a547a01f22ba86985");
		// System.out.println(html);

		Pattern regex = Pattern.compile("(CurrentConditions--tempValue--3KcTQ\">)(.*?)(°</span)");
		Matcher matcher = regex.matcher(html);

		while (matcher.find()) {
			// System.out.println(matcher.group(2));
			temp = matcher.group(2);
		}

		// System.out.println(temp);

		double temperature = Double.parseDouble(temp);

		temperature = ((temperature - 32) * 5) / 9;
		betterWeatherData = temperature;
	}

	public static void cyclists() throws IOException {

		startDay();
		endDay();

		String html = Toolbox.downloadIntoString(
				"https://www.eco-visio.net/api/aladdin/1.0.0/pbl/publicwebpageplus/607?withNull=true&begin=" + startDay
						+ "%2F" + startMonth + "%2F" + startYear + "&end=" + endDay + "%2F" + endMonth + "%2F" + endYear
						+ "&pratiques=2");
		// System.out.println(html);

		Reader reader = new StringReader(html);
		Scanner scanner = new Scanner(reader);

		String line = scanner.nextLine();
		Pattern regex = Pattern.compile("(total\":)(\\d*)");
		Matcher matcher = regex.matcher(line);
		
		cyclistCount = 0;

		while (matcher.find()) {
			// System.out.println(matcher.group(2));
			int tempCyc = Integer.parseInt(matcher.group(2));
			cyclistCount += tempCyc;
		}
		
		Interface.cyclistCount.setText(" " + cyclistCount);
		Interface.textFlowCyclists.setTranslateX(App.widthOfScreen / 2 - Interface.textFlowCyclists.getBoundsInLocal().getWidth() / 2);


	}

	public static void startDay() {

		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		Date currentDate = new Date();

		startDay = dayFormat.format(currentDate);
		startMonth = monthFormat.format(currentDate);
		startYear = yearFormat.format(currentDate);
	}

	public static void endDay() {

		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		Date currentDate = new Date();

		String day = dayFormat.format(currentDate);
		String month = monthFormat.format(currentDate);
		String year = yearFormat.format(currentDate);

		LocalDate today = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		LocalDate tomorrow = today.plusDays(1);

		DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");
		DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
		DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");

		endDay = dayFormatter.format(tomorrow);
		endMonth = monthFormatter.format(tomorrow);
		endYear = yearFormatter.format(tomorrow);

	}

	public static void foodsharing() throws IOException {

		String html = "";
		html = Toolbox.downloadIntoString("https://foodsharing.de/statistik");
		// System.out.println(html);

		Pattern regex = Pattern.compile("(?s)(\\s{4}<h4>)(.*?)(<span style)");
		Matcher matcher = regex.matcher(html);

		if (matcher.find()) {
			// System.out.println(matcher.group(2));
			foodsharingCount = matcher.group(2);
		}
		
		Interface.foodsharingCount.setText(foodsharingCount + " kg");
		Interface.textFlowFoodsharing.setTranslateX(App.widthOfScreen / 2 - Interface.textFlowFoodsharing.getBoundsInLocal().getWidth() / 2);

	}

}
