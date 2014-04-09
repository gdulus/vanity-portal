<%@ page contentType="text/html;charset=UTF-8" %>
<style>
    table {
        font-family: helvetica, arial, sans-serif;
        font-size: 14px;
    }

    td, tr {
        text-align: left;
        padding: 10px;
    }
</style>
<table>
    <tr>
        <th>Full name</th>
        <td>${cmd.fullName}</td>
    </tr>
    <tr>
        <th>Email</th>
        <td>${cmd.email}</td>
    </tr>
    <tr>
        <td colspan="2">${cmd.message}</td>
    </tr>
</table>