<div class="row auth">
    <div class="col-md-6" ng-controller="LoginController">
        <h3>Zaloguj się</h3>

        <form role="form" ng-submit="submit()">
            <div class="form-group">
                <label for="email-login">Email</label>
                <input id="email-login" type="email" class="form-control" name="email" placeholder="Twój email"/>
            </div>

            <div class="form-group">
                <label for="password-login">Hasło</label>
                <input id="password-login" type="password" class="form-control" name="password" placeholder="Twoje hasło"/>
            </div>

            <button type="submit" class="btn btn-default button-main login">Zaloguj się</button>
        </form>

        <h3>Lub połącz przy pomocy</h3>
        <div class="form-group">
            <a ng-click="fbLogin()" class="social-btn"><asset:image src="facebook-icon.png"/></a>
        </div>
    </div>

    <div class="col-md-6" ng-controller="RegisterController">

        <h3>Zarejestruj się</h3>
        <form role="form" ng-submit="submit()">
            <div class="form-group">
                <label for="register-nick">Ksywka*</label>
                <input type="text" class="form-control" id="register-nick" name="nick" placeholder="Twoja ksywka"/>
            </div>

            <div class="form-group">
                <label for="register-email">Email*</label>
                <input type="email" class="form-control" id="register-email" name="email" placeholder="Twój email"/>
            </div>

            <div class="form-group">
                <label for="register-password">Hasło*</label>
                <input type="password" class="form-control" id="register-password" name="password" placeholder="Twoje hasło"/>
            </div>

            <button type="submit" class="btn btn-default button-main">Zarejestruj się</button>
        </form>
    </div>
</div>