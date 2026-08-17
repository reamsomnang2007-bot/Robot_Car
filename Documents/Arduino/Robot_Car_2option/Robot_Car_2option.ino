#include <AFMotor.h>
#include <Servo.h>

AF_DCMotor M1(1);
AF_DCMotor M2(2);
AF_DCMotor M3(3);
AF_DCMotor M4(4);

Servo servo;

#define LEFT_IR A2
#define RIGHT_IR A3

#define TRIG A0
#define ECHO A1

#define BUTTON A4

int distanceCenter;
int distanceLeft;
int distanceRight;

int mode = 0;
int lastButtonState = HIGH;

void setup() {
  Serial.begin(9600);

  pinMode(LEFT_IR, INPUT);
  pinMode(RIGHT_IR, INPUT);

  pinMode(TRIG, OUTPUT);
  pinMode(ECHO, INPUT);

  pinMode(BUTTON, INPUT_PULLUP);

  servo.attach(10);
  servo.writeMicroseconds(1500);

  M1.setSpeed(150);
  M2.setSpeed(150);
  M3.setSpeed(150);
  M4.setSpeed(150);

  stopRobot();
  delay(1000);

  Serial.println("Option 1: Obstacle Avoidance");
}

void loop() {
  checkButton();

  if(mode == 0) {
    obstacleAvoidance();
  }
  else {
    followLine();
  }

  delay(10);
}

void checkButton() {
  int buttonState = digitalRead(BUTTON);

  if(lastButtonState == HIGH && buttonState == LOW) {
    mode = !mode;

    stopRobot();
    delay(200);

    if(mode == 0) {
      Serial.println("Option 1: Obstacle Avoidance");
    }
    else {
      Serial.println("Option 2: Line Following");
    }
  }

  lastButtonState = buttonState;
}

void obstacleAvoidance() {
  servo.writeMicroseconds(1500);
  delay(50);

  distanceCenter = getDistance();

  Serial.print("Center: ");
  Serial.println(distanceCenter);

  if(distanceCenter > 30) {
    forward();
  }
  else {
    avoidObstacle();
  }
}

void avoidObstacle() {
  stopRobot();
  delay(300);

  backward();
  delay(500);

  stopRobot();
  delay(200);

  servo.writeMicroseconds(2200);
  delay(500);

  distanceLeft = getDistance();

  Serial.print("Left: ");
  Serial.println(distanceLeft);

  servo.writeMicroseconds(800);
  delay(500);

  distanceRight = getDistance();

  Serial.print("Right: ");
  Serial.println(distanceRight);

  servo.writeMicroseconds(1500);
  delay(300);

  if(distanceLeft > distanceRight) {
    turnLeft();
    delay(500);
  }
  else {
    turnRight();
    delay(500);
  }

  stopRobot();
  delay(200);
}

void followLine() {
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
    lineTurnLeft();
  }
  else if(leftSensor == LOW && rightSensor == HIGH) {
    lineTurnRight();
  }
  else {
    stopRobot();
  }
}

int getDistance() {
  digitalWrite(TRIG, LOW);
  delayMicroseconds(2);

  digitalWrite(TRIG, HIGH);
  delayMicroseconds(10);

  digitalWrite(TRIG, LOW);

  long duration = pulseIn(ECHO, HIGH, 30000);

  if(duration == 0) {
    return 300;
  }

  return duration * 0.034 / 2;
}

void forward() {
  M1.run(FORWARD);
  M2.run(FORWARD);
  M3.run(FORWARD);
  M4.run(FORWARD);
}

void backward() {
  M1.run(BACKWARD);
  M2.run(BACKWARD);
  M3.run(BACKWARD);
  M4.run(BACKWARD);
}

void lineTurnLeft() {
  M1.run(RELEASE);
  M2.run(RELEASE);
  M3.run(FORWARD);
  M4.run(FORWARD);
}

void lineTurnRight() {
  M1.run(FORWARD);
  M2.run(FORWARD);
  M3.run(RELEASE);
  M4.run(RELEASE);
}

void turnLeft() {
  M1.run(BACKWARD);
  M2.run(BACKWARD);
  M3.run(FORWARD);
  M4.run(FORWARD);
}

void turnRight() {
  M1.run(FORWARD);
  M2.run(FORWARD);
  M3.run(BACKWARD);
  M4.run(BACKWARD);
}

void stopRobot() {
  M1.run(RELEASE);
  M2.run(RELEASE);
  M3.run(RELEASE);
  M4.run(RELEASE);
}