#include "MyBLEDevice.h"
#include <Arduino.h>

const std::string PASSWORD = "securepassword";

class AuthCallbacks : public BLECharacteristicCallbacks {
    MyBLEDevice* device;

public:
    AuthCallbacks(MyBLEDevice* d) : device(d) {}

    void onWrite(BLECharacteristic* pChar) override {
        std::string value = pChar->getValue();
        if (value == PASSWORD) {
            device->authenticate(true);
            Serial.println("Salasana oikein!");
        } else {
            Serial.println("Väärä salasana! Katkaistaan yhteys.");
        }
    }
};

class CommandCallbacks : public BLECharacteristicCallbacks {
    MyBLEDevice* device;

public:
    CommandCallbacks(MyBLEDevice* d) : device(d) {}

    void onWrite(BLECharacteristic* pChar) override {
        if (!device->isAuthenticated()) {
            Serial.println("Ei autentikoitu, komento hylätty!");
            return;
        }

        std::string cmd = pChar->getValue();
        Serial.print("Komento vastaanotettu: ");
        Serial.println(cmd.c_str());
    }
};

MyBLEDevice::MyBLEDevice() {}

MyBLEDevice::~MyBLEDevice() {}

void MyBLEDevice::initBLE(std::string deviceName) {
    BLEDevice::init(deviceName);

    pServer = BLEDevice::createServer();

    BLEService* pService = pServer->createService(SERVICE_UUID);

  
    authChar = pService->createCharacteristic(
        AUTH_UUID,
        BLECharacteristic::PROPERTY_WRITE |   
        BLECharacteristic::PROPERTY_READ     
    );

    authChar->setValue("0");                  
    authChar->setCallbacks(new AuthCallbacks(this));

    cmdChar = pService->createCharacteristic(
        CHARACTERISTIC_UUID,
        BLECharacteristic::PROPERTY_WRITE |
        BLECharacteristic::PROPERTY_READ     
    );

    cmdChar->setValue("");                    
    cmdChar->setCallbacks(new CommandCallbacks(this));

    
    pService->start();

    BLEAdvertising* pAdvertising = BLEDevice::getAdvertising();
    pAdvertising->addServiceUUID(SERVICE_UUID);   
    pAdvertising->setScanResponse(true);
    BLEDevice::startAdvertising();

    Serial.println("Started advertising");
}

bool MyBLEDevice::sendCommand(std::string command) {
    return true;
}

void MyBLEDevice::authenticate(bool value) {
    authenticated = value;
}

bool MyBLEDevice::isAuthenticated() {
    return authenticated;
}
