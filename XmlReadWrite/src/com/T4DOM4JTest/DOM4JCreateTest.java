package com.T4DOM4JTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class DOM4JCreateTest {
    private void createXML() {
        //1.创建document对象，代表整个xml文档
        Document document = DocumentHelper.createDocument();
        //2.创建根节点rss
        Element rss = document.addElement("rss");
        //3.向rss节点中添加version属性
        rss.addAttribute("version", "2.0");
        //4.生成子节点及节点内容
        Element channel = rss.addElement("channel");
        Element title = channel.addElement("title");
        title.setText("<![CDATA[上海移动互联网产业促进中心正式揭牌 ]]>");
        //5.设置生成xml的格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("GBK");
        //6.生成xml文件
        File file = new File("XmlReadWrite/src/com/resultFiles/t4rssNews.xml");
        XMLWriter writer;
        try {
            writer = new XMLWriter(new FileOutputStream(file), format);
            //设置是否转义，默认值是true，代表转义
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DOM4JCreateTest().createXML();
    }

}
