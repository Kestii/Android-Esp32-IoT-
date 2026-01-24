#ifndef MYIRTRANSMITTER_H
#define MYIRTRANSMITTER_H

#include <Arduino.h>
#include <IRremoteESP8266.h>
#include <IRsend.h>

class MyIRTransmitter {

private:
    IRsend irsend{4}; 
    bool init_done = false;
    
public:
    MyIRTransmitter();
    ~MyIRTransmitter();
    void sendIRCommand(String protokolla, uint64_t address, uint64_t data, uint16_t nbits);
    void sendRawIR(const uint16_t* rawdata);
    void init();
    
};

#endif