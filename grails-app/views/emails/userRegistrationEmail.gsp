<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <style>
    body {
        font-family: Arial, Helvetica, sans-serif;
    }

    h1 {
        font-size: 14px;
        font-weight: normal;
    }

    p {
        font-size: 12px;
    }
    </style>
</head>

<body>
<h1>
    Witaj ${user.username}!
</h1>

<p>
    Twoje konto zostało uwtorzone pomyślnie. Aby je aktywować kliknij poniższy link:<br/>
    <a href="${g.createLink(absolute: true, controller: 'top', action: 'newestArticles')}#/aktywuj-konto?token=${token}">aktywuj konto!</a>
</p>

<p>
    Pozdrawiamy,<br/>
    Zespół <g:link absolute="true" controller="home">TaniLans</g:link>
</p>
</body>
</html>