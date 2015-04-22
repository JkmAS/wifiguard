<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                version="1.0">           
    
    <xsl:output method="html" encoding="UTF-8"/>
    
    <xsl:template match="devices">
        <html>    
            <head>       
                <title>Wifi Guard</title>        
                <link rel="stylesheet" type="text/css" href="devices_list.css"/>
            </head>
            <body>                
                <img src="../../image/Wifi_Guard_Desktop_icon.png" alt="Logo Wifi Guard" title="Wifi Guard" height="100" width="100"/><br/>
                <h1>Wifi Guard | <xsl:value-of select="time"/></h1>
                <strong>The list of devices in the network:</strong>
                   
                <xsl:apply-templates select="device"/> 
                                 
            </body>
        </html>       
     </xsl:template>   
    
    <xsl:template match="device">
        <table>
            <tr>
                <td colspan="2"><b>Record <xsl:value-of select="@no."/>.</b></td>
            </tr>
            <xsl:if test="ip_address">
                <tr>
                    <td>IP Address:</td>
                    <td><xsl:value-of select="ip_address"/></td>
                </tr>
            </xsl:if>
            <xsl:if test="mac_address">
                <tr>
                    <td>MAC Address:</td>
                    <td><xsl:value-of select="mac_address"/></td>
                </tr>
            </xsl:if>
            <xsl:if test="device_name">
                <tr>
                    <td>Device:</td>
                    <td><xsl:value-of select="device_name"/></td>
                </tr>
            </xsl:if>
            <xsl:if test="information">
                <tr>
                    <td>Information:</td>
                    <td><xsl:value-of select="information"/></td>
                </tr>
            </xsl:if>            
        </table>    
    </xsl:template>
    
</xsl:stylesheet>
