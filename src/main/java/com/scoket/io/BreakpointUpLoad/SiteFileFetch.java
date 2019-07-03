/*package com.scoket.io.BreakpointUpLoad;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

*//**
 * @author riemann
 * @date 2019/04/24 23:23
 *//*
public class SiteFileFetch extends Thread {

    *//** 文件信息 Bean *//*
    SiteInfoBean siteInfoBean = null;

    *//** 文件指针开始位置数组 *//*
    long[] nStartPos;

    *//** 文件指针结束位置数组 *//*
    long[] nEndPos;

    *//** 子下载线程数组 *//*
    FileSplitterFetch[] fileSplitterFetch;

    *//** 文件长度 (字节byte) *//*
    long nFileLength;

    *//** 是否第一次取文件 *//*
    boolean bFirst = true;

    *//** 停止标志 *//*
    boolean bStop = false;

    *//**
     * 临时文件用于记录文件下载信息(下载线程个数,每个下载线程的当前文件开始指针,文件结束指针)
     *//*
    private File tmpFile;

    // 输出到文件的输出流
    DataOutputStream output;

    private boolean isLoading;

    public SiteFileFetch(SiteInfoBean bean) throws IOException
    {
        siteInfoBean = bean;

        isLoading = true;

        adjustFileNameForDuplicate(bean);

        tmpFile = new File(bean.getSFilePath() + File.separator
                + bean.getSFileName() + ".info");

        if (tmpFile.exists())
        {
            // 临时文件存在,则认为不是第一次下载,之前有下载过,但是没下载完成(断点续传下载)
            bFirst = false;

            read_nPos();
        }
        else
        {
            // 文件指针开始位置数组个数取决于文件被分割成子文件的个数
            nStartPos = new long[bean.getNSplitter()];
            nEndPos = new long[bean.getNSplitter()];
        }
    }



    *//**
     * 文件名重复则重新命名
     *
     * @param bean
     *//*
    private void adjustFileNameForDuplicate(SiteInfoBean bean)
    {
        if (bean != null && bean.getSFileName() != null
                && bean.getSFilePath() != null)
        {
            File file = new File(bean.getSFilePath() + File.separator
                    + bean.getSFileName());

            int lastDotIdx = bean.getSFileName().lastIndexOf(".");

            String prefix = bean.getSFileName().substring(0, lastDotIdx);

            String suffix = bean.getSFileName().substring(lastDotIdx + 1);

            int count = 1;

            while (file.exists())
            {

                File loadInfoFile = new File(bean.getSFilePath() + File.separator
                        + bean.getSFileName() + ".info");

                if (loadInfoFile.exists())
                {//如果临时文件存在,则认为是上次没有下载完成的,这是不用重新命名
                    break;
                }

                String newPrefix = prefix + "(" + count + ")";

                bean.setSFileName(newPrefix + "." + suffix);

                file = new File(bean.getSFilePath() + File.separator
                        + bean.getSFileName());
                count++;
            }
        }
    }

    *//**
     * (1) 获得文件长度 <br>
     * (2) 分割文件<br>
     * (3) 创建文件下载线程 FileSplitterFetch<br>
     * (4) 启动文件下载线程  FileSplitterFetch 线程<br>
     * (5) 等待子线程返回
     *//*
    public void run()
    {
        try
        {
            nFileLength = getFileSize();

            if (nFileLength == -1)
            {
                isLoading = false;
                bStop = true;
                System.err.println("File Length is not known!");
                return;
            }
            else if (nFileLength == -2)
            {
                isLoading = false;
                bStop = true;
                System.err.println("File is not access!");

                return;
            }

            if (bFirst)
            {// 如果是第一次下载
                // 分配文件指针数组的起始结束位置
                for (int i = 0; i < nStartPos.length; i++)
                {
                    nStartPos[i] = (long) (i * (nFileLength / nStartPos.length));
                }

                for (int i = 0; i < nEndPos.length - 1; i++)
                {
                    nEndPos[i] = nStartPos[i + 1];
                }

                nEndPos[nEndPos.length - 1] = nFileLength;
            }

            // 创建 启动子线程数组
            fileSplitterFetch = new FileSplitterFetch[nStartPos.length];

            for (int i = 0; i < nStartPos.length; i++)
            {
                fileSplitterFetch[i] = new FileSplitterFetch(
                        siteInfoBean.getSSiteURL(), siteInfoBean.getSFilePath()
                        + File.separator + siteInfoBean.getSFileName(),
                        nStartPos[i], nEndPos[i], i);

                Utility.log("Thread " + i + " , nStartPos = " + nStartPos[i]
                        + ", nEndPos = " + nEndPos[i]);

                //启动子线程
                fileSplitterFetch[i].start();
            }

            boolean breakWhile = false;

            while (!bStop)
            {// 如果下载没有停止,则每隔500ms去保存一次文件指针信息到临时文件

                write_nPos();

                gatherLoadProgress();

                Utility.sleep(500);

                breakWhile = true;

                for (int i = 0; i < nStartPos.length; i++)
                {
                    if (!fileSplitterFetch[i].bDownOver)
                    {// 只要其中有一个没下载完成,
                        breakWhile = false;
                        break;
                    }
                }

                if (breakWhile)
                {
                    break;
                }
            }

            gatherLoadProgress();

            System.err.println("文件下载结束！");

            isLoading = false;
        }
        catch (Exception e)
        {
            isLoading = false;
            e.printStackTrace();
        }
    }

    *//**
     * 获得文件长度
     *
     * @return
     *//*
    public long getFileSize()
    {
        int nFileLength = -1;

        try
        {
            URL url = new URL(siteInfoBean.getSSiteURL());

            HttpURLConnection httpConnection = (HttpURLConnection) url
                    .openConnection();
            httpConnection.setRequestProperty("User-Agent", "NetFox");

            int responseCode = httpConnection.getResponseCode();

            if (responseCode >= 400)
            {
                processErrorCode(responseCode);
                return -2; // -2 represent access is error
            }

            String sHeader;

            for (int i = 1;; i++)
            {
                sHeader = httpConnection.getHeaderFieldKey(i);
                if (sHeader != null)
                {
                    if (sHeader.equals("Content-Length"))
                    {
                        nFileLength = Integer.parseInt(httpConnection
                                .getHeaderField(sHeader));
                        break;
                    }
                }
                else
                {
                    break;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Utility.log(nFileLength);
        return nFileLength;
    }

    *//**
     * 收集下载进度
     *//*
    private void gatherLoadProgress()
    {
        // 剩余的字节数
        long laveLength = 0;

        for (int i = 0; i < nStartPos.length; i++)
        {
            laveLength += (fileSplitterFetch[i].nEndPos - fileSplitterFetch[i].nStartPos);
        }

        int percent = (int) ((nFileLength - laveLength) * 100 / nFileLength);

        if(percent == 100)
        {
            if(tmpFile != null && tmpFile.exists())
            {
                //全部下载完成,则删除临时文件,
                tmpFile.delete();
            }

            isLoading = false;

            bStop = true;
        }

        System.out.println("当前下载进度 " + percent + "%");
    }

    *//**
     * 保存下载信息（文件指针位置）
     *//*
    private void write_nPos()
    {
        try
        {
            output = new DataOutputStream(new FileOutputStream(tmpFile));
            output.writeInt(nStartPos.length);

            for (int i = 0; i < nStartPos.length; i++)
            {
                output.writeLong(fileSplitterFetch[i].nStartPos);
                output.writeLong(fileSplitterFetch[i].nEndPos);
            }

            output.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    *//**
     * 读取之前下载保存下来的文件指针位置
     *//*
    private void read_nPos()
    {
        try
        {
            DataInputStream input = new DataInputStream(new FileInputStream(
                    tmpFile));

            // 个数(这里记录了文件被划分成几个子文件(子任务))
            int nCount = input.readInt();

            nStartPos = new long[nCount];
            nEndPos = new long[nCount];

            for (int i = 0; i < nStartPos.length; i++)
            {
                nStartPos[i] = input.readLong();
                nEndPos[i] = input.readLong();
            }

            input.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void processErrorCode(int nErrorCode)
    {
        System.err.println("Error Code : " + nErrorCode);
    }

    public boolean isLoading()
    {
        return isLoading;
    }

    *//**
     * 停止文件下载
     *//*
    public void siteStop()
    {
        bStop = true;

        isLoading = false;

        for (int i = 0; i < nStartPos.length; i++)
        {
            fileSplitterFetch[i].splitterStop();
        }
    }

    public interface LoadProgressListener
    {
        void onstartLoad();
        void onProgressUpdate(int percent);
        void onCompleteLoad();
        void onStopLoad();
    }

}*/
