package com.sperez.jhs;

class HtmlTemplateEngine {
    static String pageStringWithALinks(String result, String port) {
        String[] splittedResult = result.split("\n");
        String templatedResult = "";
        for(int i=0; i < splittedResult.length; i++) {
            templatedResult = templatedResult + "<a href=\"" + "/" +
            splittedResult[i] + "\">" + splittedResult[i] + "</a></br>";
        }
        return templatedResult;
    }

    static String page404() {
        String FourOhFour = "<html><body><h1>404 Not Found</h1></body></html>";
        return FourOhFour;
    }

    static String page405() {
        String FourOhFive = "<html><body><h1>405 Method Not Allowed</h1></body></html>";
        return FourOhFive;
    }
}