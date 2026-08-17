
#include <AFMotor.h>

AF_DCMotor M1(1);
AF_DCMotor M2(2);
AF_DCMotor M3(3);
AF_DCMotor M4(4);

#define LEFT_IR A2
#define RIGHT_IR A3

void setup() {
  Serial.begin(9600);

  pinMode(LEFT_IR, INPUT);
  pinMode(RIGHT_IR, INPUT);

  M1.setSpeed(110);
  M2.setSpeed(110);
  M3.setSpeed(110);
  M4.setSpeed(110);

  stopRobot();
  delay(1000);
}

void loop() {
  int leftSensor = digitalRead(LEFT_IR);
  int rightSensor = digitalRead(RIGHT_IR);

  Serial.print("Left: ");
  Serial.print(leftSensor);
  Serial.print(" Right: ");
  Serial.println(rightSensor);

  if(leftSensor == LOW && rightSensor == LOW) {
    forward();
  }
  else if(leftSensor == HIGH && rightSensor == LOW) {
    turnLeft();
  }
  else if(leftSensor == LOW && rightSensor == HIGH) {
    turnRight();
  }
  else {
    stopRobot();
  }

  delay(10);
}

void forward() {
  M1.run(FORWARD);
  M2.run(FORWARD);
  M3.run(FORWARD);
  M4.run(FORWARD);
}

void turnLeft() {
  M1.run(RELEASE);
  M2.run(RELEASE);
  M3.run(FORWARD);
  M4.run(FORWARD);
}

void turnRight() {
  M1.run(FORWARD);
  M2.run(FORWARD);
  M3.run(RELEASE);
  M4.run(RELEASE);
}

void stopRobot() {
  M1.run(RELEASE);
  M2.run(RELEASE);
  M3.run(RELEASE);
  M4.run(RELEASE);
}