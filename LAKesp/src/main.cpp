#include <Arduino.h>
#include "MyBLEDevice/MyBLEDevice.h"



BLEServer* pServer = nullptr;
BLEAdvertising* pAdvertising = nullptr;
MyBLEDevice device;
void setup() {
  
  Serial.begin(115200);
  device.initBLE("TestausLaite32");
}

void loop() {
  delay(1000);
  device.loop();
}