#include <AFMotor.h>
#include <Servo.h>
// Motors
AF_DCMotor M1(1);
AF_DCMotor M2(2);
AF_DCMotor M3(3);
AF_DCMotor M4(4);
// Servo
Servo servo;
// Ultrasonic
# define TRIG A0
# define ECHO A1

int distanceCenter;
int distanceLeft;
int distanceRight;


void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(TRIG, OUTPUT);
  pinMode(ECHO, INPUT);

  servo.attach(10);
  servo.write(1500);

  M1.setSpeed(150);
  M2.setSpeed(150);
  M3.setSpeed(150);
  M4.setSpeed(150);


  stopRobot();
  delay(1000);

}

void loop() {
  // put your main code here, to run repeatedly:
  servo.write(1500);
  delay(300);

  distanceCenter = getDistance();

  Serial.print("Center: ");
  Serial.println(distanceCenter);

  if(distanceCenter > 30){
    forward();
  }
  else{
    avoidObstacle();
  }
}

void avoidObstacle(){
  stopRobot();
  delay(300);

  backward();
  delay(500);

  stopRobot();
  delay(200);

  // Look Left
  servo.write(2200);
  delay(500);
  distanceLeft = getDistance();

  Serial.print("Letf: ");
  Serial.println(distanceLeft);

  // Look Right
  servo.write(800);
  delay(500);
  distanceRight = getDistance();

  Serial.print("Right: ");
  Serial.println(distanceRight);
  
  // Center
  servo.write(1500);
  delay(300);

  if(distanceLeft > distanceRight){
    turnLeft();
    delay(500);
  }
  else{
    turnRight();
    delay(500);
  }

  stopRobot();
  delay(200);

}

// Distance
int getDistance(){
  digitalWrite(TRIG, LOW);
  delayMicroseconds(2);

  digitalWrite(TRIG, HIGH);
  delayMicroseconds(10);

  digitalWrite(TRIG, LOW);

  long duration = pulseIn(ECHO, HIGH, 30000);

  if(duration == 0){
    return 300;
  }
  return duration * 0.034 / 2;
}

// FORWARD
void forward(){
  M1.run(FORWARD);
  M2.run(FORWARD);
  M3.run(FORWARD);
  M4.run(FORWARD);
}

// BACKWARD
void backward(){
  M1.run(BACKWARD);
  M2.run(BACKWARD);
  M3.run(BACKWARD);
  M4.run(BACKWARD);
}

// TURN LEFT
void turnLeft(){
  M1.run(BACKWARD);
  M2.run(BACKWARD);
  M3.run(FORWARD);
  M4.run(FORWARD);
}

// TURN RIGHT
void turnRight(){
  M1.run(FORWARD);
  M2.run(FORWARD);
  M3.run(BACKWARD);
  M4.run(BACKWARD);
}

// STOP-ROBOT
void stopRobot(){
  M1.run(RELEASE);
  M2.run(RELEASE);
  M3.run(RELEASE);
  M4.run(RELEASE);
}