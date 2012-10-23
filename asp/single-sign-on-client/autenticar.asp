<%
username = request.form("username")
password = request.form("password")

Response.Write (username & "<br>")
Response.Write (password & "<br>")

Set HTTP = CreateObject("MSXML2.serverXMLHTTP")
url = "http://openam.sdr.serpro:8080/openam/identity/json/authenticate?username=" & username & "&password=" & password
HTTP.Open "GET", (url), False
HTTP.send("")
retorno = HTTP.responseText
Response.Write (retorno & "<br>")

token = Mid (retorno, 13, 62)

Response.Write token & "<br>"

Response.AddHeader "Set-Cookie", "iPlanetDirectoryPro="&token&"; Domain=.sdr.serpro; path=/"

'Response.Cookies("iPlanetDirectoryPro")=token
'Response.Cookies("iPlanetDirectoryPro").Domain = ".sdr.serpro"
'Response.Cookies("iPlanetDirectoryPro").Path = "/"

Response.Redirect "/openam/recurso.asp"
%>
