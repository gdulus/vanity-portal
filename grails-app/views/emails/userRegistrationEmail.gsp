<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <style>
    body {
        font-family: Arial, Helvetica, sans-serif;
    }

    h1 {
        font-size: 16px;
        font-weight: normal;
    }

    h1 span {
        font-weight: bold;
    }

    p {
        font-size: 12px;
    }
    </style>
</head>

<body>
<h1>
    Witaj <span>${user.username}</span>!
</h1>

<p>
    Twoje konto zostało uwtorzone pomyślnie. Aby je aktywować kliknij poniższy link:<br/>
    <g:if test="${vanity.portal.utils.FeatureUtils.isEnabled('social')}">
        <a href="${g.createLink(absolute: true, controller: 'top', action: 'newestArticles')}#/aktywuj-konto?token=${token}">
    </g:if>
    <g:else>
        <a href="${g.createLink(absolute: true, controller: 'top', action: 'newestArticles', params: ['features.social': true])}#/aktywuj-konto?token=${token}">
    </g:else>
    aktywuj konto!
</a>
</p>

<p>
    Pozdrawiamy,<br/>
    Zespół <g:link absolute="true" controller="home">TaniLans</g:link>
</p>
</body>
</html>