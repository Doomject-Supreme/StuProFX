#include <SPI.h> // SPI-Bibiothek hinzufügen

#include <MFRC522.h> // RFID-Bibiothek hinzufügen

#define SS_PIN 53 // SDA an Pin 10 (bei MEGA anders)

#define RST_PIN 5 // RST an Pin 9 (bei MEGA anders)

MFRC522 mfrc522(SS_PIN, RST_PIN); // RFID-Empfänger benennen

#define echoPin 10 // attach pin D2 Arduino to pin Echo of HC-SR04
#define trigPin 9 //attach pin D3 Arduino to pin Trig of HC-SR04

// defines variables
long duration; // variable for the duration of sound wave travel
int distance; // variable for the distance measurement
int LEFT = 2 ; // define the Hall magnetic sensor
boolean left = false;
int MID = 3;
boolean mid = false;
int RIGHT = 4;
boolean right = false;
int pirPin = 44;
int pirStat = 0;
int val ; // define numeric variables 
int cameraLEDs = 33;

void setup() // Beginn des Setups:

{

Serial.begin(9600); // Serielle Verbindung starten (Monitor)

SPI.begin(); // SPI-Verbindung aufbauen

mfrc522.PCD_Init(); // Initialisierung des RFID-Empfängers

pinMode (LEFT, INPUT) ;  // define the Hall magnetic sensor line as input
pinMode (MID, INPUT);
pinMode(RIGHT, INPUT);
pinMode(pirPin, INPUT);
pinMode(trigPin, OUTPUT); // Sets the trigPin as an OUTPUT
pinMode(echoPin, INPUT); // Sets the echoPin as an INPUT
pinMode(cameraLEDs, OUTPUT);
}



void loop() // Hier beginnt der Loop-Teil

{


//FUKING ULTRASOUND
   // Clears the trigPin condition
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  // Sets the trigPin HIGH (ACTIVE) for 10 microseconds
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  // Reads the echoPin, returns the sound wave travel time in microseconds
  duration = pulseIn(echoPin, HIGH);
  // Calculating the distance
  distance = duration * 0.034 / 2; // Speed of sound wave divided by 2 (go and back)
  // Displays the distance on the Serial Monitor

if (distance < 6 && distance > 0) {
 Serial.println("BUTTON");
 digitalWrite(cameraLEDs, HIGH);
 delay(3000);
 digitalWrite(cameraLEDs, LOW);
}
 
  delay(100);


//HALL GROUP  
  val = digitalRead (LEFT) ; // read sensor line
  if (val == HIGH) {
    Serial.println("openedLEFT");
    left = false;
  } else {
      left = true;
  }
  
  val = digitalRead (MID) ; // read sensor line
  if (val == HIGH) {
    Serial.println("openedRIGHT");
    mid = false;
  } else {
      mid = true;
  }
  
  val = digitalRead (RIGHT) ; // read sensor line
  if (val == HIGH) {
    Serial.println("openedMID");
    right = false;
  } else {
      right = true;
  }

//MOVEMENT SENSOR
  pirStat = digitalRead(pirPin); 
 if (pirStat == HIGH) {            // if motion detected
  // digitalWrite(ledOne, HIGH);  // turn ledOne ON
   Serial.println("MOVE");
 } 

 //RFID CARD READER
if ( ! mfrc522.PICC_IsNewCardPresent()) // Wenn keine Karte in Reichweite ist...

{
return; // ...springt das Programm zurück vor die if-Schleife, womit sich die Abfrage wiederholt.
}

if ( ! mfrc522.PICC_ReadCardSerial()) // Wenn kein RFID-Sender ausgewählt wurde

{
  
return; // ...springt das Programm zurück vor die if-Schleife, womit sich die Abfrage wiederholt.

}

Serial.print("RFID="); // "Die ID des RFID-TAGS lautet:" wird auf den Serial Monitor geschrieben.

for (byte i = 0; i < mfrc522.uid.size; i++) {
  int id = mfrc522.uid.uidByte[i];

  Serial.print(id);  // Dann wird die UID ausgelesen, die aus vier einzelnen Blöcken besteht und der Reihe nach an den Serial Monitor gesendet.
}

Serial.println(); // Mit dieser Zeile wird auf dem Serial Monitor nur ein Zeilenumbruch gemacht.

}
