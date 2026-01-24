#include "MyIRTransmitter.h"

MyIRTransmitter::MyIRTransmitter() {
    
}
MyIRTransmitter::~MyIRTransmitter() {
    
}
const uint16_t IR_PIN = 19;
IRsend irsend(IR_PIN);


void MyIRTransmitter::sendIRCommand(String protokolla, uint64_t address, uint64_t data, uint16_t nbits) {

    if(!init_done){
        init();
    }
    
    if(protokolla == "NEC") {
       
        irsend.sendNEC(data, nbits);
        Serial.println("Sent NEC command: " + String(data, HEX));
    }


    
    
}
void MyIRTransmitter::sendRawIR(const uint16_t* rawdata) {

    if(!init_done){
        init();
    }
    
    const uint16_t kRawDataLen = 67; // Esimerkkiarvo, muuta tarpeen mukaan
    irsend.sendRaw(rawdata, kRawDataLen, 38); // 38 kHz taajuus
    Serial.println("Raw IR data sent");
}

void MyIRTransmitter::init(){
    irsend.begin();
    init_done = true;
}