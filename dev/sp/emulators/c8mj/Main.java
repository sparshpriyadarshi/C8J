package dev.sp.emulators.c8mj;

import java.time.LocalDateTime;


class Emulator{

}

class Rom{
    
}
public class Main{
    public static void main(String[] args) {
        System.out.println("Running C8MJ... | Current time = " + LocalDateTime.now());
        Emulator emu = new Emulator();
        System.out.println("Initialized: " + emu.toString());
        System.out.println("C8MJ exiting...");
    }
}