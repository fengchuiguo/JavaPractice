package com.T3JDOMTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.EscapeStrategy;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class JDOMCreateTest {

    private void createXML() {
        //1.生成一个根节点
        Element rss = new Element("rss");
        //2.为节点添加属性
        rss.setAttribute("version", "2.0");
        //3.生成一个document对象
        Document document = new Document(rss);

        Element channel = new Element("channel");
        rss.addContent(channel);
        Element title = new Element("title");
        title.setText("<![CDATA[上海移动互联网产业促进中心正式揭牌 ]]>");
        channel.addContent(title);

//        Format format = Format.getPrettyFormat();
        Format format = Format.getCompactFormat();
        format.setIndent("");
        format.setEncoding("GBK");

        //4.创建XMLOutputter的对象
        XMLOutputter outputer = new XMLOutputter(format);
        try {
            //5.利用outputer将document对象转换成xml文档
            outputer.output(document, new FileOutputStream(new File("XmlReadWrite/src/com/resultFiles/t3rssNews.xml")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JDOMCreateTest creater = new JDOMCreateTest();
        creater.createXML();
    }

}
