package shop.utils;

import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.io.IOException;

public class sendEmail {
    static public boolean checkRegister(String email, String check_str) {
        String content =
                "<!doctype html>\n" +
                        "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                        "\n" +
                        "<head>\n" +
                        "  <title> </title>\n" +
                        "  <!--[if !mso]><!-- -->\n" +
                        "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                        "  <!--<![endif]-->\n" +
                        "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                        "  <style type=\"text/css\">\n" +
                        "    #outlook a {\n" +
                        "      padding: 0;\n" +
                        "    }\n" +
                        "\n" +
                        "    .ReadMsgBody {\n" +
                        "      width: 100%;\n" +
                        "    }\n" +
                        "\n" +
                        "    .ExternalClass {\n" +
                        "      width: 100%;\n" +
                        "    }\n" +
                        "\n" +
                        "    .ExternalClass * {\n" +
                        "      line-height: 100%;\n" +
                        "    }\n" +
                        "\n" +
                        "    body {\n" +
                        "      margin: 0;\n" +
                        "      padding: 0;\n" +
                        "      -webkit-text-size-adjust: 100%;\n" +
                        "      -ms-text-size-adjust: 100%;\n" +
                        "    }\n" +
                        "\n" +
                        "    table,\n" +
                        "    td {\n" +
                        "      border-collapse: collapse;\n" +
                        "      mso-table-lspace: 0pt;\n" +
                        "      mso-table-rspace: 0pt;\n" +
                        "    }\n" +
                        "\n" +
                        "    img {\n" +
                        "      border: 0;\n" +
                        "      height: auto;\n" +
                        "      line-height: 100%;\n" +
                        "      outline: none;\n" +
                        "      text-decoration: none;\n" +
                        "      -ms-interpolation-mode: bicubic;\n" +
                        "    }\n" +
                        "\n" +
                        "    p {\n" +
                        "      display: block;\n" +
                        "      margin: 13px 0;\n" +
                        "    }\n" +
                        "  </style>\n" +
                        "  <!--[if !mso]><!-->\n" +
                        "  <style type=\"text/css\">\n" +
                        "    @media only screen and (max-width:480px) {\n" +
                        "      @-ms-viewport {\n" +
                        "        width: 320px;\n" +
                        "      }\n" +
                        "      @viewport {\n" +
                        "        width: 320px;\n" +
                        "      }\n" +
                        "    }\n" +
                        "  </style>\n" +
                        "  <!--<![endif]-->\n" +
                        "  <!--[if mso]>\n" +
                        "        <xml>\n" +
                        "        <o:OfficeDocumentSettings>\n" +
                        "          <o:AllowPNG/>\n" +
                        "          <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                        "        </o:OfficeDocumentSettings>\n" +
                        "        </xml>\n" +
                        "        <![endif]-->\n" +
                        "  <!--[if lte mso 11]>\n" +
                        "        <style type=\"text/css\">\n" +
                        "          .outlook-group-fix { width:100% !important; }\n" +
                        "        </style>\n" +
                        "        <![endif]-->\n" +
                        "  <!--[if !mso]><!-->\n" +
                        "  <link href=\"https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700\" rel=\"stylesheet\" type=\"text/css\">\n" +
                        "  <style type=\"text/css\">\n" +
                        "    @import url(https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700);\n" +
                        "  </style>\n" +
                        "  <!--<![endif]-->\n" +
                        "  <style type=\"text/css\">\n" +
                        "    @media only screen and (min-width:480px) {\n" +
                        "      .mj-column-per-100 {\n" +
                        "        width: 100% !important;\n" +
                        "        max-width: 100%;\n" +
                        "      }\n" +
                        "      .mj-column-per-50 {\n" +
                        "        width: 50% !important;\n" +
                        "        max-width: 50%;\n" +
                        "      }\n" +
                        "    }\n" +
                        "  </style>\n" +
                        "  <style type=\"text/css\">\n" +
                        "    @media only screen and (max-width:480px) {\n" +
                        "      table.full-width-mobile {\n" +
                        "        width: 100% !important;\n" +
                        "      }\n" +
                        "      td.full-width-mobile {\n" +
                        "        width: auto !important;\n" +
                        "      }\n" +
                        "    }\n" +
                        "  </style>\n" +
                        "</head>\n" +
                        "\n" +
                        "<body style=\"background-color:#eeeeee;\">\n" +
                        "  <div style=\"background-color:#eeeeee;\">\n" +
                        "    <!--[if mso | IE]>\n" +
                        "      <table\n" +
                        "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                        "      >\n" +
                        "        <tr>\n" +
                        "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                        "      <![endif]-->\n" +
                        "    <div style=\"Margin:0px auto;max-width:600px;\">\n" +
                        "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                        "        <tbody>\n" +
                        "          <tr>\n" +
                        "            <td style=\"direction:ltr;font-size:0px;padding:20px 0;text-align:center;vertical-align:top;\">\n" +
                        "              <!--[if mso | IE]>\n" +
                        "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "                \n" +
                        "        <tr>\n" +
                        "      \n" +
                        "            <td\n" +
                        "               class=\"\" style=\"vertical-align:top;width:600px;\"\n" +
                        "            >\n" +
                        "          <![endif]-->\n" +
                        "              <div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                        "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                        "                  <tr>\n" +
                        "                    <td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                        "                      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1;text-align:center;color:#555555;\">\n" +
                        "                        <h2>注册验证邮件</h2>\n" +
                        "                      </div>\n" +
                        "                    </td>\n" +
                        "                  </tr>\n" +
                        "                </table>\n" +
                        "              </div>\n" +
                        "              <!--[if mso | IE]>\n" +
                        "            </td>\n" +
                        "          \n" +
                        "        </tr>\n" +
                        "      \n" +
                        "                  </table>\n" +
                        "                <![endif]-->\n" +
                        "            </td>\n" +
                        "          </tr>\n" +
                        "        </tbody>\n" +
                        "      </table>\n" +
                        "    </div>\n" +
                        "    <!--[if mso | IE]>\n" +
                        "          </td>\n" +
                        "        </tr>\n" +
                        "      </table>\n" +
                        "      \n" +
                        "      <table\n" +
                        "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                        "      >\n" +
                        "        <tr>\n" +
                        "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                        "      <![endif]-->\n" +
                        "    <div style=\"background:#ffffff;background-color:#ffffff;Margin:0px auto;max-width:600px;\">\n" +
                        "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#ffffff;background-color:#ffffff;width:100%;\">\n" +
                        "        <tbody>\n" +
                        "          <tr>\n" +
                        "            <td style=\"direction:ltr;font-size:0px;padding:20px 0;text-align:center;vertical-align:top;\">\n" +
                        "              <!--[if mso | IE]>\n" +
                        "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "                \n" +
                        "        <tr>\n" +
                        "      \n" +
                        "            <td\n" +
                        "               class=\"\" style=\"vertical-align:top;width:300px;\"\n" +
                        "            >\n" +
                        "          <![endif]-->\n" +
                        "              <div class=\"mj-column-per-50 outlook-group-fix\" style=\"font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                        "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                        "                  <tr>\n" +
                        "                    <td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                        "                      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1;text-align:center;color:#555555;\">\n" +
                        "                        <h2>分享书城</h2>\n" +
                        "                      </div>\n" +
                        "                    </td>\n" +
                        "                  </tr>\n" +
                        "                  <tr>\n" +
                        "                    <td align=\"left\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                        "                      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1;text-align:left;color:#555555;\"> 感谢您注册成为分享书城的用户.请点击下方链接以完成您的注册. </div>\n" +
                        "                    </td>\n" +
                        "                  </tr>\n" +
                        "                  <tr>\n" +
                        "                    <td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                        "                      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1;text-align:center;color:#555555;\">\n" +
                        "                        <h2><a href=\"http://shop/localhost:8080/checkRegisterServlet?email=" + email + "&check_str=" + check_str + "\">点击此处验证</a></h2>\n" +
                        "                      </div>\n" +
                        "                    </td>\n" +
                        "                  </tr>\n" +
                        "                </table>\n" +
                        "              </div>\n" +
                        "              <!--[if mso | IE]>\n" +
                        "            </td>\n" +
                        "          \n" +
                        "            <td\n" +
                        "               class=\"\" style=\"vertical-align:top;width:300px;\"\n" +
                        "            >\n" +
                        "          <![endif]-->\n" +
                        "              <div class=\"mj-column-per-50 outlook-group-fix\" style=\"font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                        "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                        "                  <tr>\n" +
                        "                    <td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                        "                      <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px;\">\n" +
                        "                        <tbody>\n" +
                        "                          <tr>\n" +
                        "                            <td style=\"width:140px;\"> <img height=\"auto\" src=\"https://blog.mooyyu.cn/images/info/mooyyu.jpg\" style=\"border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;\" width=\"140\" /> </td>\n" +
                        "                          </tr>\n" +
                        "                        </tbody>\n" +
                        "                      </table>\n" +
                        "                    </td>\n" +
                        "                  </tr>\n" +
                        "                </table>\n" +
                        "              </div>\n" +
                        "              <!--[if mso | IE]>\n" +
                        "            </td>\n" +
                        "          \n" +
                        "        </tr>\n" +
                        "      \n" +
                        "                  </table>\n" +
                        "                <![endif]-->\n" +
                        "            </td>\n" +
                        "          </tr>\n" +
                        "        </tbody>\n" +
                        "      </table>\n" +
                        "    </div>\n" +
                        "    <!--[if mso | IE]>\n" +
                        "          </td>\n" +
                        "        </tr>\n" +
                        "      </table>\n" +
                        "      \n" +
                        "      <table\n" +
                        "         align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\"\n" +
                        "      >\n" +
                        "        <tr>\n" +
                        "          <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
                        "      <![endif]-->\n" +
                        "    <div style=\"Margin:0px auto;max-width:600px;\">\n" +
                        "      <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\">\n" +
                        "        <tbody>\n" +
                        "          <tr>\n" +
                        "            <td style=\"direction:ltr;font-size:0px;padding:20px 0;text-align:center;vertical-align:top;\">\n" +
                        "              <!--[if mso | IE]>\n" +
                        "                  <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "                \n" +
                        "        <tr>\n" +
                        "      \n" +
                        "            <td\n" +
                        "               class=\"\" style=\"vertical-align:top;width:600px;\"\n" +
                        "            >\n" +
                        "          <![endif]-->\n" +
                        "              <div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\">\n" +
                        "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\">\n" +
                        "                  <tr>\n" +
                        "                    <td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\">\n" +
                        "                      <div style=\"font-family:Ubuntu, Helvetica, Arial, sans-serif;font-size:13px;line-height:1;text-align:center;color:#555555;\"> 分享书城 CopyRight © 2019 MOOYYU </div>\n" +
                        "                    </td>\n" +
                        "                  </tr>\n" +
                        "                </table>\n" +
                        "              </div>\n" +
                        "              <!--[if mso | IE]>\n" +
                        "            </td>\n" +
                        "          \n" +
                        "        </tr>\n" +
                        "      \n" +
                        "                  </table>\n" +
                        "                <![endif]-->\n" +
                        "            </td>\n" +
                        "          </tr>\n" +
                        "        </tbody>\n" +
                        "      </table>\n" +
                        "    </div>\n" +
                        "    <!--[if mso | IE]>\n" +
                        "          </td>\n" +
                        "        </tr>\n" +
                        "      </table>\n" +
                        "      <![endif]-->\n" +
                        "  </div>\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>";

        Session session = MailUtils.createSession("smtp.qq.com", "name", "password");
        Mail mail = new Mail("emailAddress", email, "注册验证", content);
        try {
            MailUtils.send(session, mail);
            return true;
        } catch (IOException io) {
            io.printStackTrace();
        } catch (MessagingException m) {
            m.printStackTrace();
        }
        return false;
    }
}
