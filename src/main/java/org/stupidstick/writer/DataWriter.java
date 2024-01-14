package org.stupidstick.writer;

import java.io.IOException;

public interface DataWriter {
    void writeLine(String line) throws IOException;

    void close() throws IOException;
}
