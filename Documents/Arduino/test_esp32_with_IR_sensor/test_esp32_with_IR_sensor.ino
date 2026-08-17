#define IR_PIN 27

void setup() {
  Serial.begin(115200);
  pinMode(IR_PIN, INPUT);
}

void loop() {
  int sensorState = digitalRead(IR_PIN);

  if (sensorState == LOW) {
    Serial.println("Object detected");
  } else {
    Serial.println("No object");
  }

  delay(200);
}