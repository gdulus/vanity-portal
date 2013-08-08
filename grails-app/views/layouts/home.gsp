<!DOCTYPE html>
<html lang="pl">
    <head>
        <meta charset="utf-8">
        <title><g:message code="portal.title" /></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <title><g:layoutTitle default="Vanity cms"/></title>
        <g:layoutHead/>
        <r:layoutResources />
    </head>
    <body>
        <div id="wrap">
            <div class="container">
                <g:layoutBody/>
            </div>
            <div id="push"></div>
        </div>

        <footer>
            <div class="container">
                <div class="row credit">
                    <div class="span2 offset2">
                        <h6 class="vanity"><g:message code="portal.credits" args="[new Date().format('yyyy')]" /></h6>
                    </div>
                    <div class="span6 menu text-right">
                        <a href="#" class="muted"><g:message code="portal.footer.aboutUs" /></a>
                        <a href="#" class="muted"><g:message code="portal.footer.regulations" /></a>
                        <a href="#" class="muted"><g:message code="portal.footer.contact" /></a>
                    </div>
                </div>
            </div>
        </footer>
        <r:layoutResources />
    </body>
</html>