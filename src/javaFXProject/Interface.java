package javaFXProject;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import StuPro.App;
import StuPro.InfoPanel;
import StuPro.Rankings;
import hdm.shared.Toolbox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class Interface extends Application {

	public static Group groupCyclists;
	public static Group groupFoodsharing;
	public static Group groupNewUser;
	public static Group groupKnownUser;
	public static Group groupTrashPhoto;

	public static Text cyclistCount;
	public static Text foodsharingCount;
	public static Text textDarkBold;
	public static Text score;
	public static TextFlow textFlowCyclists;
	public static TextFlow textFlowFoodsharing;
	public static Text nickname;

	static int rectWidth = 350;
	static int textFlowMargin = 120;
	static int rankingTitleMargin = 225;

	public static Text user1;
	public static Text user2;
	public static Text user3;
	public static Text user4;
	public static Text user5;

	public static Text user6;
	public static Text user7;
	public static Text user8;
	public static Text user9;
	public static Text user10;

	public static Text weeklyUser1;
	public static Text weeklyUser2;
	public static Text weeklyUser3;
	public static Text weeklyUser4;
	public static Text weeklyUser5;
	public static Text weeklyUser6;
	public static Text weeklyUser7;
	public static Text weeklyUser8;
	public static Text weeklyUser9;
	public static Text weeklyUser10;

	public static Text weeklyUser1points;
	public static Text weeklyUser2points;
	public static Text weeklyUser3points;
	public static Text weeklyUser4points;
	public static Text weeklyUser5points;
	public static Text weeklyUser6points;
	public static Text weeklyUser7points;
	public static Text weeklyUser8points;
	public static Text weeklyUser9points;
	public static Text weeklyUser10points;

	public static Rectangle rectanglePhoto;

	public static TextFlow text_flow2;

	public static void main(String args[]) throws IOException {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		sceneCyclists();
		sceneFoodsharing();
		sceneNewUser();
		sceneKnownUser();
		sceneTrashPhoto();

		Group megaGroup = new Group(groupCyclists, groupFoodsharing, groupNewUser, groupKnownUser, groupTrashPhoto);

		// Creating a Scene by passing the group object, height and width
		Scene scene = new Scene(megaGroup, App.widthOfScreen, App.heightOfScreen);

		// setting color to the scene
		scene.setFill(Color.web("#4AAC8D"));

		// Setting the title to Stage.
		stage.setTitle("Sample Application");
		stage.initStyle(StageStyle.UNDECORATED);

		// Adding the scene to Stage
		stage.setScene(scene);

		// Displaying the contents of the stage
		stage.show();
		// sceneOne();

	}

	public static void sceneCyclists() throws FileNotFoundException {

		Text textLight = new Text("We hope you will be one of them!");
		textLight.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 70));
		textLight.setFill(Color.web("#AFEDD9"));
		textLight.setTextAlignment(TextAlignment.CENTER);
		textLight.setX((App.widthOfScreen - textLight.getBoundsInLocal().getWidth()) / 2);
		textLight.setY(App.heightOfScreen / 2.5);

		// create TextFlow
		textFlowCyclists = new TextFlow();

		// create text
		Text text_1 = new Text("There have been");
		text_1.setFill(Color.web("#393C3A"));
		text_1.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 50));

		// create text
		cyclistCount = new Text(" " + InfoPanel.cyclistCount);
		cyclistCount.setFill(Color.web("#393C3A"));
		cyclistCount.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 50));

		Text text_3 = new Text(" cyclists spotted in Stuttgart today!");
		text_3.setFill(Color.web("#393C3A"));
		text_3.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 50));

		// add text to textflow
		textFlowCyclists.getChildren().add(text_1);
		textFlowCyclists.getChildren().add(cyclistCount);
		textFlowCyclists.getChildren().add(text_3);

		// set text Alignment
		textFlowCyclists.setTextAlignment(TextAlignment.CENTER);
		textFlowCyclists.setTranslateX(App.widthOfScreen / 2 - textFlowCyclists.getBoundsInLocal().getWidth() / 2);
		textFlowCyclists.setTranslateY(App.heightOfScreen / 5);

		// System.out.println(text_flow.getBoundsInLocal().getWidth());

		// create VBox
		VBox vbox = new VBox(textFlowCyclists);
		vbox.setAlignment(Pos.CENTER);

		// Instantiating the Shadow class
		DropShadow dropShadow2 = new DropShadow();

		// setting the type of blur for the shadow
		dropShadow2.setBlurType(BlurType.GAUSSIAN);
		dropShadow2.setRadius(30);
		dropShadow2.setOffsetX(5);
		dropShadow2.setOffsetY(5);
		dropShadow2.setColor(Color.grayRgb(0, 0.4));

		int arc = 90;

		Rectangle rectangleOne = new Rectangle();
		rectangleOne.setWidth(rectWidth);
		rectangleOne.setHeight(rectWidth);
		rectangleOne.setArcWidth(arc);
		rectangleOne.setArcHeight(arc);
		rectangleOne.setFill(Color.web("#4AAC8D"));
		rectangleOne.setEffect(dropShadow2);

		Rectangle rectangleTwo = new Rectangle();
		rectangleTwo.setWidth(rectWidth);
		rectangleTwo.setHeight(rectWidth);
		rectangleTwo.setArcWidth(arc);
		rectangleTwo.setArcHeight(arc);
		rectangleTwo.setFill(Color.web("#4AAC8D"));
		rectangleTwo.setEffect(dropShadow2);

		Text rightText = new Text("Recent");
		rightText.setFill(Color.web("#AFEDD9"));
		rightText.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 35));

		TextFlow text_flow2 = new TextFlow();

		String breaker = "\n";

		user1 = new Text(Rankings.recent.poll() + breaker);
		user1.setFill(Color.web("#AFEDD9"));
		user1.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		user2 = new Text(Rankings.recent.poll() + breaker);
		user2.setFill(Color.web("#AFEDD9"));
		user2.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		user3 = new Text(Rankings.recent.poll() + breaker);
		user3.setFill(Color.web("#AFEDD9"));
		user3.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		user4 = new Text(Rankings.recent.poll() + breaker);
		user4.setFill(Color.web("#AFEDD9"));
		user4.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		user5 = new Text(Rankings.recent.poll() + breaker);
		user5.setFill(Color.web("#AFEDD9"));
		user5.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));

		text_flow2.getChildren().add(user1);
		text_flow2.getChildren().add(user2);
		text_flow2.getChildren().add(user3);
		text_flow2.getChildren().add(user4);
		text_flow2.getChildren().add(user5);
		text_flow2.setLineSpacing(10);
		text_flow2.setLayoutY(200);
		text_flow2.setTextAlignment(TextAlignment.CENTER);

		StackPane stackRight = new StackPane();
		stackRight.setMargin(text_flow2, new Insets(textFlowMargin, 0, 0, 0));
		stackRight.setMargin(rightText, new Insets(0, 0, rankingTitleMargin, 0));
		stackRight.getChildren().addAll(rectangleTwo, rightText, text_flow2);
		stackRight.setLayoutX((App.widthOfScreen / 3) * 2 - stackRight.getBoundsInLocal().getWidth() / 2);
		stackRight.setLayoutY((App.heightOfScreen / 5) * 2.75);

		Text leftText = new Text("Weekly");
		leftText.setFill(Color.web("#AFEDD9"));
		leftText.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 35));

		TextFlow text_flow3 = new TextFlow();

		weeklyUser1points = new Text("null");
		weeklyUser1points.setFill(Color.web("#AFEDD9"));
		weeklyUser1points.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 20));
		weeklyUser1points.setTextAlignment(TextAlignment.RIGHT);
		text_flow3.getChildren().add(weeklyUser1points);
		weeklyUser1 = new Text("null" + breaker);
		weeklyUser1.setFill(Color.web("#AFEDD9"));
		weeklyUser1.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		weeklyUser1.setTextAlignment(TextAlignment.RIGHT);
		text_flow3.getChildren().add(weeklyUser1);
		
		
		weeklyUser2points = new Text("null");
		weeklyUser2points.setFill(Color.web("#AFEDD9"));
		weeklyUser2points.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 20));
		weeklyUser2points.setTextAlignment(TextAlignment.RIGHT);
		text_flow3.getChildren().add(weeklyUser2points);
		weeklyUser2 = new Text("null" + breaker);
		weeklyUser2.setFill(Color.web("#AFEDD9"));
		weeklyUser2.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		text_flow3.getChildren().add(weeklyUser2);
		
		weeklyUser3points = new Text("null");
		weeklyUser3points.setFill(Color.web("#AFEDD9"));
		weeklyUser3points.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 20));
		weeklyUser3points.setTextAlignment(TextAlignment.RIGHT);
		text_flow3.getChildren().add(weeklyUser3points);
		weeklyUser3 = new Text("null" + breaker);
		weeklyUser3.setFill(Color.web("#AFEDD9"));
		weeklyUser3.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		text_flow3.getChildren().add(weeklyUser3);
		
		weeklyUser4points = new Text("null");
		weeklyUser4points.setFill(Color.web("#AFEDD9"));
		weeklyUser4points.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 20));
		weeklyUser4points.setTextAlignment(TextAlignment.RIGHT);
		text_flow3.getChildren().add(weeklyUser4points);
		weeklyUser4 = new Text("null" + breaker);
		weeklyUser4.setFill(Color.web("#AFEDD9"));
		weeklyUser4.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		text_flow3.getChildren().add(weeklyUser4);
		
		weeklyUser5points = new Text("null");
		weeklyUser5points.setFill(Color.web("#AFEDD9"));
		weeklyUser5points.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 20));
		weeklyUser5points.setTextAlignment(TextAlignment.RIGHT);
		text_flow3.getChildren().add(weeklyUser5points);
		weeklyUser5 = new Text("null");
		weeklyUser5.setFill(Color.web("#AFEDD9"));
		weeklyUser5.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		text_flow3.getChildren().add(weeklyUser5);
		
		text_flow3.setLineSpacing(10);
		text_flow3.setLayoutY(200);
		text_flow3.setTextAlignment(TextAlignment.LEFT);

		StackPane stackLeft = new StackPane();
		stackLeft.setMargin(text_flow3, new Insets(textFlowMargin, 0, 0, 50));
		stackLeft.setMargin(leftText, new Insets(0, 0, rankingTitleMargin, 0));
		stackLeft.getChildren().addAll(rectangleOne, leftText, text_flow3);
		stackLeft.setLayoutX((App.widthOfScreen / 3) * 1.1 - stackLeft.getBoundsInLocal().getWidth() / 2);
		stackLeft.setLayoutY((App.heightOfScreen / 5) * 2.75);

		// Creating an image

		Image image = new Image(new FileInputStream("assets/cyclistBackground.png"));

		// Setting the image view
		ImageView imageView = new ImageView(image);

		// Setting the position of the image
		imageView.setX(0);
		imageView.setY(0);

		// setting the fit height and width of the image view
		imageView.setFitHeight(App.heightOfScreen);
		imageView.setFitWidth(App.widthOfScreen);

		// Setting the preserve ratio of the image view
		imageView.setPreserveRatio(false);

		groupCyclists = new Group(imageView, vbox, textLight, stackRight, stackLeft);

		groupCyclists.setVisible(true);

	}

	public static void sceneFoodsharing() throws FileNotFoundException {

		Text textLight = new Text("Get involved at foodsharing.de");
		textLight.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 70));
		textLight.setFill(Color.web("#AFEDD9"));
		textLight.setTextAlignment(TextAlignment.CENTER);
		textLight.setX((App.widthOfScreen - textLight.getBoundsInLocal().getWidth()) / 2);
		textLight.setY(App.heightOfScreen / 2.5);

		// create TextFlow
		textFlowFoodsharing = new TextFlow();

		// create text
		Text text_1 = new Text("Foodsharing has saved ");
		text_1.setFill(Color.web("#393C3A"));
		text_1.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 50));

		// create text
		foodsharingCount = new Text(InfoPanel.foodsharingCount + " kg");
		foodsharingCount.setFill(Color.web("#393C3A"));
		foodsharingCount.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 50));

		Text text_3 = new Text(" of food!");
		text_3.setFill(Color.web("#393C3A"));
		text_3.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 50));

		// add text to textflow
		textFlowFoodsharing.getChildren().add(text_1);
		textFlowFoodsharing.getChildren().add(foodsharingCount);
		textFlowFoodsharing.getChildren().add(text_3);

		// set text Alignment
		textFlowFoodsharing.setTextAlignment(TextAlignment.CENTER);
		textFlowFoodsharing
				.setTranslateX(App.widthOfScreen / 2 - textFlowFoodsharing.getBoundsInLocal().getWidth() / 2);
		textFlowFoodsharing.setTranslateY(App.heightOfScreen / 5);

		// create VBox
		VBox vbox = new VBox(textFlowFoodsharing);
		vbox.setAlignment(Pos.CENTER);

		// Instantiating the Shadow class
		DropShadow dropShadow2 = new DropShadow();

		// setting the type of blur for the shadow
		dropShadow2.setBlurType(BlurType.GAUSSIAN);
		dropShadow2.setRadius(30);
		dropShadow2.setOffsetX(5);
		dropShadow2.setOffsetY(5);
		dropShadow2.setColor(Color.grayRgb(0, 0.4));

		int arc = 90;

		Rectangle rectangleOne = new Rectangle();
		rectangleOne.setWidth(rectWidth);
		rectangleOne.setHeight(rectWidth);
		rectangleOne.setArcWidth(arc);
		rectangleOne.setArcHeight(arc);
		rectangleOne.setFill(Color.web("#4AAC8D"));
		rectangleOne.setEffect(dropShadow2);

		Rectangle rectangleTwo = new Rectangle();
		rectangleTwo.setWidth(rectWidth);
		rectangleTwo.setHeight(rectWidth);
		rectangleTwo.setArcWidth(arc);
		rectangleTwo.setArcHeight(arc);
		rectangleTwo.setFill(Color.web("#4AAC8D"));
		rectangleTwo.setEffect(dropShadow2);

		Text rightText = new Text("Recent");
		rightText.setFill(Color.web("#AFEDD9"));
		rightText.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 35));

		TextFlow text_flow2 = new TextFlow();

		String breaker = "\n";

		user6 = new Text(Rankings.recent.poll() + breaker);
		user6.setFill(Color.web("#AFEDD9"));
		user6.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		user7 = new Text(Rankings.recent.poll() + breaker);
		user7.setFill(Color.web("#AFEDD9"));
		user7.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		user8 = new Text(Rankings.recent.poll() + breaker);
		user8.setFill(Color.web("#AFEDD9"));
		user8.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		user9 = new Text(Rankings.recent.poll() + breaker);
		user9.setFill(Color.web("#AFEDD9"));
		user9.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		user10 = new Text(Rankings.recent.poll() + breaker);
		user10.setFill(Color.web("#AFEDD9"));
		user10.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));

		text_flow2.getChildren().add(user6);
		text_flow2.getChildren().add(user7);
		text_flow2.getChildren().add(user8);
		text_flow2.getChildren().add(user9);
		text_flow2.getChildren().add(user10);
		text_flow2.setLineSpacing(10);
		text_flow2.setLayoutY(200);
		text_flow2.setTextAlignment(TextAlignment.CENTER);

		StackPane stackRight = new StackPane();
		stackRight.setMargin(text_flow2, new Insets(textFlowMargin, 0, 0, 0));
		stackRight.setMargin(rightText, new Insets(0, 0, rankingTitleMargin, 0));
		stackRight.getChildren().addAll(rectangleTwo, rightText, text_flow2);
		stackRight.setLayoutX((App.widthOfScreen / 3) * 2 - stackRight.getBoundsInLocal().getWidth() / 2);
		stackRight.setLayoutY((App.heightOfScreen / 5) * 2.75);

		Text leftText = new Text("Best of");
		leftText.setFill(Color.web("#AFEDD9"));
		leftText.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 35));

		TextFlow text_flow3 = new TextFlow();

		weeklyUser6points = new Text("null");
		weeklyUser6points.setFill(Color.web("#AFEDD9"));
		weeklyUser6points.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 20));
		weeklyUser6points.setTextAlignment(TextAlignment.RIGHT);
		text_flow3.getChildren().add(weeklyUser6points);
		weeklyUser6 = new Text("null" + breaker);
		weeklyUser6.setFill(Color.web("#AFEDD9"));
		weeklyUser6.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		weeklyUser6.setTextAlignment(TextAlignment.RIGHT);
		text_flow3.getChildren().add(weeklyUser6);
		
		
		weeklyUser7points = new Text("null");
		weeklyUser7points.setFill(Color.web("#AFEDD9"));
		weeklyUser7points.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 20));
		weeklyUser7points.setTextAlignment(TextAlignment.RIGHT);
		text_flow3.getChildren().add(weeklyUser7points);
		weeklyUser7 = new Text("null" + breaker);
		weeklyUser7.setFill(Color.web("#AFEDD9"));
		weeklyUser7.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		text_flow3.getChildren().add(weeklyUser7);
		
		weeklyUser8points = new Text("null");
		weeklyUser8points.setFill(Color.web("#AFEDD9"));
		weeklyUser8points.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 20));
		weeklyUser8points.setTextAlignment(TextAlignment.RIGHT);
		text_flow3.getChildren().add(weeklyUser8points);
		weeklyUser8 = new Text("null" + breaker);
		weeklyUser8.setFill(Color.web("#AFEDD9"));
		weeklyUser8.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		text_flow3.getChildren().add(weeklyUser8);
		
		weeklyUser9points = new Text("null");
		weeklyUser9points.setFill(Color.web("#AFEDD9"));
		weeklyUser9points.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 20));
		weeklyUser9points.setTextAlignment(TextAlignment.RIGHT);
		text_flow3.getChildren().add(weeklyUser9points);
		weeklyUser9 = new Text("null" + breaker);
		weeklyUser9.setFill(Color.web("#AFEDD9"));
		weeklyUser9.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		text_flow3.getChildren().add(weeklyUser9);
		
		weeklyUser10points = new Text("null");
		weeklyUser10points.setFill(Color.web("#AFEDD9"));
		weeklyUser10points.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 20));
		weeklyUser10points.setTextAlignment(TextAlignment.RIGHT);
		text_flow3.getChildren().add(weeklyUser10points);
		weeklyUser10 = new Text("null");
		weeklyUser10.setFill(Color.web("#AFEDD9"));
		weeklyUser10.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 20));
		text_flow3.getChildren().add(weeklyUser10);
		
		text_flow3.setLineSpacing(10);
		text_flow3.setLayoutY(200);
		text_flow3.setTextAlignment(TextAlignment.LEFT);


		StackPane stackLeft = new StackPane();
		stackLeft.setMargin(text_flow3, new Insets(textFlowMargin, 0, 0, 50));
		stackLeft.setMargin(leftText, new Insets(0, 0, rankingTitleMargin, 0));
		stackLeft.getChildren().addAll(rectangleOne, leftText, text_flow3);
		stackLeft.setLayoutX((App.widthOfScreen / 3) * 1.1 - stackLeft.getBoundsInLocal().getWidth() / 2);
		stackLeft.setLayoutY((App.heightOfScreen / 5) * 2.75);

		// Creating an image
		Image image = new Image(new FileInputStream("assets/foodsharingBackground.png"));

		// Setting the image view
		ImageView imageView = new ImageView(image);

		// Setting the position of the image
		imageView.setX(0);
		imageView.setY(0);

		// setting the fit height and width of the image view
		imageView.setFitHeight(App.heightOfScreen);
		imageView.setFitWidth(App.widthOfScreen);

		// Setting the preserve ratio of the image view
		imageView.setPreserveRatio(false);

		groupFoodsharing = new Group(imageView, vbox, textLight, stackRight, stackLeft);

		groupFoodsharing.setVisible(true);
	}

	public static void sceneNewUser() {

		Rectangle rectangleBackground = new Rectangle(0, 0, App.widthOfScreen, App.heightOfScreen);
		rectangleBackground.setFill(Color.web("#AFEDD9"));

		Text textDark = new Text("Welcome to FuBi-Intelligence\nyour trainer-nickname is");
		textDark.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 70));
		textDark.setFill(Color.web("#393C3A"));
		textDark.setTextAlignment(TextAlignment.CENTER);
		textDark.setX((App.widthOfScreen - textDark.getBoundsInLocal().getWidth()) / 2);
		textDark.setY(App.heightOfScreen / 4);
		textDark.setLineSpacing(50);

		// Instantiating the Shadow class
		DropShadow dropShadow2 = new DropShadow();

		// setting the type of blur for the shadow
		dropShadow2.setBlurType(BlurType.GAUSSIAN);
		dropShadow2.setRadius(30);
		dropShadow2.setOffsetX(5);
		dropShadow2.setOffsetY(5);
		dropShadow2.setColor(Color.grayRgb(0, 0.4));

		int rectWidth = App.widthOfScreen / 2;
		int arc = 100;

		nickname = new Text();
		nickname.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 50));
		nickname.setFill(Color.web("#AFEDD9"));

		Rectangle rectangleNickname = new Rectangle();
		rectangleNickname.setWidth(rectWidth);
		rectangleNickname.setHeight(nickname.getBoundsInLocal().getHeight() + 50);
		rectangleNickname.setArcWidth(arc);
		rectangleNickname.setArcHeight(arc);
		rectangleNickname.setFill(Color.web("#393C3A"));
		rectangleNickname.setEffect(dropShadow2);

		StackPane stackNickname = new StackPane();
		stackNickname.getChildren().addAll(rectangleNickname, nickname);
		stackNickname.setLayoutX((App.widthOfScreen / 2) - stackNickname.getBoundsInLocal().getWidth() / 2);
		;
		stackNickname.setLayoutY(App.heightOfScreen / 5 * 3);

		groupNewUser = new Group(rectangleBackground, textDark, stackNickname);

		groupNewUser.setVisible(false);

	}

	public static void sceneKnownUser() {

		Rectangle rectangleBackground = new Rectangle(0, 0, App.widthOfScreen, App.heightOfScreen);
		rectangleBackground.setFill(Color.web("#AFEDD9"));

		textDarkBold = new Text("Welcome back trainer " + "\nKeep it up!");
		textDarkBold.setLineSpacing(App.heightOfScreen * 0.5);
		textDarkBold.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 70));
		textDarkBold.setFill(Color.web("#393C3A"));
		textDarkBold.setTextAlignment(TextAlignment.CENTER);
		textDarkBold.setX((App.widthOfScreen - textDarkBold.getBoundsInLocal().getWidth()) / 2);
		textDarkBold.setY(App.heightOfScreen / 4);

		Text textDarkLight = new Text("Your current FuBi-AI Training score is");
		textDarkLight.setFont(Font.font("DIN 2014", FontWeight.LIGHT, 30));
		textDarkLight.setFill(Color.web("#393C3A"));
		textDarkLight.setTextAlignment(TextAlignment.CENTER);
		textDarkLight.setX((App.widthOfScreen - textDarkLight.getBoundsInLocal().getWidth()) / 2);
		textDarkLight.setY(App.heightOfScreen / 4 + 100);

		// Instantiating the Shadow class
		DropShadow dropShadow2 = new DropShadow();

		// setting the type of blur for the shadow
		dropShadow2.setBlurType(BlurType.GAUSSIAN);
		dropShadow2.setRadius(30);
		dropShadow2.setOffsetX(5);
		dropShadow2.setOffsetY(5);
		dropShadow2.setColor(Color.grayRgb(0, 0.4));

		score = new Text("12");
		score.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 100));
		score.setFill(Color.web("#AFEDD9"));

		int rectWidth = 200;
		int arc = 90;

		Rectangle rectangleScore = new Rectangle();
		rectangleScore.setWidth(rectWidth);
		rectangleScore.setHeight(rectWidth);
		rectangleScore.setArcWidth(arc);
		rectangleScore.setArcHeight(arc);
		rectangleScore.setFill(Color.web("#4AAC8D"));
		rectangleScore.setEffect(dropShadow2);

		StackPane stackScore = new StackPane();
		stackScore.getChildren().addAll(rectangleScore, score);
		stackScore.setLayoutX((App.widthOfScreen / 2) - stackScore.getBoundsInParent().getWidth() / 2);
		;
		stackScore.setLayoutY(App.heightOfScreen / 5 * 2.3);

		groupKnownUser = new Group(rectangleBackground, textDarkBold, textDarkLight, stackScore);

		groupKnownUser.setVisible(false);

	}

	public static void sceneTrashPhoto() throws FileNotFoundException {

		Rectangle rectangleBackground = new Rectangle(0, 0, App.widthOfScreen, App.heightOfScreen);
		rectangleBackground.setFill(Color.web("#AFEDD9"));

		Text textDarkBold = new Text("Do you like your waste as I do?");
		textDarkBold.setLineSpacing(App.heightOfScreen * 0.5);
		textDarkBold.setFont(Font.font("DIN 2014", FontWeight.EXTRA_BOLD, 70));
		textDarkBold.setFill(Color.web("#393C3A"));
		textDarkBold.setTextAlignment(TextAlignment.CENTER);
		textDarkBold.setX((App.widthOfScreen - textDarkBold.getBoundsInLocal().getWidth()) / 2);
		textDarkBold.setY(App.heightOfScreen / 4);

		int rectWidth = 300;
		int arc = 90;

		// Image image = new Image(new FileInputStream("assets/image.png"));

		rectanglePhoto = new Rectangle();
		rectanglePhoto.setWidth(rectWidth);
		rectanglePhoto.setHeight(rectWidth);
		rectanglePhoto.setArcWidth(arc);
		rectanglePhoto.setArcHeight(arc);
		rectanglePhoto.setFill(Color.TRANSPARENT);

		Rectangle rectangleOutlineOne = new Rectangle();
		rectangleOutlineOne.setWidth(rectWidth + 70);
		rectangleOutlineOne.setHeight(rectWidth + 70);
		rectangleOutlineOne.setArcWidth(arc + 7);
		rectangleOutlineOne.setArcHeight(arc + 7);
		rectangleOutlineOne.setFill(Color.TRANSPARENT);
		rectangleOutlineOne.setStroke(Color.web("#4AAC8D"));
		rectangleOutlineOne.setStrokeWidth(10);

		Rectangle rectangleOutlineTwo = new Rectangle();
		rectangleOutlineTwo.setWidth(rectWidth + 30);
		rectangleOutlineTwo.setHeight(rectWidth + 30);
		rectangleOutlineTwo.setArcWidth(arc + 3);
		rectangleOutlineTwo.setArcHeight(arc + 3);
		rectangleOutlineTwo.setFill(Color.TRANSPARENT);
		rectangleOutlineTwo.setStroke(Color.web("#4AAC8D"));
		rectangleOutlineTwo.setStrokeWidth(5);

		StackPane stackScore = new StackPane();
		stackScore.getChildren().addAll(rectangleOutlineOne, rectangleOutlineTwo, rectanglePhoto);
		stackScore.setLayoutX((App.widthOfScreen / 2) - stackScore.getBoundsInParent().getWidth() / 2);
		;
		stackScore.setLayoutY(App.heightOfScreen / 3 * 1.2);

		groupTrashPhoto = new Group(rectangleBackground, textDarkBold, stackScore);

		groupTrashPhoto.setVisible(false);

	}
}
