package com.scoket.io.BreakpointUpLoad;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author riemann
 * @date 2019/04/24 23:24
 */
public class FileSplitterFetch extends Thread {

    String sURL; // File URL
    long nStartPos; // File Snippet Start Position
    long nEndPos; // File Snippet End Position
    int nThreadID; // Thread's ID
    boolean bDownOver = false; // Downing is over
    boolean bStop = false; // Stop identical
    FileAccessI fileAccessI = null; // File Access interface

    /**
     *
     * @param sURL 文件资源URL
     * @param sName 要保存的文件名(完整路径,绝对路径)
     * @param nStart 文件指针开始位置
     * @param nEnd 文件指针结束位置
     * @param id 线程ID
     * @throws IOException
     */
    public FileSplitterFetch(String sURL, String sName, long nStart, long nEnd,
                             int id) throws IOException, IOException {
        this.sURL = sURL;
        this.nStartPos = nStart;
        this.nEndPos = nEnd;
        nThreadID = id;
        fileAccessI = new FileAccessI(sName, nStartPos);
    }

    public void run()
    {
        while (nStartPos < nEndPos && !bStop)
        {
            try
            {
                URL url = new URL(sURL);

                HttpURLConnection httpConnection = (HttpURLConnection) url
                        .openConnection();
                httpConnection.setRequestProperty("User-Agent", "NetFox");

                String sProperty = "bytes=" + nStartPos + "-";
                httpConnection.setRequestProperty("RANGE", sProperty);

                Utility.log(sProperty);

                InputStream input = httpConnection.getInputStream();

                byte[] b = new byte[1024];

                int nRead;

                while ((nRead = input.read(b, 0, 1024)) > 0
                        && nStartPos < nEndPos && !bStop)
                {
                    //注意这里不用再判断 nRead+nStartPos<nEndPos,只需要 nStartPos<nEndPos就可以,
                    //因为是前面几个下载线程读取的内容超出了nEndPos,也会被它后面的子线程读取内容覆盖掉,
                    //最后一个子下载子线程最后读取到的字节个数小于1024的,所以总的结束位置不超过就可以
                    nStartPos += fileAccessI.write(b, 0, nRead);
                }

                Utility.log("Thread " + nThreadID + " is over!"+",nStartPos="+nStartPos+",nEndPos="+nEndPos);

                bDownOver = true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    // 打印回应的头信息
    public void logResponseHead(HttpURLConnection con)
    {
        for (int i = 1;; i++)
        {
            String header = con.getHeaderFieldKey(i);
            if (header != null)
                // responseHeaders.put(header,httpConnection.getHeaderField(header));
                Utility.log(header + " : " + con.getHeaderField(header));
            else
                break;
        }
    }

    public void splitterStop()
    {
        bStop = true;
    }

}
