#include "MyBLEDevice/MyBLEDevice.h"
#include <Arduino.h>
#include "MyIR/MyIRTransmitter.h"



MyIRTransmitter irTransmitter;

unsigned long lastIRsend = 0;
const unsigned long time_period = 3000;



class MyServerCallbacks : public BLEServerCallbacks {
    public:
    void onConnect(BLEServer* pServer) override {
        Serial.println("Client connected");
    }
    void onDisconnect(BLEServer* pServer) override {
        Serial.println("Client disconnected");
        pServer->getAdvertising()->start();
    }
};


// lukee kun tulee komento
class CommandCallbacks : public BLECharacteristicCallbacks {
    MyBLEDevice* device;
public:
    CommandCallbacks(MyBLEDevice* dev) :
    device(dev) 
    {}

    void onWrite(BLECharacteristic* pChar) override {
        std::string value = pChar->getValue();

        Serial.print("UUID: ");
        Serial.println(pChar->getUUID().toString().c_str());
        Serial.print("Data: ");
        Serial.print("Komento vastaanotettu: ");
        Serial.println(value.c_str());
        device->handle_command(
            pChar, 
            value
        );
    }
};

MyBLEDevice::MyBLEDevice() {}

MyBLEDevice::~MyBLEDevice() {}



void MyBLEDevice::initBLE(std::string deviceName) {

    BLEDevice::init(deviceName);
    pServer = BLEDevice::createServer();

    pServer->setCallbacks(new MyServerCallbacks());
    BLEService* pService = pServer->createService(SERVICE_UUID);

    CommandCallbacks* commonCallback = new CommandCallbacks(this);

    cmdChar = pService->createCharacteristic(
        CHARACTERISTIC_UUID,
        BLECharacteristic::PROPERTY_WRITE |
        BLECharacteristic::PROPERTY_READ |
        BLECharacteristic::PROPERTY_NOTIFY|
        BLECharacteristic::PROPERTY_INDICATE
    );   
    
    cmdChar->setCallbacks(commonCallback);
    cmdChar->addDescriptor(new BLE2902());

     authChar = pService->createCharacteristic(
        AUTH_UUID,
        BLECharacteristic::PROPERTY_WRITE |
        BLECharacteristic::PROPERTY_READ |
        BLECharacteristic::PROPERTY_NOTIFY
    );
    
    
    authChar->setCallbacks(commonCallback);
    authChar->addDescriptor(new BLE2902());


    pService->start();

    BLEAdvertising* pAdvertising = BLEDevice::getAdvertising();
    pAdvertising->addServiceUUID(SERVICE_UUID);   
    pAdvertising->setScanResponse(true);
    BLEDevice::startAdvertising();
    Serial.println("Started advertising");

    

}

bool MyBLEDevice::sendCommand(const char* command) {
    cmdChar->setValue(command);
    cmdChar->notify();
    return true;
}



bool MyBLEDevice::isAuthenticated() {
    return authenticated;
}
void MyBLEDevice::loop() {
    cmdChar->setValue((uint8_t*)&value, 1);
    cmdChar->notify();
    unsigned long now = millis();
    if(now-lastIRsend >= time_period) {
    irTransmitter.sendIRCommand("NEC",0, 0x20DF10EF, 32);  // koko data yhdellä arvolla
        lastIRsend = now;
        delay(120);
    }   
}

void MyBLEDevice::handle_command(BLECharacteristic* pChar, const std::string& command_value){
    if (1){

    }

}

