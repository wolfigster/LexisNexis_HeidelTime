package com.lexisnexis.bulk;

public class Util {
    public static String getXMLValue(String theContent, String theStartElement, String theEndElement) {

        if(theContent == null || theContent.length() == 0){
            return null;
        }
        String theElement = getXML(theContent, theStartElement, theEndElement);
        if ((theElement != null) && (!theElement.isEmpty())) {
            // could be a namespace so move to the end of the start tag

            int start = theContent.indexOf(theStartElement);
            int startTagLength = 0;
            if (start < 0) {
                return null;
            }
            // look for the end of the start tag (marked by a '>')
            startTagLength = theContent.indexOf(">", start) - start + 1;
            if (startTagLength < 0) {
                return null;
            }

            String returnString = theElement.substring(startTagLength,
                    theElement.length() - theEndElement.length());
            return returnString.trim();
        }
        return null;
    }

    public static String getXML(String theContent, String theStartElement, String theEndElement) {

        if(theContent == null || theContent.length() == 0){
            return null;
        }

        // start of XML
        int start = theContent.indexOf(theStartElement);
        if (start < 0) {
            return null;
        }

        int end = theContent.indexOf(theEndElement);
        if (end < start) {
            return null;
        }

        end += theEndElement.length();

        // the xml
        return theContent.substring(start, end);
    }
}
