package com.scoket.io.BreakpointUpLoad;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

/**
 * @author riemann
 * @date 2019/04/24 23:22
 */
public class FileAccessI implements Serializable {

    RandomAccessFile oSavedFile;
    long nPos;

    public FileAccessI() throws IOException
    {
        this("", 0);
    }

    public FileAccessI(String sName, long nPos) throws IOException
    {
        oSavedFile = new RandomAccessFile(sName, "rw");
        this.nPos = nPos;
        oSavedFile.seek(nPos);
    }

    public synchronized int write(byte[] b, int nStart, int nLen)
    {
        int n = -1;
        try
        {
            oSavedFile.write(b, nStart, nLen);
            n = nLen;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return n;
    }
}
