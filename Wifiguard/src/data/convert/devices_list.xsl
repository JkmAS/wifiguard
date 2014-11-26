<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns="http://www.w3.org/1999/xhtml" >            
    <xsl:output method="xml"  encoding="utf-8" indent="yes" doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" xml:lang="cs"></xsl:output>
<xsl:template match="devices">
    <html xmlns="http://www.w3.org/1999/xhtml">
    <article>    
    <head>       
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <title>Wifi Guard</title>        
        <link rel="stylesheet" type="text/css" href="devices_list.css"/>
    </head>
    <body>
        <p>
            <img src="../../image/Wifi_Guard_Desktop_icon.png" alt="Logo Wifi Guard" title="Wifi Guard" height="100" width="100"/>
            <h1>Wifi Guard</h1>
            <h1>The list of devices in the network:</h1>
        </p>    
        <xsl:apply-templates select="device"/> 
                         
    </body>
    </article>   
    </html>       
 </xsl:template>    
    <xsl:template match="device">
        <h2><xsl:value-of select="no."/></h2> 
        <table>
            <xsl:if test="ip_address"><tr><td>IP Address:</td><td><xsl:value-of select="ip_address"/></td></tr></xsl:if>
            <xsl:if test="mac_address"><tr><td>MAC Address:</td><td><xsl:value-of select="mac_address"/></td></tr></xsl:if>
            <xsl:if test="device_name"><tr><td>Device:</td><td><xsl:value-of select="device_name"/></td></tr></xsl:if>
            <xsl:if test="information"><tr><td>Information:</td><td><xsl:value-of select="information"/></td></tr></xsl:if>            
        </table>    
    </xsl:template>   
</xsl:stylesheet>
