package com.fuping.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet("/servlet/upload")
@MultipartConfig
public class SeFileServlet extends HttpServlet{
    private Logger logger = LoggerFactory.getLogger(SeFileServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/uploadse.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String fileStr = req.getParameter("fileStr");
        logger.debug("文件的描述：{}",fileStr);
        Part part= req.getPart("doc");
        logger.debug("文件的大小：{}",part.getSize());
        String filename=getFileName(part);
        logger.debug("文件的原始名字：{}",filename);

        saveFile(part);
    }

    private void saveFile(Part part) throws IOException {
        File dir = new File("/F:upload");
        if (!dir.exists()){
            dir.mkdir();
        }
        InputStream input= part.getInputStream();
        FileOutputStream output= new FileOutputStream(new File(dir,getFileName(part)));

        BufferedInputStream bufferInput=new BufferedInputStream(input);
        BufferedOutputStream bufferOutput=new BufferedOutputStream(output);

        byte[] buffer = new byte[1024];
        int len=-1;
        while ((len=bufferInput.read(buffer))!=-1){
            bufferOutput.write(buffer,0,len);
        }

        bufferOutput.flush();
        bufferInput.close();
        bufferOutput.close();
    }

    private String getFileName(Part part){
        String header= part.getHeader("Content-Disposition");
        header = header.substring(header.indexOf("filename=\""));
        header = header.substring(header.indexOf("\"")+1,header.length()-1);
        return header;
    }
}
