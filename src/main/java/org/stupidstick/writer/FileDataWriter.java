package org.stupidstick.writer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.stupidstick.filter.FileWritingMode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@RequiredArgsConstructor
public class FileDataWriter implements DataWriter {
    @Getter
    private final String filePath;
    @Getter
    private final FileWritingMode writingMode;
    private BufferedWriter writer;

    @Override
    public void writeLine(String line) throws IOException {
        if (writer == null)
            writer = new BufferedWriter(new FileWriter(filePath, writingMode.isAppend()));
        writer.write(line);
        writer.newLine();
    }

    @Override
    public void close() throws IOException {
        if (writer != null)
            writer.close();
    }
}
