#include <AFMotor.h>

AF_DCMotor M1(1);
AF_DCMotor M2(2);
AF_DCMotor M3(3);
AF_DCMotor M4(4);



void setup() {
  // put your setup code here, to run once:
  M1.setSpeed(255);
  M2.setSpeed(255);
  M3.setSpeed(255);
  M4.setSpeed(255);
}

void loop() {
  // put your main code here, to run repeatedly:
  M1.run(FORWARD);
  M2.run(FORWARD);
  M3.run(FORWARD);
  M4.run(FORWARD);
}
