#include <Arduino.h>
#include "MyBLEDevice.h"



BLEServer* pServer = nullptr;
BLEAdvertising* pAdvertising = nullptr;

void setup() {
  MyBLEDevice device;
  Serial.begin(115200);
  device.initBLE("TestausLaite32");
}

void loop() {
  
  delay(1000);
}