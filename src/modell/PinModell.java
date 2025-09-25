package modell;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PinModell {
    private StringBuilder pin = new StringBuilder();

    public void addDigit(String digit) {
        if (pin.length() < 4) {
            pin.append(digit);
        }
    }

    public String getPin() {
        return pin.toString();
    }

    public boolean isComplete() {
        return pin.length() == 4;
    }

    public void reset() {
        pin.setLength(0);
    }

    private void mentesFileaba()throws IOException {
        Path path = Path.of("pin.txt");
        Files.write(path, List.of(pin));
    }
}

