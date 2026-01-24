#ifndef MYBLEDEVICE_H
#define MYBLEDEVICE_H

#include <BLEDevice.h>
#include <BLEServer.h>
#include <BLEUtils.h>
#include <BLEAdvertising.h>
#include <iostream>
#include <BLE2902.h>

#define SERVICE_UUID        "4fafc201-1fb5-459e-8fcc-c5c9c331914e"
#define CHARACTERISTIC_UUID "beb5483e-36e1-4688-b7f5-ea07361b26a8"
#define AUTH_UUID    "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"

class CommandCallbacks;  

class MyBLEDevice
{
private:
    
    BLEServer* pServer = nullptr;
    BLECharacteristic* authChar = nullptr;
    BLECharacteristic* cmdChar = nullptr;
    bool authenticated = false;
    CommandCallbacks* cmdCallback = nullptr;  
    CommandCallbacks* authCallback = nullptr; 
    
    
public:
    MyBLEDevice(/* args */);
    ~MyBLEDevice();

    void initBLE(std::string deviceName);
    bool sendCommand(const char* command);
    bool isAuthenticated();
    uint8_t value = 1;
  

    void loop();

    
};

#endif


