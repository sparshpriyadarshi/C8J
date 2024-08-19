
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HexFormat;
import java.util.List;

/**
 * 
 * A CHIP-8 emulator
 */
class Emulator {
    private static int MAX_ADDRESSIBLE_BYTES = 4096;// 0x1000
    private static int PROGRAM_MEM_BASE_IDX = 512;// 0x200
    private static int PROGRAM_MEM_LAST_IDX = 3743;// 0xE9F

    // This welcome screen program is always loaded by default
    public static String DEFAULT_PROGRAM_SRC_FILEPATH = "./resources/binaries/c8splash.ch8";

    private byte[] program; // Just a program, not in memory...
    private byte[] memory;
    private byte[] vRegs; // V registers
    private byte IReg; // the I register

    private void readCh8Binary(Path path) throws IOException {
        File binFile = new File(path.toUri());
        FileInputStream fileInputStream = new FileInputStream(binFile);
        program = fileInputStream.readAllBytes();
        fileInputStream.close();
    }

    public Emulator() throws IOException {
        // initialize empty registers V0 to VF, I
        vRegs = new byte[16];
        IReg = 0;

        // get hold of a "default" program to run
        readCh8Binary(Paths.get(DEFAULT_PROGRAM_SRC_FILEPATH));

        // initialize memory with welcome program
        memory = new byte[MAX_ADDRESSIBLE_BYTES];
        for (int idx = PROGRAM_MEM_BASE_IDX; idx < PROGRAM_MEM_BASE_IDX + program.length; idx++) {
            memory[idx] = program[idx - PROGRAM_MEM_BASE_IDX];
        }

    }

    public void loadCh8Binary(Path path) throws IOException {
        System.out.println("loading binary...");
        // sanity check path and all
        readCh8Binary(path);

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        HexFormat hexFormatLinear = HexFormat.ofDelimiter(":").withUpperCase().withPrefix("#");
        // HexFormat hexFormatVertical =
        // HexFormat.ofDelimiter("\n").withUpperCase().withPrefix("#");

        sb.append(String.format("Emulator Object: %n"));
        sb.append(String.format("- Max addressible bytes are: %d%n", MAX_ADDRESSIBLE_BYTES));
        sb.append(String.format("-- loaded program size is %d bytes: ", program.length));
        sb.append(hexFormatLinear.formatHex(program));

        sb.append(String.format("%n"));
        sb.append(String.format("-- Peeking Memory at location 0x%s (index: %d): %n",
                hexFormatLinear.toHexDigits(PROGRAM_MEM_BASE_IDX), PROGRAM_MEM_BASE_IDX));
        for (int idx = 0; idx < 8; idx++) {
            sb.append(String.format("M- %d|0x%s = #%s%n", idx, hexFormatLinear.toHexDigits(PROGRAM_MEM_BASE_IDX + idx),
                    hexFormatLinear.toHexDigits(memory[PROGRAM_MEM_BASE_IDX + idx])));
        }
        sb.append(String.format("-- .:..%n"));

        sb.append(String.format("R- I Register: %d%n", IReg));

        sb.append(String.format("R- V Registers: %n"));
        for (int i = 0; i < 16; i++) {
            sb.append(String.format("R- V%02d|V%X = %03d or #%s%n", i, i, vRegs[i],
                    hexFormatLinear.toHexDigits(vRegs[i])));
        }

        sb.append(String.format("-- .:..%n"));
        return sb.toString();
    }

}

public class Main {
    public static void main(String[] args) {
        System.out.println("Running C8MJ... | Current time = " + LocalDateTime.now());

        Emulator emu;
        try {
            emu = new Emulator();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Abnormally exiting C8MJ !");
            return;
        }

        System.out.println(emu);
        System.out.println("Exiting C8MJ... | time = " + LocalDateTime.now());
    }
}