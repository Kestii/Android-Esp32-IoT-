#include <Arduino.h>
#include "MyBLEDevice/MyBLEDevice.h"
#include "MyIR/MyIRReceiver.h"

MyIRReceiver irReceiver;


BLEServer* pServer = nullptr;
BLEAdvertising* pAdvertising = nullptr;
MyBLEDevice device;

void setup() {
  
  Serial.begin(115200);
  device.initBLE("TestausLaite32");
  irReceiver.receiveIRSignal();
}

void loop() {
  irReceiver.loop();
  device.loop();
}