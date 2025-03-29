<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
<!--Define una plantilla que se aplicara al elemento raiz del documento-->    
    <xsl:template match="/">
    <!--Estructura del HTML, titulo estilo de la tabla y definicion de la misma-->
        <html>
            <head>
                <title>Videojuegos</title>
                <style>
                    table { border-collapse: collapse; width: 100%; }
                    th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }
                    th { background-color: #f2f2f2; }
                </style>
            </head>
            <body>
                <h1>Lista de Videojuegos</h1>
                <table>
                    <tr>
                        <th>Título</th>
                        <th>Plataforma</th>
                        <th>Género</th>
                        <th>Año de Lanzamiento</th>
                        <th>Desarrollador</th>
                    </tr>
                    <!--Itera sobre cada videojuego y crea una fila y columna-->
                    <xsl:for-each select="videojuegos/videojuego">
                        <tr>
                            <td><xsl:value-of select="titulo"/></td>
                            <td><xsl:value-of select="plataforma"/></td>
                            <td><xsl:value-of select="genero"/></td>
                            <td><xsl:value-of select="anio_lanzamiento"/></td>
                            <td><xsl:value-of select="desarrollador"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>