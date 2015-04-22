<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                version="1.0"
                xmlns:xp="http://www.wifiguard.cz">           
    
    <xsl:output method="html" encoding="UTF-8"/>
    
    <xsl:template match="xp:devices">
        <html>    
            <head>       
                <title>Wifi Guard</title>        
                <link rel="stylesheet" type="text/css" href="devices_list.css"/>
            </head>
            <body>                
                <img src="../../image/Wifi_Guard_Desktop_icon.png" alt="Logo Wifi Guard" title="Wifi Guard" height="100" width="100"/><br/>
                <h1>Wifi Guard | <xsl:value-of select="xp:time"/></h1>
                <strong>The list of devices in the network:</strong>
                   
                <xsl:apply-templates select="xp:device"/> 
                                 
            </body>
        </html>       
     </xsl:template>   
    
    <xsl:template match="xp:device">
        <table>
            <tr>
                <td colspan="2"><b>Record <xsl:value-of select="@no."/>.</b></td>
            </tr>
            <xsl:if test="xp:ip_address">
                <tr>
                    <td>IP Address:</td>
                    <td><xsl:value-of select="xp:ip_address"/></td>
                </tr>
            </xsl:if>
            <xsl:if test="xp:mac_address">
                <tr>
                    <td>MAC Address:</td>
                    <td><xsl:value-of select="xp:mac_address"/></td>
                </tr>
            </xsl:if>
            <xsl:if test="xp:device_name">
                <tr>
                    <td>Device:</td>
                    <td><xsl:value-of select="xp:device_name"/></td>
                </tr>
            </xsl:if>
            <xsl:if test="xp:information">
                <tr>
                    <td>Information:</td>
                    <td><xsl:value-of select="xp:information"/></td>
                </tr>
            </xsl:if>            
        </table>    
    </xsl:template>
    
</xsl:stylesheet>
