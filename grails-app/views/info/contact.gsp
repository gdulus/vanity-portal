<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="portal.info.contact"/></title>
    <meta name="layout" content="list"/>
    <asset:stylesheet href="vanity/info.css"/>
</head>

<body>

<div id="summary" class="row">
    <div class="col-md-10 col-md-offset-1">
        <h2><g:message code="portal.info.contact"/></h2>
    </div>
</div>

<div class="row" id="contact">
    <div class="col-md-10 col-md-offset-1">
        <div class="wrapper">
            <div class="row">
                <div class="col-md-12">
                    <g:form controller="info" action="contactSend">
                        <g:if test="${flash.success}">
                            <div class="alert alert-success">
                                <h3><g:message code="${flash.success}"/></h3>
                            </div>
                        </g:if>
                        <g:hasErrors bean="${cmd}">
                            <div class="alert alert-danger">
                                <h3><g:message code="portal.info.contact.sent.error" /></h3>
                                <g:eachError bean="${cmd}">
                                        <p><g:message error="${it}"/></p>
                                </g:eachError>
                            </div>
                        </g:hasErrors>

                        <div class="form-group">
                            <label for="firstName"><g:message
                                    code="portal.info.contact.firstName"/> <span>&#x2606;</span></label>
                            <g:textField name="firstName"
                                         class="form-control ${cmd?.errors?.hasFieldErrors('firstName') ? 'error' : ''}"
                                         value="${cmd?.firstName}"
                                         placeholder="${g.message(code: 'portal.info.contact.firstName.placeholder')}"/>
                        </div>

                        <div class="form-group">
                            <label for="lastName"><g:message code="portal.info.contact.lastName"/> <span>&#x2606;</span>
                            </label>
                            <g:textField name="lastName"
                                         class="form-control ${cmd?.errors?.hasFieldErrors('lastName') ? 'error' : ''}"
                                         value="${cmd?.lastName}"
                                         placeholder="${g.message(code: 'portal.info.contact.lastName.placeholder')}"/>
                        </div>

                        <div class="form-group">
                            <label for="email"><g:message code="portal.info.contact.email"/> <span>&#x2606;</span>
                            </label>
                            <g:textField name="email"
                                         class="form-control ${cmd?.errors?.hasFieldErrors('email') ? 'error' : ''}"
                                         value="${cmd?.email}"
                                         placeholder="${g.message(code: 'portal.info.contact.email.placeholder')}"/>
                        </div>

                        <div class="form-group">
                            <label for="message"><g:message code="portal.info.contact.message"/> <span>&#x2606;</span>
                            </label>
                            <g:textArea name="message"
                                        class="form-control ${cmd?.errors?.hasFieldErrors('message') ? 'error' : ''}"
                                        rows="5" value="${cmd?.message}"/>
                        </div>

                        <div class="form-actions">
                            <span>&#x2606;</span> <g:message code="portal.info.contact.fieldRequired"/> <input
                                type="submit" class="btn btn-default pull-right"
                                value="${g.message(code: 'portal.info.contact.send')}"/>
                        </div>

                        <div class="clearfix"></div>
                    </g:form>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>