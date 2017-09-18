package com.ddt.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MMap {

    public static byte[] read(final String fileName) {
        File file = new File(fileName);
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(fileName, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        MappedByteBuffer buff = null;
        try {
            assert randomAccessFile != null;
            buff = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (buff == null) {
            return null;
        }

        byte[] dst = new byte[(int) file.length()];
        for (int j = 0; j < buff.capacity(); j++) {
            dst[j] = buff.get(j);
        }
        return dst;
    }
}
