<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2//EN">
<html>
	<head>
		<title>Recurso</title>
	</head>
	<body>
		<%
		cookieName = "iPlanetDirectoryPro"
		token = Request.Cookies(cookieName)
		acessoliberado = false
		
		'verifica se o cookie existe		
		If ( token <> "" ) Then
			'cookie valido, valida o token via restful services
			Set HTTP = CreateObject("MSXML2.serverXMLHTTP")
			url = "http://openam.sdr.serpro:8080/openam/identity/json/isTokenValid?tokenid=" & token
			HTTP.Open "GET", (url), False
			HTTP.send("")
			retorno = HTTP.responseText
						
			if (retorno = "{""boolean"":true}") then
				acessoliberado = true
			end if
						
		end if
		
		if (acessoliberado = true) then
		%>
			Acesso garantido!
		<%
		else
			Response.Redirect "/openam/login.asp"
		end if
		%>
	</body>
</html>
