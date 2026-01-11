#ifndef MYBLEDEVICE_H
#define MYBLEDEVICE_H

#include <BLEDevice.h>
#include <BLEServer.h>
#include <BLEUtils.h>
#include <BLEAdvertising.h>
#include <iostream>

#define SERVICE_UUID        "4fafc201-1fb5-459e-8fcc-c5c9c331914b"
#define CHARACTERISTIC_UUID "beb5483e-36e1-4688-b7f5-ea07361b26a8"
#define AUTH_UUID    "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"


class MyBLEDevice
{
private:
    
    BLEServer* pServer = nullptr;
    BLECharacteristic* authChar = nullptr;
    BLECharacteristic* cmdChar = nullptr;
    bool authenticated = false;
public:
    MyBLEDevice(/* args */);
    ~MyBLEDevice();

    void initBLE(std::string deviceName);
    bool sendCommand(std::string command);
    void authenticate(bool authvalue);
    bool isAuthenticated();
    
};

#endif


