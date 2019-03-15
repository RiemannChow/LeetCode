package com.onlinePreview;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import java.io.File;
import java.net.ConnectException;

public class OnlinePreview {
    public static void main(String[] args) {
        new OnlinePreview("E:/Test/OnlinePreview.doc").conver();
    }

    private String fileName;
    private File pdfFile;
    private File docFile;

    public OnlinePreview(String fileString){
        init(fileString);
        System.out.println("文件路径"+fileString);
    }

    /**
     * 重新设置file
     * @param fileString
     */
    public void setFile(String fileString){
        init(fileString);
    }

    /**
     * 初始化
     * @param fileString
     */
    private void init(String fileString){
        fileName = fileString.substring(0,fileString.lastIndexOf("."));
        docFile = new File(fileString);
        pdfFile = new File(fileName + ".pdf");
    }

    private void doc2pdf() throws Exception{
        if (docFile.exists()){
          if (!pdfFile.exists()){
              OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
              try {
                  connection.connect();
                  DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
                  converter.convert(docFile,pdfFile);
                  connection.disconnect();
                  System.out.println("****pdf转换成功，PDF输出: \"+ pdfFile.getPath() + \"****");
              } catch (ConnectException e) {
                  e.printStackTrace();
                  throw e;
              }

          }else{
              System.out.println("****已经转换为pdf，不需要再进行转化 ****PDF输出： "+ pdfFile.getPath() + "****");
          }
        }else{
            System.out.println("****需要转换的文档不存在****");
        }
    }

    public boolean conver(){
        try {
            doc2pdf();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (pdfFile.exists()){
            System.out.println(docFile.getPath()+"已处理成功,处理为pdf的路径为："+pdfFile.getPath());
            return true;
        }else{
            System.out.println(docFile.getPath()+"处理失败");
            return false;
        }
    }

}
