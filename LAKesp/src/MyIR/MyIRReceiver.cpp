#include "MyIRReceiver.h"
#include <IRremoteESP8266.h>
#include <IRrecv.h>
#include <IRutils.h>  


MyIRReceiver::MyIRReceiver() {
    
}
MyIRReceiver::~MyIRReceiver() {    
}

const int IR_RECEIVE_PIN = 16;
IRrecv irrecv(IR_RECEIVE_PIN);
decode_results results; 




void MyIRReceiver::receiveIRSignal() {
    irrecv.enableIRIn();
}

void MyIRReceiver::loop() {
   if (irrecv.decode(&results)) {
        // Tallenna arvo
        uint32_t val = results.value;

        // Tarkista repeat
        if (val == 0xFFFFFFFF) Serial.println("Repeat code detected");
        else Serial.printf("Received: 0x%08X\n", val);

        // Tulosta raakapulssit
        Serial.print("Raw pulses: ");
        for (int i = 0; i < results.rawlen; i++) {
            Serial.print(results.rawbuf[i]);
            Serial.print(", ");
        }
        Serial.println();

        irrecv.resume(); // valmis seuraavaan signaaliin
   }
}
