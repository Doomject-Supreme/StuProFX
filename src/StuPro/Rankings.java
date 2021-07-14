package StuPro;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javaFXProject.Interface;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Rankings {

	public static Queue<String> recent = new LinkedList<String>();
	public static List<StudentLite> weekly = new ArrayList<StudentLite>();

	static boolean newStudent = true;

	Student[] monthly;

	private static final SimpleDateFormat dailyTimestamp = new SimpleDateFormat("YYY-MM-dd");
	private static DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		recentRanking();
	}

	public static void weeklyRanking() throws FileNotFoundException, InterruptedException {

		for (StudentLite retStud : weekly) {
			retStud.points = 0;
		}

		Reader reader = new FileReader("assets/userLog");
		Scanner scanner = new Scanner(reader);

		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		Date currentDate = new Date();

		String day = dayFormat.format(currentDate);
		String month = monthFormat.format(currentDate);
		String year = yearFormat.format(currentDate);

		LocalDate today = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		// System.out.println(today);

		if (scanner.hasNext()) {

			do {
				String student = scanner.nextLine();
				// System.out.println(student);

				String patternRecent = "(\\d{4})(-)(\\d{2})(-)(\\d{2}).*(nickname:)(.*)";
				Pattern patternOfRecent = Pattern.compile(patternRecent);
				Matcher matcherOfRecent = patternOfRecent.matcher(student);

				while (matcherOfRecent.find()) {
					// System.out.println(matcherOfRecent.group(7));

					LocalDate logDate = LocalDate.of(Integer.parseInt(matcherOfRecent.group(1)),
							Integer.parseInt(matcherOfRecent.group(3)), Integer.parseInt(matcherOfRecent.group(5)));
					// System.out.println(logDate);
					LocalDate yesterday = today.plusDays(-7);
					// System.out.println(yesterday);

					if (logDate.isAfter(yesterday)) {

						for (StudentLite retStud : weekly) {
							if (retStud.nickname.equals(matcherOfRecent.group(7))) {
								retStud.points++;
								newStudent = false;

							}
						}

						if (newStudent) {
							StudentLite tempStud = new StudentLite(matcherOfRecent.group(7), 1);
							weekly.add(tempStud);
						}
						newStudent = true;
					}
				}

			} while (scanner.hasNext());
		}

		Comparator<StudentLite> studentPointComparator = new Comparator<StudentLite>() {

			@Override
			public int compare(StudentLite studi1, StudentLite studi2) {
				return studi2.points - studi1.points;
			};

		};

		Collections.sort(weekly, studentPointComparator);

		// for ( StudentLite object:weekly) { System.out.println(object); }

		if (Rankings.weekly.size() > 0) {
			if (Integer.toString(weekly.get(0).points).length() < 2) {
				Interface.weeklyUser1points.setText("  " + weekly.get(0).points + " ");
			} else {
				Interface.weeklyUser1points.setText(weekly.get(0).points + " ");
			}
			TimeUnit.SECONDS.sleep(2);

			Interface.weeklyUser1.setText(weekly.get(0).nickname + "\n");
		}
		TimeUnit.SECONDS.sleep(2);

		if (Rankings.weekly.size() > 1) {
			if (Integer.toString(weekly.get(1).points).length() < 2) {
				Interface.weeklyUser2points.setText("  " + weekly.get(1).points + " ");
			} else {
				Interface.weeklyUser2points.setText(weekly.get(1).points + " ");
			}
			TimeUnit.SECONDS.sleep(2);

			Interface.weeklyUser2.setText(weekly.get(1).nickname + "\n");
		}
		TimeUnit.SECONDS.sleep(2);


		if (Rankings.weekly.size() > 2) {
			if (Integer.toString(weekly.get(2).points).length() < 2) {
				Interface.weeklyUser3points.setText("  " + weekly.get(2).points + " ");
			} else {
				Interface.weeklyUser3points.setText(weekly.get(2).points + " ");
			}
			TimeUnit.SECONDS.sleep(2);

			Interface.weeklyUser3.setText(weekly.get(2).nickname + "\n");
		}
		TimeUnit.SECONDS.sleep(2);


		if (Rankings.weekly.size() > 3) {
			if (Integer.toString(weekly.get(3).points).length() < 2) {
				Interface.weeklyUser4points.setText("  " + weekly.get(3).points + " ");
			} else {
				Interface.weeklyUser4points.setText(weekly.get(3).points + " ");
			}
			TimeUnit.SECONDS.sleep(2);

			Interface.weeklyUser4.setText(weekly.get(3).nickname + "\n");
		}
		TimeUnit.SECONDS.sleep(2);

		
		if (Rankings.weekly.size() > 4) {
			if (Integer.toString(weekly.get(4).points).length() < 2) {
				Interface.weeklyUser5points.setText("  " + weekly.get(4).points + " ");
			} else {
				Interface.weeklyUser5points.setText(weekly.get(4).points + " ");
			}
			TimeUnit.SECONDS.sleep(2);

			Interface.weeklyUser5.setText(weekly.get(4).nickname + "\n");
		}
		TimeUnit.SECONDS.sleep(2);
		
		if(weekly.size() == 0) {
			Interface.weeklyUser1points.setText("    ");
			Interface.weeklyUser1.setText("   " + "\n");
			Interface.weeklyUser2points.setText("    ");
			Interface.weeklyUser2.setText("   " + "\n");
			Interface.weeklyUser3points.setText("    ");
			Interface.weeklyUser3.setText("   " + "\n");
			Interface.weeklyUser4points.setText("    ");
			Interface.weeklyUser4.setText("   " + "\n");
			Interface.weeklyUser5points.setText("    ");
			Interface.weeklyUser5.setText("   " + "\n");
		}
		
		if(weekly.size() == 0) {
			Interface.weeklyUser1points.setText("    ");
			Interface.weeklyUser1.setText("   " + "\n");
			Interface.weeklyUser2points.setText("    ");
			Interface.weeklyUser2.setText("   " + "\n");
			Interface.weeklyUser3points.setText("    ");
			Interface.weeklyUser3.setText("   " + "\n");
			Interface.weeklyUser4points.setText("    ");
			Interface.weeklyUser4.setText("   " + "\n");
			Interface.weeklyUser5points.setText("    ");
			Interface.weeklyUser5.setText("   " + "\n");
		}
		if(weekly.size() == 1) {
			Interface.weeklyUser2points.setText("    ");
			Interface.weeklyUser2.setText("   " + "\n");
			Interface.weeklyUser3points.setText("    ");
			Interface.weeklyUser3.setText("   " + "\n");
			Interface.weeklyUser4points.setText("    ");
			Interface.weeklyUser4.setText("   " + "\n");
			Interface.weeklyUser5points.setText("    ");
			Interface.weeklyUser5.setText("   " + "\n");
		}
		if(weekly.size() == 2) {
			Interface.weeklyUser3points.setText("    ");
			Interface.weeklyUser3.setText("   " + "\n");
			Interface.weeklyUser4points.setText("    ");
			Interface.weeklyUser4.setText("   " + "\n");
			Interface.weeklyUser5points.setText("    ");
			Interface.weeklyUser5.setText("   " + "\n");
		}
		if(weekly.size() == 3) {
			Interface.weeklyUser4points.setText("    ");
			Interface.weeklyUser4.setText("   " + "\n");
			Interface.weeklyUser5points.setText("    ");
			Interface.weeklyUser5.setText("   " + "\n");
		}
		
		if(weekly.size() == 4) {
			Interface.weeklyUser5points.setText("    ");
			Interface.weeklyUser5.setText("   " + "\n");
		}
		
		//System.out.println("Weekly size: " + weekly.size());
	}

	public static void recentRanking() throws FileNotFoundException, InterruptedException {
		String today = dailyTimestamp();
		// System.out.println(today);
		// today = "2021-06-22";

		String recentStudent = null;

		Reader reader = new FileReader("assets/userLog");
		Scanner scanner = new Scanner(reader);

		if (scanner.hasNext()) {

			do {
				String student = scanner.nextLine();
				// System.out.println("lookin at: " + student);

				String patternRecent = today + "(.*)(nickname:)(.*)";
				Pattern patternOfRecent = Pattern.compile(patternRecent);
				Matcher matcherOfRecent = patternOfRecent.matcher(student);

				while (matcherOfRecent.find()) {
					recentStudent = matcherOfRecent.group(3);
					// System.out.println(recent.size());
				}
				if (recent.size() == 5) {
					recent.remove();
				}
				if (!recent.contains(recentStudent)) {
					if (recentStudent != null) {
						recent.add(recentStudent);
					}
				}
			} while (scanner.hasNext());
		}

		if (recent.peek() != null) {
			Interface.user1.setText(recent.peek() + "\n");
			TimeUnit.SECONDS.sleep(2);
			Interface.user6.setText(recent.poll() + "\n");
		} else {
			Interface.user1.setText(" " + "\n");
			TimeUnit.SECONDS.sleep(2);

			Interface.user6.setText(" " + "\n");

		}

		if (recent.peek() != null) {
			Interface.user2.setText(recent.peek() + "\n");
			TimeUnit.SECONDS.sleep(2);

			Interface.user7.setText(recent.poll() + "\n");

		} else {
			Interface.user2.setText(" " + "\n");
			TimeUnit.SECONDS.sleep(2);

			Interface.user7.setText(" " + "\n");

		}
		if (recent.peek() != null) {
			Interface.user3.setText(recent.peek() + "\n");
			TimeUnit.SECONDS.sleep(2);

			Interface.user8.setText(recent.poll() + "\n");

		} else {
			Interface.user3.setText(" " + "\n");
			TimeUnit.SECONDS.sleep(2);

			Interface.user8.setText(" " + "\n");

		}
		if (recent.peek() != null) {
			Interface.user4.setText(recent.peek() + "\n");
			TimeUnit.SECONDS.sleep(2);

			Interface.user9.setText(recent.poll() + "\n");

		} else {
			Interface.user4.setText(" " + "\n");
			TimeUnit.SECONDS.sleep(2);

			Interface.user9.setText(" " + "\n");

		}
		if (recent.peek() != null) {
			Interface.user5.setText(recent.peek() + "\n");
			TimeUnit.SECONDS.sleep(2);

			Interface.user10.setText(recent.poll() + "\n");

		} else {
			Interface.user5.setText(" " + "\n");
			TimeUnit.SECONDS.sleep(2);

			Interface.user10.setText(" " + "\n");

		}

	}

	public static String dailyTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return dailyTimestamp.format(timestamp);
	}
}
