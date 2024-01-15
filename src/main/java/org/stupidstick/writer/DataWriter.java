package org.stupidstick.writer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.stupidstick.filter.WritingMode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@RequiredArgsConstructor
public class DataWriter {
    @Getter
    private final String filePath;
    @Getter
    private final WritingMode writingMode;
    private BufferedWriter writer;

    public void writeLine(String line) throws IOException {
        if (writer == null)
            writer = new BufferedWriter(new FileWriter(filePath, writingMode.isAppend()));
        writer.write(line);
        writer.newLine();
    }

    public void close() throws IOException {
        if (writer != null)
            writer.close();
    }
}
