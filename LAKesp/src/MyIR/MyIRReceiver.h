#ifndef MYIRRECEIVER_H
#define MYIRRECEIVER_H
#include <Arduino.h>


class MyIRReceiver{
private:


public:
    MyIRReceiver();
    ~MyIRReceiver();
    void loop();
    void receiveIRSignal();
    std::string getAllProtocolsAsJSON();
};
#endif