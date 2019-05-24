package com.java.common;

import java.util.Properties;  
import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.AddressException;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
  
/**************************************************************** 
 * 电子邮件发送服务
 * 
 * 基于JAVA MAIL封装完成，首先把QQ邮箱（或其它种类邮箱）的POP3 SMTP打开，例如： 设置->账户 --在下面-- 把这个选上 [√]SMTP发信后保存到服务器 
 ****************************************************************/  
public class MailUtil {  
  
    // 设置服务器  
    private static String KEY_SMTP = "mail.smtp.host";  
    private static String VALUE_SMTP = "smtp.163.com";  
    // 服务器验证  
    private static String KEY_PROPS = "mail.smtp.auth";  
    private static boolean VALUE_PROPS = true;  
    // 发件人用户名、密码  
    private String SEND_USER = "africareadaccount@163.com";  
    private String SEND_PWD = "ar_987654321";  
    // 建立会话  
    private MimeMessage message;  
    private Session s;  
  
    private static MailUtil Instance = null;
    
    public static MailUtil instance()
    {
    	if(Instance == null)
    	{
    		Instance = new MailUtil();
    	}
    	return Instance;
    }
    
    /* 
     * 初始化方法 
     */  
    private MailUtil() {  
        Properties props = System.getProperties();  
        props.setProperty(KEY_SMTP, VALUE_SMTP);  
        props.put(KEY_PROPS, VALUE_PROPS);  
        s = Session.getInstance(props);  
        /* s.setDebug(true);开启后有调试信息 */  
        message = new MimeMessage(s);  
    }  
  
    /** 
     * 发送邮件 
     *  
     * @param headName 
     *            邮件头文件名 
     * @param sendHtml 
     *            邮件内容 
     * @param receiveUser 
     *            收件人地址 
     */  
    public boolean send(String title, String content,  
            String receiveUser) {  
        try {  
            // 发件人  
            InternetAddress from = new InternetAddress(SEND_USER);  
            message.setFrom(from);  
            // 收件人  
            InternetAddress to = new InternetAddress(receiveUser);  
            message.setRecipient(Message.RecipientType.TO, to);  
            // 邮件标题  
            message.setSubject(title);  
            content = content.toString();  
            // 邮件内容,也可以使纯文本"text/plain"  
            message.setContent(content, "text/html;charset=GBK");  
            message.saveChanges();  
            Transport transport = s.getTransport("smtp");  
            // smtp验证，就是你用来发邮件的邮箱用户名密码  
            transport.connect(VALUE_SMTP, SEND_USER, SEND_PWD);  
            // 发送  
            transport.sendMessage(message, message.getAllRecipients());  
            transport.close();  
            System.out.println("send success!");  
            return true;
        } catch (AddressException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (MessagingException e) {  
            e.printStackTrace();  
        }  
        return false;
    }  
  
    public static void main(String[] args) {  
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < 1000; i++)
    	{
    		sb.append("\n");
    		sb.append(i);
    		sb.append("\n");    		
        	sb.append("<p>通讯员 凌玮</p>"+

        			"<p>你有没有想象过这样一个场景：在房顶贴上一种类似“彩砖”的东西，让它利用太阳能发电、蓄电，如果自家用不完，还可以卖给电力公司。</p>"+

        			"<p>“彩砖”一小时能发电300度</p>");
    	}
    	MailUtil.instance().send("邮件头文件名", sb.toString(), "tianlan91@163.com");  
    }
}  

